package ncku.pd2final.Final.auth.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    private final UserDetailsManager manager = new UserManager();

    @Override
    public UserDetailsManager getUserDetailsManager() {
        return manager;
    }

    @Override
    public PasswordEncoder getPasswordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Override
    public void addExp(String username, int exp) {
        CustomUserDetail user = (CustomUserDetail) manager.loadUserByUsername(username);
        user.getPlayerStatus().addExp(exp);
    }

    @Override
    public void levelUp(String username) {
        CustomUserDetail user = (CustomUserDetail) manager.loadUserByUsername(username);
        user.getPlayerStatus().levelUp();
    }

    @Override
    public void setNickname(String username, String nickname) {
        CustomUserDetail user = (CustomUserDetail) manager.loadUserByUsername(username);
        user.getPlayerStatus().setNickname(nickname);
    }

    private static class UserManager extends InMemoryUserDetailsManager {
        private final Map<String, PlayerStatus> statusMap = new HashMap<>();

        @Override
        public void createUser(UserDetails user) {
            super.createUser(user);
            if (user instanceof CustomUserDetail) {
                statusMap.putIfAbsent(user.getUsername(), ((CustomUserDetail) user).getPlayerStatus());
            }
        }

        @Override
        public void deleteUser(String username) {
            super.deleteUser(username);
            statusMap.remove(username);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails details = super.loadUserByUsername(username);
            return new CustomUserDetail(details, statusMap.get(username));
        }
    }
}
