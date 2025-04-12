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

include("core-domain")
include("core-usecase")
include("core-gateway")
include("adapter-repository-jpa")
include("adapter-gateway-receita")
include("adapter-gateway-api")
include("api-rest")
