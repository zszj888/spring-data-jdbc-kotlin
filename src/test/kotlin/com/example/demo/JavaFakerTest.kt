package com.example.demo

import com.github.javafaker.Faker
import org.junit.jupiter.api.Test
import java.util.*

/***********************************************************************************************************************
 * Project - temporary-parking-service
 * JavaFakerTest
 * (c) Kerry Properties Limited. All rights reserved.
 *
 * @author - Sam.Zhang
 * Created Date - 2024/3/1 17:52
 **********************************************************************************************************************/
class JavaFakerTest {
    @Test
    fun test() {
        val faker = Faker(Locale.CHINESE)
        (1 .. 100).forEach { _ ->
            println(faker.address().city())
        }
    }
}