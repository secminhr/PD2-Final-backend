package ncku.pd2final.Final.auth.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * An in-memory implementation of {@link UserSource}.
 * <p>
 *     This is a quick version for development but for production purpose. <br>
 *     One should consider using a implementation which can persist user data.
 * </p>
 *
 * @author secminhr
 */
@Component
@Qualifier("InMemoryUserSource")
public class InMemoryUserSource implements UserSource {

    private final UserDetailsManager manager = new InMemoryUserDetailsManager();

    @Override
    public UserDetailsManager getUserDetailsManager() {
        return manager;
    }

    @Override
    public PasswordEncoder getPasswordEncoder() {
        return new Argon2PasswordEncoder();
    }
}
