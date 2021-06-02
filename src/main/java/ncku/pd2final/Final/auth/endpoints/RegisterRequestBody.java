package ncku.pd2final.Final.auth.endpoints;

/**
 * A java representation of request boy of /register
 *
 * @author secminhr
 */
public class RegisterRequestBody {
    private String username;
    private String nickname;
    private String password;

    //TODO: Reconsider the type of faction carefully, this is only a temporary solution.
//    private String faction;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getNickname() {
        return nickname;
    }

//    public String getFaction() {
//        return faction;
//    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
//    public void setFaction(String faction) {
//        this.faction = faction;
//    }
}
