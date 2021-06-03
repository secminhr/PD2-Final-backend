package ncku.pd2final.Final.auth.user;

/**
 * A plain object describing a player's status. <br/>
 * One may need to change this if a new status in introduced.
 *
 * @author secminhr
 */
public class PlayerStatus {
    private int exp;
    private int level;
    private String nickname;
//    private final String faction;

    public PlayerStatus(int exp, int level, String nickname/*, String faction*/) {
        this.exp = exp;
        this.level = level;
        this.nickname = nickname;
//        this.faction = faction;
    }

    public static PlayerStatus NewPlayer(String nickname) {
        if(nickname == null){
            throw new IllegalArgumentException("nickname should not be null");
        }
        return new PlayerStatus(0, 0, nickname);
    }

    public int getExp() {
        return exp;
    }

    void addExp(int exp) {
        this.exp += exp;
    }

    public int getLevel() {
        return level;
    }

    void levelUp() {
        this.level++;
    }

    public String getNickname() {
        return nickname;
    }

    void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public String getFaction() {
//        return faction;
//    }

    public String toJson() {
//        return "{    \"exp\":" + exp + ", " + "\"level\":" + level + ", " + "\"nickname\":\"" + nickname }" ;

        return "{\"exp\" :"  +   exp   +  ","  + "\"level\":"  +  level  +  ","  +  "\"nickname\":"  +  "\""  +  nickname  +  "\""  +  "}"      ;

    }

    public static PlayerStatus NewPlayer(String nickname, String faction) {
        if (nickname == null || faction == null) {
//            throw new IllegalArgumentException("nickname and faction should not be null");
            throw new IllegalArgumentException("nickname should not be null");
        }
        return new PlayerStatus(0, 0, nickname/*, faction*/);
    }
}
