plugins {
    java
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":salesorder-core-domain"))
}
