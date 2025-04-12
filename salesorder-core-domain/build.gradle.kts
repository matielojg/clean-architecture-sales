plugins {
    java
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    val junitVersion = rootProject.extra["junitVersion"] as String
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
