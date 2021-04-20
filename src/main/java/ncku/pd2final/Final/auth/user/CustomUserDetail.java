package ncku.pd2final.Final.auth.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.function.Function;

public class CustomUserDetail extends User {
    private final PlayerStatus playerStatus;
    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    private CustomUserDetail(String username,
                             String password,
                             boolean enabled,
                             boolean accountNonExpired,
                             boolean credentialsNonExpired,
                             boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities,
                             PlayerStatus playerStatus) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.playerStatus = playerStatus;
    }

    public CustomUserDetail(UserDetails user, PlayerStatus playerStatus) {
        this(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            user.isAccountNonExpired(),
            user.isCredentialsNonExpired(),
            user.isAccountNonLocked(),
            user.getAuthorities(),
            playerStatus
        );
    }

    public String toJson() {
        return "{\"username\": \"" + getUsername() + "\", " +
                "\"status\": " + playerStatus.toJson() + "}";
    }

    public static class Builder {
        private final User.UserBuilder builder = User.builder();
        private PlayerStatus playerStatus;

        public Builder passwordEncoder(Function<String, String> encoder) {
            builder.passwordEncoder(encoder);
            return this;
        }

        public Builder username(String username) {
            builder.username(username);
            return this;
        }

        public Builder password(String password) {
            builder.password(password);
            return this;
        }

        public Builder roles(String... roles) {
            builder.roles(roles);
            return this;
        }

        public Builder playerStatus(PlayerStatus status) {
            playerStatus = status;
            return this;
        }

        public CustomUserDetail build() {
            UserDetails user = builder.build();
            return new CustomUserDetail(user, playerStatus);
        }


    }
}
