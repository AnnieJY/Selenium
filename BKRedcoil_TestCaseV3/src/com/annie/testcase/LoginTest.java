package com.annie.testcase;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.annie.testng.TestNG;
import com.annie.util.ExcelData;
import com.annie.util.ScreenShot;

public class LoginTest {

	private WebDriver driver;
	private String exel_username = "";
	private String exel_password = "";
	//用于测试服务器 网速较慢情况下 跳转页面后获取失败的情况
	//private String targetAfterLogin_URL = "http://redcoil.technologystudios.com/web/index.html";
	private String targetAfterLogin_URL="http://10.0.0.88:8080/bkredcoil/web/index.html";

	TestNG tn = new TestNG();

	@DataProvider(name = "accounts")
	// 登录用户的账户Excel，返回的是Object数组
	public Iterator<Object[]> data() throws Exception {
		return (Iterator<Object[]>) new ExcelData("accounts_login", "testC");// 分别是文件名和用例名（sheet
																				// name）

	}

	public void prmap(Map<String, String> arr) throws Exception {
		driver = new FirefoxDriver();
		//driver.get("http://redcoil.technologystudios.com/index.html");

		 driver.get("http://10.0.0.88:8080/bkredcoil/index.html");
		

		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("password")).clear();

		Set<String> set = arr.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String s = (String) it.next();
			if (s.equals("Email")) {
				exel_username = arr.get(s);
				exel_password = "";

			} else {
				exel_password = arr.get(s);

			}
			// 判断：如果取出来用户名和密码不为空，则

			if ((exel_username.length() > 0) && (exel_password.length() > 0)) {
				System.out.println("login_username:" + exel_username);
				System.out.println("login_password:" + exel_password);
				if (driver.findElement(By.id("username")).getText().length() == 0) {
					driver.findElement(By.id("username")).clear();
					driver.findElement(By.id("username")).sendKeys(
							exel_username);
				}
				if (driver.findElement(By.id("password")).getText().length() == 0) {
					driver.findElement(By.id("password")).clear();
					driver.findElement(By.id("password")).sendKeys(
							exel_password);
				}

				driver.findElement(By.id("btn-login")).click();
				 
				Thread.sleep(1500);
                //登录成功后页面跳转，需要判断是否跳转到成功页面
               String current_url = driver.getCurrentUrl();
               System.out.println(current_url);
               if (current_url.equals(targetAfterLogin_URL)) {
              //如果成功，则点击登录并退出driver
                   driver.get(targetAfterLogin_URL);
                   System.out.println(driver.getCurrentUrl());
                   Thread.sleep(500);
                   System.out.println("Login Success!");
               /*    System.out.println(driver.findElement(By.id("button-1016"))
                           .getTagName());*/
                   driver.findElement(By.id("button-1016")).click();
                   driver.close();

               } else {
                 
                   // Thread.sleep(500);
                   //如果Login失败， 判断登录失败的对话框是否出现，如果出现warning则截图保存至screenpic文件夹下
                   if (driver.findElement(By.className("toast-item-wrapper"))
                           .isEnabled()) {
                       System.out.println("login_warning is show!");
                      // ScreenShot ss = new ScreenShot();
                      // ss.cutshot(driver);
                        driver.quit();

                   }
                   // 登录成功后跳转，重新获取URL

               }
           }
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
