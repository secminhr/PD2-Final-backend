package ncku.pd2final.Final.auth.endpoints;

import ncku.pd2final.Final.auth.user.CustomUserDetail;
import ncku.pd2final.Final.auth.user.PlayerStatus;
import ncku.pd2final.Final.auth.user.UserSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Provide api endpoints for app to use. <br>
 * No code should call those methods directly.
 * <p>
 * For documentation for these apis, see Endpoints.md in auth folder.</a>
 * </p>
 *
 * @author secminhr
 */
@RestController
public class AuthController {

    public static final String REGISTER_ENDPOINT = "/register";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String INFO_ENDPOINT = "/info";

    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
    public AuthController(@Autowired @Qualifier(UserSource.CURRENT_USER_SOURCE_QUALIFIER) UserSource source) {
        this.manager = source.getUserDetailsManager();
        this.passwordEncoder = source.getPasswordEncoder();
    }

    /**
     * An endpoint representing a private resource that requires login.
     * <p>
     *     This endpoint only serves for developing purpose for backend developers to test whether the login system is working. <br>
     *     No call should arrive in production.
     * </p>
     *
     */
    @GetMapping("/private")
    public String privatePoint() {
        return "private";
    }

    @GetMapping(value = INFO_ENDPOINT, produces = "application/json")
    public String info(Principal principal) {
        String username = principal.getName();
        CustomUserDetail user = (CustomUserDetail) manager.loadUserByUsername(username);
        return user.toJson();
    }

    /**
     * An endpoint for a user to register a new account.
     */
    @PostMapping(value = REGISTER_ENDPOINT, produces = "application/json")
    public String register(@RequestBody RegisterRequestBody requestBody, HttpServletResponse response) {
        CustomUserDetail user;
        try {
            user = new CustomUserDetail.Builder()
                    .passwordEncoder(passwordEncoder::encode)
                    .username(requestBody.getUsername())
                    .password(requestBody.getPassword())
                    .roles("PLAYER")
                    .playerStatus(PlayerStatus.NewPlayer(requestBody.getNickname(), requestBody.getFaction()))
                    .build();
        } catch (IllegalArgumentException e) {
            //some fields in requestBody is problematic
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "{\"success\": false, \"message\": \"wrong arguments\"}";
        }

        try {
            manager.createUser(user);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return "{\"success\":true, \"auth\":\"" + LOGIN_ENDPOINT + "\"}";
        } catch (IllegalArgumentException e) {
            //user already registered
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "{\"success\": false, \"message\": \"username already exists\", \"auth\":\"" + LOGIN_ENDPOINT + "\"}";
        }
    }

}
