@beaconSimulator
Feature: Beacon Simulator

  Scenario: Add First Beacon
    When I click on Add Beacon button
    And I click on iBeacon option
    And I fill the form1 and save
    Then First Beacon switcher should be displayed
    When I click on Add Beacon button
    And I click on iBeacon option
    And I fill the form2 and save
    Then Second Beacon switcher should be displayed
    And I start switching beacons

