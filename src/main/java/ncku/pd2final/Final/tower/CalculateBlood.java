package ncku.pd2final.Final.tower;

public class CalculateBlood {
	int wholeBlood = 2500;
	double[][] positions = new double[2][4];
	int[] remainBlood = {wholeBlood, wholeBlood, wholeBlood, wholeBlood};
	boolean remain = false;
	double[] gameEnd = {-1, -1, -1};
	
	public void storePositions(double[][] positions) {
		for(int i = 0; i < 2; i++) {
			for(int j = 0 ; j < 4; j++) {
				this.positions[i][j] = positions[i][j]; 
			}
		}
	}
	/*
	 * 將隨機到的四個堡壘座標存到這個類別裡面
	 */
	
	//傳血量、lat、lng
	public double[] blood(int attackPoints, double[][] attackedPositions, double time) {
		long neededTime = (long)time;
		double[] contain = new double[3];
		try {
			Thread.sleep(neededTime * 60 * 1000);
			if(attackedPositions[0][0] == positions[0][0] && attackedPositions[1][0] == positions[1][0]) {
				remainBlood[0] -= attackPoints;
				if(remainBlood[0] <= 0)
					remainBlood[0] = 0;
				checkBlood(remainBlood);
				if(remain == true) {
					updateTowerAndBlood();
					return gameEnd;
				} else {
					contain[0] = remainBlood[0];
					contain[1] = attackedPositions[0][0];
					contain[2] = attackedPositions[1][0];
				}
					return contain;
			} else if(attackedPositions[0][0] == positions[0][1] && attackedPositions[1][0] == positions[1][1]) {
				remainBlood[1] -= attackPoints;
				if(remainBlood[1] <= 0)
					remainBlood[1] = 0;
				checkBlood(remainBlood);
				if(remain == true) {
					updateTowerAndBlood();
					return gameEnd;
				} else{
					contain[0] = remainBlood[1];
					contain[1] = attackedPositions[0][0];
					contain[2] = attackedPositions[1][0];
				}
					return contain;
			} else if(attackedPositions[0][0] == positions[0][2] && attackedPositions[1][0] == positions[1][2]) { 
				remainBlood[2] -= attackPoints;
				if(remainBlood[2] <= 0)
					remainBlood[2] = 0;
				checkBlood(remainBlood);
				if(remain == true) {
					updateTowerAndBlood();
					return gameEnd;
				} else {
					contain[0] = remainBlood[2];
					contain[1] = attackedPositions[0][0];
					contain[2] = attackedPositions[1][0];
				}
					return contain;
			} else if(attackedPositions[0][0] == positions[0][3] && attackedPositions[1][0] == positions[1][3]) {
				remainBlood[3] -= attackPoints;
				if(remainBlood[3] <= 0)
					remainBlood[3] = 0;
				checkBlood(remainBlood);
				if(remain == true) {
					updateTowerAndBlood();
					return gameEnd;
				} else {
					contain[0] = remainBlood[3];
					contain[1] = attackedPositions[0][0];
					contain[2] = attackedPositions[1][0];
				}
					return contain;
			} else
				return null;
		} catch (InterruptedException e) {
			return null;
		}
	}
	/*
	 * 扣血
 	 * 會回傳double[血量、lat、lng]，如果遊戲結束，會回傳double[-1、-1、-1]
	 */
	
	public boolean checkBlood(int[] remainBlood) {
		if(remainBlood[0] == 0 && remainBlood[1] == 0 && remainBlood[2] == 0 && remainBlood[3] == 0)
			remain = true;
		return remain;
	}
	/*
	 * 判斷是否所有血量歸零
	 */
	public void updateTowerAndBlood() {
		TowerPosition towerPosition = new TowerPosition();
		towerPosition.towerAndBlood();
		for(int i = 0; i < 4; i++) 
			remainBlood[i] = wholeBlood;
		remain = false;
	}
	/*
	 * 更新血量
	 */
	
	public String update() {
		return "Update completed.";
	}
	/*
	 * 通知設定完成
	 */	
}
