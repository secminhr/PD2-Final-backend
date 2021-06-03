package ncku.pd2final.Final.tower;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TowerPosition {
	//double[][] border = {{22.996922457722466, 23.002152153822472},{120.2142340863143, 120.2244958401646}};
	double lat[] = {22.997794073739134, 22.997794073739134, 22.998665689755802,  22.997794073739134, 22.997794073739134, 22.998665689755802, 23.000408921789038, 23.001280537805706, 23.001280537805706, 23.001280537805706, 23.001280537805706, 23.000408921789038};
	double lng[] = {120.2159443786227, 120.2176546709311, 120.2159443786227, 120.2210752555479, 120.2227855478563, 120.2227855478563, 120.2227855478563, 120.2227855478563, 120.2210752555479, 120.2176546709311, 120.2159443786227, 120.2159443786227};
	double[][] position = {{lat[0], lat[1], lat[2], lat[3], lat[4], lat[5], lat[6], lat[7], lat[8], lat[9], lat[10], lat[11]},
			       {lng[0], lng[1], lng[2], lng[3], lng[4], lng[5], lng[6], lng[7], lng[8], lng[9], lng[10], lng[11]}};
	double[][] randomPosition = new double[2][4];
	double[][] positionAndBlood = new double[3][4];
	//隨機選出四個經緯度
	int quadrant1 = (int)(Math.random() * 3);     //隨機出0~2
	int quadrant2 = (int)(Math.random() * 3 + 3); //隨機出3~5
	int quadrant3 = (int)(Math.random() * 3 + 6); //隨機出6~8
	int quadrant4 = (int)(Math.random() * 3 + 9); //隨機出9~11
			
	
	public void towerAndBlood() {
		quadrant1 = (int)(Math.random() * 3);     // 
		quadrant2 = (int)(Math.random() * 3 + 3); //重新隨機數字
		quadrant3 = (int)(Math.random() * 3 + 6); //
		quadrant4 = (int)(Math.random() * 3 + 9); // 
		positionAndBlood();
	}
	/*
	 * 儲存所有的堡壘位置，並去隨機堡壘位置
	 */
	@GetMapping(value = "/positionAndBlood")
	public double[][] positionAndBlood() {
		for(int i = 0; i < 2; i++) {
			randomPosition[i][0] = position[i][quadrant1];
			randomPosition[i][1] = position[i][quadrant2];
			randomPosition[i][2] = position[i][quadrant3];
			randomPosition[i][3] = position[i][quadrant4];
		}
		for(int i = 0; i < 2; i++) {
			positionAndBlood[i][0] = randomPosition[i][0];
			positionAndBlood[i][1] = randomPosition[i][1];
			positionAndBlood[i][2] = randomPosition[i][2];
			positionAndBlood[i][3] = randomPosition[i][3];
		}
		
		CalculateBlood calculateblood = new CalculateBlood();
		calculateblood.storePositions(randomPosition);
		for(int i = 0; i < 4; i++) {
			positionAndBlood[2][i] = calculateblood.remainBlood[i]; 
		}
		
		return positionAndBlood;
		
	}
	/*
	 * 隨機堡壘位置，再把出來的東西丟給Blood的class
	 * positionAndBlood[0][]:lat
	 * positionAndBlood[1][]:lng
	 * positionAndBlood[2][]:blood
	 */
}
