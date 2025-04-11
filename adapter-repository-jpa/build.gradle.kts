plugins {
    kotlin("jvm") apply false
}

dependencies {
    implementation(project(":core-domain"))
    implementation(project(":core-gateway"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
