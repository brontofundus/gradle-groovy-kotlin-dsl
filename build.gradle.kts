import com.palantir.gradle.docker.DockerExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	val kotlinVersion = "1.2.71"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	jacoco
	id("org.springframework.boot") version "2.1.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.6.RELEASE"
	id("com.palantir.docker") version "0.21.0"
}

repositories {
	mavenCentral()
}

val version = System.getenv("BUILD_NUMBER") ?: "9.0.0-SNAPSHOT"

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion
println("Kotlin Version of Plugin: $kotlinVersion")

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
	implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.micrometer:micrometer-registry-prometheus")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.5")

	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
	testImplementation("com.natpryce:hamkrest:1.6.0.0")
}

val jacocoExecFileName = project.file("$buildDir/jacoco/test.exec")

tasks.test {
	extensions.configure(JacocoTaskExtension::class.java) {
		setDestinationFile(jacocoExecFileName)
		includes = mutableListOf("ch.brontofundus.service.example.*")
	}
}

task("printJacacoInfoForTeamcity") {
	doLast {
		val classpath = "+:build/classes/kotlin/main/**"
		val includes = "ch.brontofundus.service.example.*"
		println("##teamcity[jacocoReport dataPath='$jacocoExecFileName' includes='$includes' classpath='$classpath']")
	}
}

tasks.compileKotlin {
	kotlinOptions {
		jvmTarget = "1.8"
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}

tasks.compileTestKotlin {
	kotlinOptions {
		jvmTarget = "1.8"
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}

val bootJar: BootJar by tasks
//bootJar.baseName = "brontofundus-example-service"
//bootJar.version = "0.0.1"

configure<DockerExtension> {
	name = "brontofundus-example-service:$version"
	tag("latest", "brontofundus-example-service:latest")
	files(bootJar.outputs)
	buildArgs(mapOf("JARFILE" to bootJar.archiveFileName.get(), "VERSION" to version))
	dependsOn(tasks["build"])
}
