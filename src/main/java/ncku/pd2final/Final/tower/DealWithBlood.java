package ncku.pd2final.Final.tower;

import java.util.Arrays;
import java.util.TimerTask;


public class DealWithBlood extends TimerTask {
    private final CalculateBlood cal ;
    private final int attackPoints;
    private final double[][] purpose;
    private final int time;

    public DealWithBlood(CalculateBlood cal, int attackPoints, double[][] purpose, int time) {
        this.cal = cal;
        this.attackPoints = 1250;
//        this.attackPoints = attackPoints;
        this.purpose = Arrays.stream(purpose).map(double[]::clone).toArray(double[][]::new);
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println("t");
        cal.blood(attackPoints, purpose, time);
    }
}
