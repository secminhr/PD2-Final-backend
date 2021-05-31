package ncku.pd2final.Final.attack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.toDegrees;

@RestController
public class Attack {

    //latitude = 緯度 ； longitude = 經度！！！
    @GetMapping(value = "/attack")
    public void attack(@RequestParam double[] lat, @RequestParam double[] lon){

        double[][] lAL = new double[lat.length][2] ; //lAL means latitude and longitude
        for(int i=0; i<lat.length; i++){
            lAL[i][0] = lat[i] ;
        }
        for(int i=0; i<lon.length; i++){
            lAL[i][1] = lon[i] ;
        }


        DistanceCalculate test = new DistanceCalculate();
//        System.out.println( test.showMessage(lAL)+ " m");
        double attackPoints = test.showMessage(lAL)*0.3 ;

    }

}
