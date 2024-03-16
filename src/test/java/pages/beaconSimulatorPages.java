package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class beaconSimulatorPages {
    AppiumDriver driver;
    WebDriverWait wait;
    ElementHelper elementHelper;

    public beaconSimulatorPages(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.elementHelper = new ElementHelper(driver);
    }

    String uuid = "AAAAAAA0-BBB0-CCC0-DDD0-431E50385F1D";
    String beacon1Major = "1";
    String beacon1Minor = "3047";
    String beacon2Major = "1";
    String beacon2Minor = "3049";
    String addNewButton = "//android.widget.ImageButton[@resource-id='net.alea.beaconsimulator:id/main_fab_shared']";
    String iBeaconOption = "//android.widget.TextView[@resource-id='net.alea.beaconsimulator:id/newbeacon_textview_ibeacon']";
    String beaconNameInput = "//android.widget.EditText[@resource-id='net.alea.beaconsimulator:id/beaconedit_textinput_name']";
    String uuidInput = "//android.widget.EditText[@resource-id='net.alea.beaconsimulator:id/cardibeacon_textinput_uuid']";
    String majorInput = "//android.widget.EditText[@resource-id='net.alea.beaconsimulator:id/cardibeacon_textinput_major']";
    String minorInput = "//android.widget.EditText[@resource-id='net.alea.beaconsimulator:id/cardibeacon_textinput_minor']";
    String saveBeaconButton = "//android.widget.TextView[@resource-id='net.alea.beaconsimulator:id/action_save']";
    String goBackButton = "//android.widget.ImageButton[@content-desc='Navigate up']";
    String firstBeaconSwitcherDisabled = "(//android.widget.LinearLayout)[4]//android.widget.Switch[@resource-id='net.alea.beaconsimulator:id/savedbeacon_switch_broadcast' and @text='Off']";
    String firstBeaconSwitcherEnabled = "(//android.widget.LinearLayout)[4]//android.widget.Switch[@resource-id='net.alea.beaconsimulator:id/savedbeacon_switch_broadcast' and @text='On']";
    String secondBeaconSwitcherDisabled = "(//android.widget.LinearLayout)[6]//android.widget.Switch[@resource-id='net.alea.beaconsimulator:id/savedbeacon_switch_broadcast' and @text='Off']";
    String secondBeaconSwitcherEnabled = "(//android.widget.LinearLayout)[6]//android.widget.Switch[@resource-id='net.alea.beaconsimulator:id/savedbeacon_switch_broadcast' and @text='On']";

    public void clickAddNewButton(){
        driver.findElementByXPath(addNewButton).click();
    }

    public void clickiBeaconOption(){
        driver.findElementByXPath(iBeaconOption).click();
    }

    public void fillTheForm1AndSave(){
        driver.findElementByXPath(beaconNameInput).sendKeys("inside geofence");
        driver.findElementByXPath(uuidInput).clear();
        driver.findElementByXPath(uuidInput).sendKeys(uuid);
        driver.findElementByXPath(majorInput).clear();
        driver.findElementByXPath(majorInput).sendKeys(beacon1Major);
        driver.findElementByXPath(minorInput).clear();
        driver.findElementByXPath(minorInput).sendKeys(beacon1Minor);
        driver.findElementByXPath(saveBeaconButton).click();
        driver.findElementByXPath(goBackButton).click();
    }

    public void firstBeaconSwitcherDisplayed(){
        MobileElement beacon1Switcher = (MobileElement) driver.findElement(MobileBy.xpath(firstBeaconSwitcherDisabled));
        Assert.assertTrue(beacon1Switcher.isDisplayed());
    }

    public void secondBeaconSwitcherDisplayed(){
        MobileElement beacon2Switcher = (MobileElement) driver.findElement(MobileBy.xpath(secondBeaconSwitcherDisabled));
        Assert.assertTrue(beacon2Switcher.isDisplayed());
    }

    public void fillTheForm2AndSave(){
        driver.findElementByXPath(beaconNameInput).sendKeys("outside geofence");
        driver.findElementByXPath(uuidInput).clear();
        driver.findElementByXPath(uuidInput).sendKeys(uuid);
        driver.findElementByXPath(majorInput).clear();
        driver.findElementByXPath(majorInput).sendKeys(beacon2Major);
        driver.findElementByXPath(minorInput).clear();
        driver.findElementByXPath(minorInput).sendKeys(beacon2Minor);
        driver.findElementByXPath(saveBeaconButton).click();
        driver.findElementByXPath(goBackButton).click();
    }

    public void startSwitchingBeacons(){
        driver.findElementByXPath(firstBeaconSwitcherDisabled).click();
        MobileElement beacon1Enabled = (MobileElement) driver.findElement(MobileBy.xpath(firstBeaconSwitcherEnabled));
        Assert.assertTrue(beacon1Enabled.isDisplayed());
        while(true){
            EnableSecondBeacon();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            EnableFirstBeacon();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void EnableFirstBeacon(){
        driver.findElementByXPath(secondBeaconSwitcherEnabled).click();
        MobileElement beacon2Disabled = (MobileElement) driver.findElement(MobileBy.xpath(secondBeaconSwitcherDisabled));
        Assert.assertTrue(beacon2Disabled.isDisplayed());

        driver.findElementByXPath(firstBeaconSwitcherDisabled).click();
        MobileElement beacon1Enabled = (MobileElement) driver.findElement(MobileBy.xpath(firstBeaconSwitcherEnabled));
        Assert.assertTrue(beacon1Enabled.isDisplayed());
    }

    public void EnableSecondBeacon(){
        driver.findElementByXPath(firstBeaconSwitcherEnabled).click();
        MobileElement beacon1Disabled = (MobileElement) driver.findElement(MobileBy.xpath(firstBeaconSwitcherDisabled));
        Assert.assertTrue(beacon1Disabled.isDisplayed());

        driver.findElementByXPath(secondBeaconSwitcherDisabled).click();
        MobileElement beacon2Enabled = (MobileElement) driver.findElement(MobileBy.xpath(secondBeaconSwitcherEnabled));
        Assert.assertTrue(beacon2Enabled.isDisplayed());
    }
}

