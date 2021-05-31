package ncku.pd2final.Final.attack;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.toDegrees;

public class DistanceCalculate {
    double[] lALn= {181, 181} ;

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = sin(toRadians(lat1)) * sin(toRadians(lat2)) + cos(toRadians(lat1)) * cos(toRadians(lat2)) * cos(toRadians(theta));
            dist = acos(dist);
            dist = toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return dist* 1.609344 * 1000D ;
        }
    }

    public double showMessage(double[][] lAL){
        ArrayList<Double []> setupPosition = new ArrayList<Double []>() {{

            for (double[] doubles : lAL) {
                this.add(Arrays.stream(doubles).boxed().toArray(Double[]::new));
            }

            this.add(Arrays.stream(lALn).boxed().toArray(Double[]::new));
        }} ;
        return addTogether(setupPosition);
    }

    private static double addTogether(ArrayList <Double[]> lAL){
        double preDistance ;
        double finalDistance = 0D ;
        for(int i=0, j=1 ; lAL.get(j)[0]!=181 && lAL.get(j)[1]!=181 ; i++, j++){
            preDistance = distance(lAL.get(i)[0], lAL.get(i)[1], lAL.get(j)[0], lAL.get(j)[1]) ;
            finalDistance = preDistance + finalDistance ;
        }
        return finalDistance ;
    }
}
