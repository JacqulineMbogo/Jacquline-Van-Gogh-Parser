package com.example.van_gough

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import java.time.Duration

import java.io.File

@SpringBootApplication
class VanGoughApplication

fun main(args: Array<String>) {
	System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver")
	runApplication<VanGoughApplication>(*args)
}
@Service
class ResultParser {
	fun parseResult(htmlFilePath: String): List<ResultItem> {
		val options = ChromeOptions()
		options.addArguments("--headless")
		options.addArguments("--no-sandbox")
		options.addArguments("--disable-dev-shm-usage")

		val driver: WebDriver = ChromeDriver(options)
		val results = mutableListOf<ResultItem>()

		try {
			val fileUrl = File(htmlFilePath).toURI().toString()

			//Validate the file before trying to parse it:
			if (!File(htmlFilePath).exists()) {
				throw IllegalArgumentException("HTML file not found: $htmlFilePath")
			}

			driver.get(fileUrl)

			// Wait for page to render
			// The `Thread.sleep(2000)` is used to wait for the page rendering. This is a blocking method that can unnecessarily delay execution, especially as the page load time may vary. Using WebDriver's explicit waits (e.g., `WebDriverWait`) is a better alternative.
			//Thread.sleep(2000)

			val wait = WebDriverWait(driver, Duration.ofSeconds(10))
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.Cz5hV > div")))

			// Find the parent
			val resultElements = driver.findElements(By.cssSelector("div.Cz5hV > div"))

			//Loop through the children
			for (element in resultElements) {
				val name = element.findElement(By.cssSelector("div.pgNMRc")).text
				val extensions = element.findElements(By.cssSelector("div.cxzHyb")).map { it.text }
				val image = element.findElement(By.cssSelector("img.taFZJe")).getAttribute("src")
				val link = element.findElement(By.cssSelector("a[href]")).getAttribute("href")


				results.add(ResultItem(name, extensions, link, image))

			}
		} finally {
			driver.quit()
		}

		return results
	}
}