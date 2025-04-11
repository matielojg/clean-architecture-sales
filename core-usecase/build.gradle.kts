extra["junitVersion"] = "5.10.0"
extra["mockitoVersion"] = "5.10.0"

plugins {
    java
}

dependencies {
    implementation(project(":core-domain"))
    implementation(project(":core-gateway"))

    testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.extra["junitVersion"]}")
    testImplementation("org.mockito:mockito-core:${rootProject.extra["mockitoVersion"]}")
}
tasks.test {
    useJUnitPlatform()
}
