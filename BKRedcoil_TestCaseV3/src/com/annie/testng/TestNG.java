package com.annie.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



;

public class TestNG {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeTest
	public void setupBrowser(String browser) {
		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {

			driver = new ChromeDriver();
		}

	}

	@Parameters({ "url", "keyword" })
	@Test
	public void search(String url, String keyword) {
		driver.get(url);
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys(keyword);
		element.submit();
		Assert.assertTrue(driver.getTitle().contains(keyword),
				"Title is  wrong!");
	}

	// 传入登录用户名、密码，获取元素，验证空用户/密码登录
	@Parameters({ "url", "login_name_txtUserName", "login_name_txtPwd",
			"login_name_btnLogin" })
	// 参数： URL、用户名name、密码name、登陆按钮name
	@Test(groups = { "login" })
	public void login_username_empty(String login_name_txtUserName,
			String login_name_txtPwd, String login_name_btnLogin)
			throws Exception {
		System.out.print("login_username_empty");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name(login_name_txtUserName)).clear();
		driver.findElement(By.name(login_name_txtPwd)).clear();
		driver.findElement(By.name(login_name_txtUserName)).sendKeys("");
		driver.findElement(By.name(login_name_txtPwd)).sendKeys("");
		driver.quit();
	}


}
