
plugins {
    kotlin("jvm") apply false
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Projetos locais
    implementation(project(":resale-core-domain"))
    implementation(project(":resale-core-gateway"))

    // Bibliotecas externas
    val springBootVersion = rootProject.extra["springBootVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")

    val lombokVersion = rootProject.extra["lombokVersion"] as String
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
