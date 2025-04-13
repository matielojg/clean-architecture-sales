plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
    kotlin("plugin.spring")
    application
}

val springBootVersion = rootProject.extra["springBootVersion"] as String
val junitVersion = rootProject.extra["junitVersion"] as String
val assertjVersion = rootProject.extra["assertjVersion"] as String

dependencies {
    // Módulos do projeto
    implementation(project(":resale-core-domain"))
    implementation(project(":resale-core-usecase"))
    implementation(project(":resale-core-gateway"))
    implementation(project(":resale-adapter-repository-jpa"))
    implementation(project(":resale-adapter-gateway-validator"))
    implementation(project(":resale-adapter-gateway-api"))
    // Banco em memória
    implementation("com.h2database:h2")
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")

    // Testes de integração
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

application {
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}

tasks.test {
    useJUnitPlatform()
}
