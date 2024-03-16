package util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    static AppiumDriver driver;
    static Properties properties;
    static DesiredCapabilities capabilities;

    public static AppiumDriver initialize_Driver(String browser) {
        properties = ConfigReader.getProperties();

        capabilities = new DesiredCapabilities();
        if (browser.equals("Android")) {
            //beacons simulator
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("udid", "06157df63a4f472e");
            capabilities.setCapability("appPackage", "net.alea.beaconsimulator");
            capabilities.setCapability("appActivity", "net.alea.beaconsimulator.ActivityMain");

        } else if (browser.equals("iOS")) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("udid", "");
            capabilities.setCapability("appPackage", "");
            capabilities.setCapability("appActivity", "");
        }
        try {
            driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        int impWait = Integer.parseInt(properties.getProperty("implicityWait"));
        driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
        return getDriver();
    }

    public static AppiumDriver getDriver() {
        return driver;
    }


}