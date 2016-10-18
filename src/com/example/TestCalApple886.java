package com.example;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCalApple886 {

	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
//		  System.setProperty("webdriver.chrome.driver", "/Users/Nigel/software/chrome driver/chromedriver");
//		  System.setProperty("webdriver.ie.driver", "src\\main\\resources\\chromedriver.exe");
		  driver = new FirefoxDriver();
//		  driver = new ChromeDriver();
		  baseUrl = "http://cal.apple886.com";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testCalApple886() throws Exception {
		  driver.get(baseUrl);
		  //测试两位正整数数相加是否是正确的（10~99）+（10~99）
		  for(int i = 1; i <= 9; i++){
			  //取出第一个加数的十位数
			  //构造button的id
			  String id1_1 = "simple" + i;
			  WebElement button1_1 = driver.findElement(By.id(id1_1));
			  //获取这个button对应的值
			  int value1_1 = Integer.parseInt(button1_1.getAttribute("value")) * 10;
			  boolean flag = false;
			   for(int j = 0; j <= 9; j++){
				   //取出第一个加数的个位数
				   String id1_2 = "simple" + j;
				   WebElement button1_2 = driver.findElement(By.id(id1_2));
				   //获取这个button对应的值
				   int value1_2 = Integer.parseInt(button1_2.getAttribute("value"));
				   
				   int p1 = value1_1 + value1_2;
				   
				   for(int l = 1; l <= 9; l++){
					   //取出第二个加数的十位数
					   String id2_1 = "simple" + l;
					   WebElement button2_1 = driver.findElement(By.id(id2_1));
					   //获取这个button对应的值
					   int value2_1 = Integer.parseInt(button2_1.getAttribute("value")) * 10;
					   for(int m = 0; m <= 9; m++){
						   //取出第二个加数的个位数
						   String id2_2 = "simple" + m;
						   WebElement button2_2 = driver.findElement(By.id(id2_2));
						   //获取这个button对应的值
						   int value2_2 = Integer.parseInt(button2_2.getAttribute("value"));
						   
						   int p2 = value2_1 + value2_2;
						   
						   //计算理论结果
						   int result1 = i * 10 + j + (l * 10 + m);
						   //计算真实结果 按钮结果
						   int result2 = p1 + p2;
						   //计算操作结果
						   button1_1.click();
						   button1_2.click();
						   driver.findElement(By.xpath(".//*[@id='simpleAdd']")).click();
						   button2_1.click();
						   button2_2.click();
						   driver.findElement(By.id("simpleEqual")).click();
						   int result3 = Integer.parseInt(driver.findElement(By.cssSelector("#resultIpt")).getAttribute("value"));
						   
						   
						   if(result1 != result2){
							   System.out.println("按钮设置有问题");
							   flag = true;
						   }else if(result2 != result3){
							   System.out.println("计算器运算有问题");
							   flag = true;
						   }
					   }
				   }
			   }

			   if(flag == false)
				   System.out.println("两位数加法运算没问题");
		  }
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    driver.findElement(by);
	      return true;
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}
