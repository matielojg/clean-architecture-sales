plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    application
}

dependencies {
    implementation(project(":core-domain"))
    implementation(project(":core-usecase"))
    implementation(project(":core-gateway"))
    implementation(project(":adapter-repository-jpa"))
    implementation(project(":adapter-gateway-receita"))
    implementation(project(":adapter-gateway-api"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.h2database:h2")
}

application {
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true // Habilita novamente só aqui
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}
