package com.annie.testcase;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.annie.testng.TestNG;
import com.annie.util.ExcelData;
import com.annie.util.ScreenShot;

public class LoginTestURL {

	private WebDriver driver;
	// private String username, password;
	private String sid,url;

	TestNG tn = new TestNG();

	@DataProvider(name = "accounts")
	// 登录用户的账户Excel，返回的是Object数组
	public Iterator<Object[]> data() throws Exception {
		return (Iterator<Object[]>) new ExcelData("accounts_login",
				"testD");

	}

	public void prmap(Map<String, String> arr) throws Exception {
		driver = new FirefoxDriver();
		// driver.get("http://redcoil.technologystudios.com/index.html");

		// driver.get("http://10.0.0.88:8080/bkredcoil/index.html");
	
		/*
		 * driver.findElement(By.id("username")).clear();
		 * driver.findElement(By.id("password")).clear();
		 */

		Set<String> set = arr.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String sid = (String) it.next();
			/*
			 * if (s.equals("Email")) { username = arr.get(s); } else { password
			 * = arr.get(s); }
			 */
			System.out.println(arr.get(sid));
			url="http://10.0.0.88:8080/bkredcoil/controller/security/signon?sid="+arr.get(sid);
			System.out.println(url);
			
			driver.get(url);

			driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

			/*
			 * if (driver.findElement(By.id("username")).getText().length()==0)
			 * { driver.findElement(By.id("username")).clear();
			 * driver.findElement(By.id("username")).sendKeys(username); } if
			 * (driver.findElement(By.id("password")).getText().length()==0) {
			 * driver.findElement(By.id("password")).clear();
			 * 
			 * 
			 * 
			 * driver.findElement(By.id("btn-login")).click();
			 */

			driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
			Thread.sleep(500);
			driver.findElement(By.id("button-1056-btnInnerEl")).click();
			
			driver.close();
			// ScreenShot ss = new ScreenShot();
			// try{
			// ss.cutshot(driver);
			// }
			// catch(Exception e){
			// e.printStackTrace();
			//
			// }

		}

	}

	// 写一个类似prmap的方法，将Map类型的exel数据传入，并在指定的URL进行登录，将Map中的Email和password 做参数传入登陆即可

	@Test(dataProvider = "accounts")
	public void ts(Map<String, String> data) throws Exception {

		this.prmap(data);

		// 每次打印一组account，则调用登录用例

		System.out.println("=====over=====");
		System.out.println("");
	}

}
