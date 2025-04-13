plugins {
    java
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":salesorder-core-domain"))
    implementation(project(":salesorder-core-gateway"))

    val springBootVersion = rootProject.extra["springBootVersion"] as String
    val jakartaPersistenceVersion = rootProject.extra["jakartaPersistenceVersion"] as String

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("jakarta.persistence:jakarta.persistence-api:$jakartaPersistenceVersion")
}
