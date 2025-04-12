plugins {
    java
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // JUnit 5
    val junitVersion = rootProject.extra["junitVersion"] as String
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
