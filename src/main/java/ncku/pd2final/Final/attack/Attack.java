package ncku.pd2final.Final.attack;

import ncku.pd2final.Final.tower.CalculateBlood;
import ncku.pd2final.Final.tower.DealWithBlood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Timer;

@RestController
public class Attack {
    double preAttackPoints ;
    int attackPoints;
    int time;
//    double[] purpose;

    @Autowired CalculateBlood bloodCalculator;
    //latitude = 緯度 ； longitude = 經度！！！
    @GetMapping(value = "/attack")
    public void attack(@RequestParam double[] lat,
                       @RequestParam double[] lon,
                       @RequestParam int time,
                       @RequestParam double[] purpose,
                       HttpServletResponse response)
    {
        double[][] lAL = new double[lat.length][2] ; //lAL means latitude and longitude

        if(dataExist(lat,lon,purpose)){

            for(int i=0; i<lat.length; i++){
                lAL[i][0] = lat[i] ;
            }
            for(int i=0; i<lon.length; i++){
                lAL[i][1] = lon[i] ;
            }

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            DistanceCalculate test = new DistanceCalculate() ;

            //把purpose格式轉成林欣諴要求的樣子
            double[][] purposeForCalculateBloodDotBlood = new double[2][1] ;
            purposeForCalculateBloodDotBlood[0][0] = purpose[0] ;
            purposeForCalculateBloodDotBlood[1][0] = purpose[1] ;

            preAttackPoints = test.showMessage(lAL)*50 ;
            attackPoints =  (int)preAttackPoints ;
            this.time = time * 60 * 1000;

            //呼叫林欣諴的東西
            Timer simpleTimer = new Timer() ;

            DealWithBlood dealWithBlood = new DealWithBlood(bloodCalculator, this.attackPoints, purposeForCalculateBloodDotBlood, this.time);
            simpleTimer.schedule(dealWithBlood, this.time) ;

        }else
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }

    public boolean dataExist(double[] lat,
                             double[] lon,
                             double[] purpose) {
        return lat != null && lon != null && purpose != null;
    }

}
