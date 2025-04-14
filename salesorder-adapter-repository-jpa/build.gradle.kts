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
//    val testcontainersVersion = rootProject.extra["testcontainersVersion"] as String
    val assertjVersion = rootProject.extra["assertjVersion"] as String

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("jakarta.persistence:jakarta.persistence-api:$jakartaPersistenceVersion")

    val h2DatabaseVersion = rootProject.extra["h2DatabaseVersion"] as String
    testImplementation("com.h2database:h2:$h2DatabaseVersion")
    // ✅ dependências para testes de integração
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}
tasks.test {
    useJUnitPlatform()
}