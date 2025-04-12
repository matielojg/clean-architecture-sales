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
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    val slf4jVersion = rootProject.extra["slf4jVersion"] as String
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
}


