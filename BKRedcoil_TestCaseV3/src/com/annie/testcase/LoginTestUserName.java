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

public class LoginTestUserName {

	private WebDriver driver;
	private String firstname, lastname, user;
	private String sid, url;

	TestNG tn = new TestNG();

	@DataProvider(name = "accounts")
	// 登录用户的账户Excel，返回的是Object数组
	public Iterator<Object[]> data() throws Exception {
		return (Iterator<Object[]>) new ExcelData("account_login", "UserName");

	}

	public void prmap(Map<String, String> arr) throws Exception {
		
	   Set<String> set = arr.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String s = (String) it.next();

			if (s.equals("firstName")) {
				firstname = arr.get(s);
			}
			else {
				lastname = arr.get(s);
			}
			user = firstname + lastname;
			if ((firstname.length() != 0) && (lastname.length() != 0)) {
				System.out.println(user);
				driver = new FirefoxDriver();
				url = "http://10.0.0.88:8080/bkredcoil/controller/security/signon?firstName="+firstname+"&"+"lastName"+lastname;

				System.out.println(url);

				driver.get(url);

				driver.manage().timeouts()
						.implicitlyWait(500, TimeUnit.SECONDS);
				Thread.sleep(500);
				driver.findElement(By.id("button-1056-btnInnerEl")).click();

				driver.close();

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
