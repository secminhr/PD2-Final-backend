package ncku.pd2final.Final.attack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Attack {
    double attackPoints;
    double time;
    double[] purpose;
    //latitude = 緯度 ； longitude = 經度！！！
    @GetMapping(value = "/attack")
    public void attack(@RequestParam double[] lat, @RequestParam double[] lon, double time, double[] purpose){

        double[][] lAL = new double[lat.length][2] ; //lAL means latitude and longitude
        for(int i=0; i<lat.length; i++){
            lAL[i][0] = lat[i] ;
        }
        for(int i=0; i<lon.length; i++){
            lAL[i][1] = lon[i] ;
        }


        DistanceCalculate test = new DistanceCalculate();
//        System.out.println( test.showMessage(lAL)+ " m");
        attackPoints = test.showMessage(lAL)*0.3 ;
        this.time = time;
        this.purpose = purpose;

    }
    public double showattackPoints(){
        return attackPoints;
    }
    public double showtime(){
        return time;
    }
    public double[] showpuepose(){
        return purpose;
    }

}
