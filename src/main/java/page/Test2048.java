package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test2048 extends Base {
	
	@FindBy(xpath="//body")
	WebElement gamePlay;
	
	public Test2048() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean intialCheck() {
		boolean initCheckBool=false;
		
		//div[@class="grid-row"][1]//div[1]
		
		
		WebElement init1 = driver.findElement(By.xpath("//div[@class='tile-container']/div[1]"));
		WebElement init2 = driver.findElement(By.xpath("//div[@class='tile-container']/div[2]"));
		
		String pos1 = init1.getAttribute("class");
		String pos2 = init2.getAttribute("class");
		
		
		String value=pos1.substring(10, pos1.indexOf(" tile-position"));
		char column = pos1.charAt(26);
		int columnInt = Integer.parseInt(String.valueOf(column));
		char row = pos1.charAt(28);
		int rowInt=Integer.parseInt(String.valueOf(row));
		System.out.println("Value at column "+(columnInt-1)+" & row "+(rowInt-1)+" is "+value);
		
		try {
			driver.findElement(By.xpath("//div[@class='tile-container']/div[3]"));
		}
		catch(Exception e){
			initCheckBool = true;
		}
		return initCheckBool;
	}
	
	public int[][] loadArray() {
		int[][] array2048 = new int[4][4];
		int k = driver.findElements(By.xpath("//div[@class='tile-container']/div")).size();
		for(int i=1;i<=k;i++) {
			String position = driver.findElement(By.xpath("//div[@class='tile-container']/div["+i+"]")).getAttribute("class");
			String value=position.substring(10, position.indexOf(" tile-position"));
			int valueInt = Integer.parseInt(value);
			char column = position.charAt(26);
			int columnInt = Integer.parseInt(String.valueOf(column));
			char row = position.charAt(28);
			int rowInt=Integer.parseInt(String.valueOf(row));
			array2048[rowInt-1][columnInt-1] = valueInt ;
		}
		
		int i = 1;int j = 1;
		for(i=0;i<4;i++) {
			for(j=0;j<4;j++) {
					System.out.println("["+i+"]"+"["+j+"]="+array2048[i][j]);
			}
		}
		
		return array2048;
		
	}
	
	public void pressDown() {
		gamePlay.sendKeys(Keys.DOWN);
	}
	
	public void pressUp() {
		gamePlay.sendKeys(Keys.UP);
	}
	
	public void pressLeft() {
		gamePlay.sendKeys(Keys.LEFT);
	}
	
	public void pressRight() {
		gamePlay.sendKeys(Keys.RIGHT);
	}

	
	public boolean checkNextStepHorizontal(int[][] gameArray) {
		boolean keyPress=true;
		for(int i=0;i<=3&&keyPress;i++) {
			for(int j=0;j<=3&&keyPress;j++) {
				if(gameArray[i][j]!=0) {
					for(int k=j+1;k<=3;k++) {
						if(gameArray[i][k]!=0)  {
							if(gameArray[i][j]==gameArray[i][k]) {
								gamePlay.sendKeys(Keys.RIGHT);
								keyPress=false;
								break;
							}
							else {
								break;
								//break k
							}
							
						}
						else {
							continue;
						}
					}
					
				}
			}
		}
		return keyPress;
	}
	
	public boolean checkNextStepVertical(int[][] gameArray) {
		boolean keyPress=true;
		for(int i=0;i<=3&&keyPress;i++) {
			for(int j=0;j<=3&&keyPress;j++) {
				if(gameArray[j][i]!=0) {
					for(int k=j+1;k<=3;k++) {
						if(gameArray[k][i]!=0)  {
							if(gameArray[j][i]==gameArray[k][i]) {
								gamePlay.sendKeys(Keys.DOWN);
								keyPress=false;
								break;
							}
							else {
								break;
								//break k
							}
							
						}
						else {
							continue;
						}
					}
					
				}
			}
		}
		return keyPress;
	}
	
	public boolean check2048(int[][] gameArray){
		boolean found2048=false;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
					if(gameArray[i][j]==2048) {
						found2048=true;
						break;
					}
			}
			if(found2048) {
				break;
			}
		}
		return found2048;
		
	}
	
	public boolean cantMove(int[][] gameArrayCurrent,int[][] gameArrayPrevious) {
		boolean cantMove=true;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
					if(gameArrayCurrent[i][j]!=gameArrayPrevious[i][j]) {
						cantMove=false;
						break;
					}
			}
			if(!cantMove) {
				break;
			}
		}
		return cantMove;
	}
	
	

}
