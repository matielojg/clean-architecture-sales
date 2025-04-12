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
    implementation(project(":salesorder-core-domain"))

    val springBootVersion = rootProject.extra["springBootVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")

    val junitVersion = rootProject.extra["junitVersion"] as String
    val assertjVersion = rootProject.extra["assertjVersion"] as String
    val mockitoVersion = rootProject.extra["mockitoVersion"] as String

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
