package ncku.pd2final.Final.auth.user;

public class PlayerStatus {
    private int exp;
    private int level;
    private String nickname;
    private final String faction;

    public PlayerStatus(int exp, int level, String nickname, String faction) {
        this.exp = exp;
        this.level = level;
        this.nickname = nickname;
        this.faction = faction;
    }

    public int getExp() {
        return exp;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        this.level++;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFaction() {
        return faction;
    }

    public static PlayerStatus NewPlayer(String nickname, String faction) {
        return new PlayerStatus(0, 0, nickname, faction);
    }
}
