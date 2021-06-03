package ncku.pd2final.Final.attack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Attack {
    double preAttackPoints ;
    int attackPoints;
    double time;
    double[] purpose;
    //latitude = 緯度 ； longitude = 經度！！！
    @GetMapping(value = "/attack")
    public void attack(@RequestParam double[] lat,
                       @RequestParam double[] lon,
                       @RequestParam double time,
                       @RequestParam double[] purpose,
                       HttpServletResponse response)
    {
        double[][] lAL = new double[lat.length][2] ; //lAL means latitude and longitude

//        boolean check = checkData(lat,lon,purpose) ;

        if(dataExist(lat,lon,purpose)){

            for(int i=0; i<lat.length; i++){
                lAL[i][0] = lat[i] ;
            }
            for(int i=0; i<lon.length; i++){
                lAL[i][1] = lon[i] ;
            }

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            DistanceCalculate test = new DistanceCalculate() ;


            preAttackPoints = test.showMessage(lAL)*0.3 ;
            attackPoints =  (int)preAttackPoints ;
            this.time = time;
            this.purpose = purpose;

            //呼叫林欣諴的東西

        }else
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);



        //System.out.println("System is working!");
//
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


    public boolean dataExist(double[] lat,
                             double[] lon,
                             double[] purpose) {
        return lat != null && lon != null && purpose != null;
    }




//    public double showAttackPoints(){
//        return attackPoints;
//    }
//    public double showTime(){
//        return time;
//    }
//    public double[] showPurpose(){
//        return purpose;
//    }

}
