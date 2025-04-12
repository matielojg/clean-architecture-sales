plugins {
    java
}

dependencies {
    implementation(project(":resale-core-domain"))
    implementation(project(":resale-core-gateway"))

    testImplementation("org.junit.jupiter:junit-jupiter:${rootProject.extra["junitVersion"]}")
    testImplementation("org.mockito:mockito-core:${rootProject.extra["mockitoVersion"]}")
}
tasks.test {
    useJUnitPlatform()
}
