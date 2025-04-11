plugins {
    kotlin("jvm") apply false
}

dependencies {
    implementation(project(":core-gateway"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}
