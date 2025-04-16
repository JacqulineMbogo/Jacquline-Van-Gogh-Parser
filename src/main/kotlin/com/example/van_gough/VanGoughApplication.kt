package com.example.van_gough

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File

@SpringBootApplication
class VanGoughApplication

fun main(args: Array<String>) {
	System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH") ?: "/usr/bin/chromedriver")
	runApplication<VanGoughApplication>(*args)
}
@Service
class ResultParser {
	fun parseResult(htmlFilePath: String): List<ResultItem> {

		val chromeVersion = ProcessBuilder("google-chrome", "--version")
			.start()
			.inputStream.bufferedReader().readText()
		println("Chrome version: $chromeVersion")

		val options = ChromeOptions()
		options.addArguments("--headless")
		options.addArguments("--no-sandbox")
		options.addArguments("--disable-dev-shm-usage")
		options.addArguments("--disable-gpu")
		options.addArguments("--remote-allow-origins=*")
		val driver: WebDriver = ChromeDriver(options)
		val results = mutableListOf<ResultItem>()

		try {
			val fileUrl = File(htmlFilePath).toURI().toString()
			driver.get(fileUrl)

			// Wait for page to render
			Thread.sleep(2000)

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