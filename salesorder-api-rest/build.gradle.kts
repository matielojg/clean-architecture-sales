plugins {
    java
    id("org.springframework.boot")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":salesorder-core-usecase"))
    implementation(project(":salesorder-core-gateway"))
    implementation(project(":salesorder-core-domain"))
    implementation(project(":salesorder-adapter-repository-jpa"))
    implementation(project(":salesorder-adapter-gateway-distributor"))
    implementation(project(":salesorder-adapter-gateway-logger"))

    val springBootVersion = rootProject.extra["springBootVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")

    val springDocOpenapiVersion = rootProject.extra["springDocOpenapiVersion"] as String
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocOpenapiVersion")


    val junitVersion = rootProject.extra["junitVersion"] as String
    val assertjVersion = rootProject.extra["assertjVersion"] as String
    val mockitoVersion = rootProject.extra["mockitoVersion"] as String
    val junitPlatformLauncherVersion = rootProject.extra["junitPlatformLauncherVersion"] as String
    val h2DatabaseVersion = rootProject.extra["h2DatabaseVersion"] as String

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformLauncherVersion")
    runtimeOnly("com.h2database:h2:$h2DatabaseVersion")



}

tasks.test {
    useJUnitPlatform()
}
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}