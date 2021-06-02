package ncku.pd2final.Final.attack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class Attack {
    double attackPoints;
    double time;
    double[] purpose;
    //latitude = 緯度 ； longitude = 經度！！！
    @GetMapping(value = "/attack")
    public void attack(@RequestParam double[] lat,
                       @RequestParam double[] lon,
                       @RequestParam double time,
                       @RequestParam double[] purpose)
    {

        double[][] lAL = new double[lat.length][2] ; //lAL means latitude and longitude
        for(int i=0; i<lat.length; i++){
            lAL[i][0] = lat[i] ;
        }
        for(int i=0; i<lon.length; i++){
            lAL[i][1] = lon[i] ;
        }


        DistanceCalculate test = new DistanceCalculate()   ;

        attackPoints = test.showMessage(lAL)*0.3 ;
        this.time = time;
        this.purpose = purpose;

//        System.out.println("System is working!");
//        for(double latitude : lat){
//            System.out.println(latitude);
//        }
//        for(double longitude : lon){
//            System.out.println(longitude);
//        }
//        System.out.println(time);
//        for(double location : purpose){
//            System.out.println(location);
//        }

    }

    @GetMapping(value = "/attack")
    public String checkData(@RequestParam double[] lat,
                            @RequestParam double[] lon,
                            @RequestParam double time,
                            @RequestParam double[] purpose,
                            HttpServletResponse response) {
        if (lat != null && lon != null && purpose != null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return "{\"success\": true}";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "{\"success\": false, \"message\": \"Some arguments leak.\"}";
        }

}
    public double showAttackPoints(){
        return attackPoints;
    }
    public double showTime(){
        return time;
    }
    public double[] showPurpose(){
        return purpose;
    }

}
