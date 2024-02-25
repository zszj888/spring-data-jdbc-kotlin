package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class DemoApplication {
//    @Bean
//    fun init(repository: ContactRepository): ApplicationRunner {
//        return ApplicationRunner {
//            repository.save(
//                Contact(
//                    "John", "Doe", "1234567890", Status("OK"), Company("Company 1",null,)
//                )
//            )
//        }
//    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

//
//@Route("")
//internal class MainView : VerticalLayout() {
//    init {
//        val label = Span("My label")
//        label.isVisible = false
//
//        label.text = "Changed my label"
//
//        val makeVisible = Button("Make visible") {
//            label.isVisible = true
//        }
//		val grid = Grid<User>()
//		val add = Button("Add") {
//			{  }
//		}
//        add(H1("Hello, world"), makeVisible, add)
//    }
//
//}
//
//data class User(val name: String, val age: Int)