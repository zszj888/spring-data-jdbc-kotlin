package com.example.demo

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	lateinit var repository: PlainContactRepository
	@Autowired
	lateinit var repository1: ContactRepository
	@Test
	fun contextLoads() {
		val contact = Contact(null,
			RandomStringUtils.randomAlphabetic(4), RandomStringUtils.randomAlphabetic(4),
			RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(2) + ".com",
			Status("ok111"),Company("Google",null)
		)
		val save = repository1.save(contact)
		println("Saved: $save")
		assert(save.id != null)
	}

	private fun extracted() {
		val contact = PlainContact(
			null,
			RandomStringUtils.randomAlphabetic(4), RandomStringUtils.randomAlphabetic(4),
			RandomStringUtils.randomAlphabetic(4) + "@" + RandomStringUtils.randomAlphabetic(2) + ".com"
		)
		val save = repository.save(contact)
		println("Saved: $save")
		assert(save.id != null)
	}

}
