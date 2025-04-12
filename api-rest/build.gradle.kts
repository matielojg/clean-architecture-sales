plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
    kotlin("plugin.spring")
    application
}

dependencies {
    // Projetos locais
    implementation(project(":core-domain"))
    implementation(project(":core-usecase"))
    implementation(project(":core-gateway"))
    implementation(project(":adapter-repository-jpa"))
    implementation(project(":adapter-gateway-receita"))
    implementation(project(":adapter-gateway-api"))

    // Bibliotecas externas - Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Banco em memória
    implementation("com.h2database:h2")

    // Testes
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}
