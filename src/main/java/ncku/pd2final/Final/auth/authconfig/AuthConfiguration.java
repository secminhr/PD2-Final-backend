package ncku.pd2final.Final.auth.authconfig;

import ncku.pd2final.Final.auth.endpoints.AuthController;
import ncku.pd2final.Final.auth.user.UserSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * Sets up necessary configuration for authentication in SpringBoot. <br>
 * Will automatically look up the entry point when login failure and the current using {@link UserSource}.
 *
 * @author secminhr
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint entryPoint;
    private final UserDetailsService service;
    private final PasswordEncoder passwordEncoder;

    /**
     * The constructor that is used automatically by SpringBoot framework. <br />
     * It's unnecessary to call this in manual.
     *
     * @param entryPoint entrypoint used when login failed or receiving unauthorized request
     * @param source the current {@link UserSource} in use
     */
    public AuthConfiguration(@Autowired AuthenticationEntryPoint entryPoint,
                             @Autowired @Qualifier(UserSource.CURRENT_USER_SOURCE_QUALIFIER) UserSource source) {
        this.entryPoint = entryPoint;
        service = source.getUserDetailsManager();
        passwordEncoder = source.getPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //make all endpoints require logged in except /login and /register
        http.csrf().disable()
                .addFilter(new JsonUsernamePasswordFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers(AuthController.REGISTER_ENDPOINT).permitAll()
                .anyRequest().authenticated();

        //setup logout handler to return HTTP_OK rather than redirection
        http.logout(logout -> {
            SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
            handler.setRedirectStrategy(new NoRedirect());
            logout.logoutSuccessHandler(handler);
        });

        //setup entry when user fails to log in
        http.exceptionHandling().authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider());
    }

    /**
     * Provide a {@link AuthenticationProvider} to SpringBoot. <br>
     * When trying to change the {@link AuthenticationProvider}, consider modify the return value of this method.
     *
     * @return the desired {@link AuthenticationProvider}
     */
    private AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
