plugins {
    kotlin("jvm") apply false
}

dependencies {
    implementation(project(":resale-core-gateway"))
    val springBootVersion = rootProject.extra["springBootVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
}