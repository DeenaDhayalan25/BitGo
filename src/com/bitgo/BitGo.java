package com.bitgo;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class BitGo {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		try {
			driver.get(
					"https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf6420347336450917732");

			WebElement transactionHeading = driver.findElement(By.xpath("//h2[contains(text(), 'Transactions')]"));
			String expectedText = "25 of 2875 Transactions";
			String actualText = transactionHeading.getText();

			if (expectedText.equals(actualText)) {
				System.out.println("Test Case 1 Passed: Correct heading is displayed.");
			} else {
				System.out.println("Test Case 1 Failed: Incorrect heading. Found: " + actualText);
			}

			List<WebElement> transactions = driver.findElements(By.cssSelector("div.transaction"));

			for (WebElement transaction : transactions) {
				String transactionHash = transaction.findElement(By.cssSelector("a.hash-link")).getText();

				String inputsText = transaction.findElement(By.cssSelector("span.inputs span")).getText();
				String outputsText = transaction.findElement(By.cssSelector("span.outputs span")).getText();

				int numInputs = Integer.parseInt(inputsText);
				int numOutputs = Integer.parseInt(outputsText);

				if (numInputs == 1 && numOutputs == 2) {
					System.out.println("Transaction with 1 input and 2 outputs found: " + transactionHash);
				}
			}

		} finally {
			driver.quit();
		}

	}
}