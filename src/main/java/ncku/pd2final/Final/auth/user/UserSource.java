package ncku.pd2final.Final.auth.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * An interface providing access to {@link UserDetailsManager} and {@link PasswordEncoder}, and managing user with its {@link PlayerStatus}
 * <p>
 * When implementing a new UserSource, remember to add {@link org.springframework.stereotype.Component} annotation so our auth system can automatically detect that.
 * </p>
 * <p>
 * After implementing a new UserSource, one may want to change the UserSource the auth system is using. <br />
 * One can achieve that by changing CURRENT_USER_SOURCE_QUALIFIER to specify the UserSource implementation for auth system.
 * </p>
 *
 * @author secminhr
 */
public interface UserSource {
    //auth
    UserDetailsManager getUserDetailsManager();
    PasswordEncoder getPasswordEncoder();

    //provide methods to modify player's status
    /**
     * Add player's experience point.
     * @param username the username of the target user
     * @param exp experience point "add to" the user
     */
    void addExp(String username, int exp);
    /**
     * Level up a user
     * @param username the username of the target user
     */
    void levelUp(String username);
    /**
     * Set a user's nickname.
     * @param username the username of the target user
     * @param nickname new nickname
     */
    void setNickname(String username, String nickname);

    String CURRENT_USER_SOURCE_QUALIFIER = "InMemoryUserSource";
}
