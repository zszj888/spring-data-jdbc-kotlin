import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.9"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.vaadin") version "24.3.5"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenLocal()
	mavenCentral()
	maven { url = uri("https://maven.vaadin.com/vaadin-addons") }
}

extra["vaadinVersion"] = "24.3.5"

dependencies {
	implementation("org.instancio:instancio-core:4.3.2")
	implementation("org.vaadin.crudui:crudui:7.1.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("com.vaadin:vaadin-spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
//	runtimeOnly("io.micrometer:micrometer-registry-dynatrace")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mysql")
}

dependencyManagement {
	imports {
		mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-tiny:latest")
}
