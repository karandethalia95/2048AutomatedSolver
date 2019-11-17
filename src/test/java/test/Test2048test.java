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
	public void intialCheckTest() {
		Test2048 test = new Test2048();
		boolean initCheckBool = test.intialCheck();
		boolean cantMove = false;
		if(initCheckBool == true)
		System.out.println("All okay");
		else
			System.out.println("Not okay");
		
		int[][] gameArray = test.loadArray();
		int[][] gameArrayPrevious;
		
		while(!cantMove) {
			
		boolean keyPress = test.checkNextStepVertical(gameArray);
		if(keyPress) {
			keyPress=test.checkNextStepHorizontal(gameArray);
		}
		else {
			gameArray = test.loadArray();
			continue;
		}
		if(keyPress) {
			gameArrayPrevious=gameArray;
			test.pressDown();
			gameArray = test.loadArray();
			if(test.check2048(gameArray)) {
				break;
			}
			cantMove=test.cantMove(gameArray, gameArrayPrevious);
				if(cantMove) {
					gameArrayPrevious=gameArray;
					test.pressUp();
					gameArray = test.loadArray();
					if(test.check2048(gameArray)) {
						break;
					}
					cantMove=test.cantMove(gameArray, gameArrayPrevious);
				}
				if(cantMove) {
					gameArrayPrevious=gameArray;
					test.pressRight();
					gameArray = test.loadArray();
					if(test.check2048(gameArray)) {
						break;
					}
					cantMove=test.cantMove(gameArray, gameArrayPrevious);
				}
				if(cantMove) {
					gameArrayPrevious=gameArray;
					test.pressLeft();
					gameArray = test.loadArray();
					if(test.check2048(gameArray)) {
						break;
					}
					cantMove=test.cantMove(gameArray, gameArrayPrevious);
				}
				
		}
		
	  }
	}
	

}
