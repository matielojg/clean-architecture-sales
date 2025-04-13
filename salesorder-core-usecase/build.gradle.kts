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

    val junitVersion = rootProject.extra["junitVersion"] as String
    val mockitoVersion = rootProject.extra["mockitoVersion"] as String

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
}

tasks.test {
    useJUnitPlatform()
}
