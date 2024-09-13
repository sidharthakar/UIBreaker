UIBreaker
Java Selenium TestNG Framework
This is a Java-based automation testing framework that uses Selenium WebDriver and TestNG. The framework is designed to run UI tests and generate detailed test reports using Allure. It supports capturing screenshots for both passed and failed test cases and includes custom annotations for test case identification and categorization.

Features
Selenium WebDriver: Automates browser actions for UI testing.
TestNG: Provides flexible test configuration and execution.
Allure Reporting: Generates rich and interactive test reports.
Screenshot Capture: Automatically captures screenshots for both passed and failed test cases.
Custom Annotations: Includes @TestCaseID and @TestCaseType annotations for better test case management.
Setup
Prerequisites
Java: Ensure that Java 8 or higher is installed.
Maven: Ensure that Maven is installed and configured.
Allure: Install Allure Commandline to generate and view reports locally.
Installation
Clone this repository to your local machine.
git clone https://github.com/your-repo/selenium-testng-framework.git
cd selenium-testng-framework
Key Points Covered:
Project Description: Describes what the framework is and what it does.
Setup Instructions: Explains how to set up the project locally.
Test Execution: Instructions for running tests with Maven.
Allure Reporting: Steps to generate and view Allure reports locally.
Custom Annotations: Explanation of the custom annotations provided in the framework.
Screenshot Capture: Notes on how screenshots are captured and included in reports.
Folder Structure: Overview of the project's folder structure.
Contributing: Guidelines for contributing to the project.
License: Notes on the licensing of the project.
Run Command
mvn test -Denv=env -Dbrowser=browser -DsuiteXml=suites/xmlname.xml