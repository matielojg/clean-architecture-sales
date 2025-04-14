plugins {
    kotlin("jvm") apply false
}

dependencies {
    val springBootVersion = rootProject.extra["springBootVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
}