package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.Base;
import page.Test2048;

public class Test2048test extends Base{
	
	
	@BeforeMethod
	public void driver() {
		Base b=new Base();
		b.initDriver();
	}
	 
	
	@Test
	public void intialCheckTest() throws InterruptedException {
		Test2048 test = new Test2048();
		boolean initCheckBool = test.intialCheck();
		boolean cantMove = false;
		boolean gameWon = false;
		boolean gameOver = false;
		if(initCheckBool == true)
		System.out.println("Game started fine");
		else
			System.out.println("Something wrong with the game start");
		
		int[][] gameArray = test.loadArray();
		int[][] gameArrayPrevious;
		
		while(!gameWon && !gameOver) {
			
		boolean keyPress = test.checkNextStepVertical(gameArray);
		if(keyPress) {
			keyPress=test.checkNextStepHorizontal(gameArray);
		}
		else {
			System.out.println("Down");
			gameArray = test.loadArray();
			gameWon = test.check2048(gameArray);
			continue;
		}
		if(keyPress) {
			gameArrayPrevious=gameArray;
			test.pressUp();
			gameArray = test.loadArray();	
			cantMove=test.cantMove(gameArray, gameArrayPrevious);
				if(cantMove) {
					test.pressDown();
					gameArray = test.loadArray();
					cantMove = test.cantMove(gameArray, gameArrayPrevious);
					if(cantMove) {
						test.pressRight();
						gameArray = test.loadArray();
						cantMove = test.cantMove(gameArray, gameArrayPrevious);
						if(cantMove) {
							test.pressLeft();
							gameArray = test.loadArray();
						}
						else {
							gameWon = test.check2048(gameArray);
						}
						
					}
					else {
						gameWon = test.check2048(gameArray);
					}
				}
				else {
					gameWon = test.check2048(gameArray);
			}
				
		}else {
			System.out.println("Right");
			gameArray = test.loadArray();
			gameWon = test.check2048(gameArray);
			continue;
		}
		gameOver = test.gameOver();
		
	  }
	}
	

}
