package ncku.pd2final.Final.tower;

import java.util.TimerTask;


public class DealWithBlood extends TimerTask {
    public CalculateBlood cal ;
    @Override
    public void run() {
        System.out.println("t");
        cal.blood();
    }
}
