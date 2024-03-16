package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.beaconSimulatorPages;
import util.DriverFactory;

public class beaconSimulatorStepDefinitions {

    beaconSimulatorPages simulatorPages = new beaconSimulatorPages(DriverFactory.getDriver());

    @When("I click on Add Beacon button")
    public void clickAddNewButton(){
        simulatorPages.clickAddNewButton();
    }

    @When("I click on iBeacon option")
    public void clickiBeaconOption(){
        simulatorPages.clickiBeaconOption();
    }
    @When("I fill the form1 and save")
    public void fillTheForm1AndSave(){
        simulatorPages.fillTheForm1AndSave();
    }

    @When("I fill the form2 and save")
    public void fillTheForm2AndSave(){
        simulatorPages.fillTheForm2AndSave();
    }
    @Then("First Beacon switcher should be displayed")
    public void firstBeaconSwitcherDisplayed(){
        simulatorPages.firstBeaconSwitcherDisplayed();
    }

    @Then("Second Beacon switcher should be displayed")
    public void secondBeaconSwitcherDisplayed(){
        simulatorPages.secondBeaconSwitcherDisplayed();
    }
    @And("I start switching beacons")
    public void startSwitchingBeacons(){
        simulatorPages.startSwitchingBeacons();
    }


}
