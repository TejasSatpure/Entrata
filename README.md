# Automated Testing with Selenium, Java, and Maven

This repository contains automated tests written in Java using Selenium and Maven for testing the Entrata website.

## Setup Instructions

1. Clone this repository to your local machine.
2. Make sure you have Java and Maven installed.
3. Download the appropriate WebDriver for your browser and set the path in the `setUp()` method of `EntrataTest.java`.
4. Run the tests using the command `mvn test`.

## Test Cases

- `testEntrataWebsite()`: This test case navigates to the Entrata website, fills in a form, verifies elements on the page, and checks for error messages.

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven

