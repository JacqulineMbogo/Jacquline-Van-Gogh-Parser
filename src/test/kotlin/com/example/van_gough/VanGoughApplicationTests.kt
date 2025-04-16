package com.example.van_gough
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class VanGoughApplicationTests {

	@Test
	fun contextLoads() {
	}

	private val parser = ResultParser()

	@Test
	fun `test parser returns result from sample HTML`() {
		val file = File("files/test.html")
		assertTrue(file.exists(), "Test HTML file should exist")

		val results = parser.parseResult(file.absolutePath)

		assertEquals(1, results.size)

		val item = results[0]
		assertEquals("Sample Name", item.name)
		assertEquals(listOf("Ext1", "Ext2"), item.extensions)
		assertEquals("http://example.com/details", item.link)
		assertEquals("http://example.com/image.jpg", item.image)
	}

	@Test
	fun `test parser throws exception when file does not exist`() {
		val invalidPath = "files/non_existent.html"

		val exception = assertThrows(IllegalArgumentException::class.java) {
			parser.parseResult(invalidPath)
		}

		assertEquals("HTML file not found: $invalidPath", exception.message)
	}


}
