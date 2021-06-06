package ncku.pd2final.Final.tower;

import ncku.pd2final.Final.websocket.CheckGame;
import ncku.pd2final.Final.websocket.Renew;
import ncku.pd2final.Final.websocket.UpdateBlood;

public class CalculateBlood {
	int wholeBlood = 2500;                                       			//一開始血量
	double[][] positions = new double[2][4];					//要儲存隨機的位置
	int[] remainBlood = {wholeBlood, wholeBlood, wholeBlood, wholeBlood};		//一開始四個堡壘的血量
	boolean remain = false;								//判斷是否還有血量
	double[] gameEnd = {-1, -1, -1};						//給前端遊戲結束
	String update = "update complete";						//確認完已經更新完成，就回傳給前端這個訊息
	
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
	public void blood(int attackPoints, double[][] attackedPositions, int time) {
		UpdateBlood updateBlood = new UpdateBlood();
		CheckGame check = new CheckGame();
		Renew renew = new Renew();
		
		long neededTime = (long)time;
		
		if(attackedPositions[0][0] == positions[0][0] && attackedPositions[1][0] == positions[1][0]) {
			remainBlood[0] -= attackPoints;
			if(remainBlood[0] <= 0)
				remainBlood[0] = 0;
			checkBlood(remainBlood);
			if(remain == true) {
				check.sendMessage(gameEnd);	//傳送四個堡壘都沒寫的訊息[-1、-1、-1]
				updateTowerAndBlood();		//更新血量跟位置
				renew.sendMessage(update); 	//通知已更改新的血量跟位置
			} else {
				updateBlood.sendMessage(remainBlood[0], attackedPositions[0][0], attackedPositions[1][0]);
			}
		} else if(attackedPositions[0][0] == positions[0][1] && attackedPositions[1][0] == positions[1][1]) {
			remainBlood[1] -= attackPoints;
			if(remainBlood[1] <= 0)
				remainBlood[1] = 0;
			checkBlood(remainBlood);
			if(remain == true) {
				check.sendMessage(gameEnd);	//傳送四個堡壘都沒寫的訊息[-1、-1、-1]
				updateTowerAndBlood();		//更新血量跟位置
				renew.sendMessage(update); 	//通知已更改新的血量跟位置
			} else {
				updateBlood.sendMessage(remainBlood[1], attackedPositions[0][0], attackedPositions[1][0]);
		}
			} else if(attackedPositions[0][0] == positions[0][2] && attackedPositions[1][0] == positions[1][2]) { 
			remainBlood[2] -= attackPoints;
			if(remainBlood[2] <= 0)
				remainBlood[2] = 0;
			checkBlood(remainBlood);
			if(remain) {
				check.sendMessage(gameEnd);	//傳送四個堡壘都沒寫的訊息[-1、-1、-1]
				updateTowerAndBlood();		//更新血量跟位置
				renew.sendMessage(update); 	//通知已更改新的血量跟位置
			} else {
				updateBlood.sendMessage(remainBlood[2], attackedPositions[0][0], attackedPositions[1][0]);
		}
			} else if(attackedPositions[0][0] == positions[0][3] && attackedPositions[1][0] == positions[1][3]) {
			remainBlood[3] -= attackPoints;
			if(remainBlood[3] <= 0)
				remainBlood[3] = 0;
			checkBlood(remainBlood);
			if(remain) {
				check.sendMessage(gameEnd);	//傳送四個堡壘都沒寫的訊息[-1、-1、-1]
				updateTowerAndBlood();		//更新血量跟位置
				renew.sendMessage(update); 	//通知已更改新的血量跟位置
			} else {
				updateBlood.sendMessage(remainBlood[3], attackedPositions[0][0], attackedPositions[1][0]);
			}
		} 
	}

	/*
	 * 扣血
 	 * 會回傳 (int)血量、(double)緯度、(double)經度
 	 * 如果遊戲結束，會回傳double[-1、-1、-1]
 	 * 然後更新完會傳string="update complete"給前端
	 */
	
	public void checkBlood(int[] remainBlood) {
		if(remainBlood[0] == 0 && remainBlood[1] == 0 && remainBlood[2] == 0 && remainBlood[3] == 0)
			remain = true;
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
	 * 更新血量(可能沒辦法更新到數字，所以需要測試)
	 */
}
