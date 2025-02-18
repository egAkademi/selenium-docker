package com.gkhnsit.tests;

import com.gkhnsit.listener.TestListener;
import com.gkhnsit.util.Config;
import com.gkhnsit.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(TestListener.class)
public abstract class AbstractTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setUpConfig(){
        Config.initialize();
    }

    @BeforeTest
    //@Parameters({"browser"})
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        //WebDriverManager.chromedriver().setup();
        //this.driver = new ChromeDriver();
        this.driver=Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER,this.driver);
//        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
//            this.driver = getRemoteDriver();
//        } else {
//            this.driver = getLocalDriver();
//
//        }
    }

//        if (Boolean.getBoolean("selenium.grid.enabled")){
//            this.driver=getRemoteDriver(browser);
//        }else {
//            this.driver=getLocalDriver();
//
//        }


    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities =new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities=new FirefoxOptions();
        }
        //if(System.getProperty("browser").equalsIgnoreCase("chrome")){
//        if(browser.equalsIgnoreCase("chrome")){
//            capabilities=new ChromeOptions();
//        }else {
//            capabilities=new FirefoxOptions();
//        }

        String urlFormat=Config.get(Constants.GRID_URL_FORMAT);
        String hubHost=Config.get(Constants.GRID_HUB_HOST);
        String url=String.format(urlFormat,hubHost);
        log.info("grid hub url: "+url);
        return new RemoteWebDriver(new URL(url),capabilities);

        //return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
    }

    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
