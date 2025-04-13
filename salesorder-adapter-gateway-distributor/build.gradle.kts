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

    val mockitoVersion = rootProject.extra["mockitoVersion"] as String
    val junitVersion = rootProject.extra["junitVersion"] as String
    val springBootVersion = rootProject.extra["springBootVersion"] as String
    val slf4jVersion = rootProject.extra["slf4jVersion"] as String

    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
}
tasks.test {
    useJUnitPlatform()
}


