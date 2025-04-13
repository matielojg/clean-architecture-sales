pluginManagement {
    plugins {
        id("org.springframework.boot") version "3.4.4" apply false
        id("io.spring.dependency-management") version "1.1.4" apply false
        kotlin("jvm") version "1.9.22" apply false
        kotlin("plugin.spring") version "1.9.22" apply false
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "revenda-pedidos-api"

include(
    "resale-core-domain",
    "resale-core-usecase",
    "resale-core-gateway",
    "resale-adapter-repository-jpa",
    "resale-adapter-gateway-validator",
    "resale-adapter-gateway-api",
    "resale-api-rest",


    "salesorder-core-domain",
    "salesorder-core-gateway",
    "salesorder-core-usecase",
    "salesorder-adapter-repository-jpa",
    "salesorder-adapter-gateway-distributor",
    "salesorder-api-rest",
    "salesorder-adapter-gateway-logger"

)
