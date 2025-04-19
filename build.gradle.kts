plugins {
    id("org.sonarqube") version "4.3.0.3225"
}

extra["junitVersion"] = "5.10.0"
extra["junitPlatformLauncherVersion"] = "1.12.1"
extra["mockitoVersion"] = "5.10.0"
extra["lombokVersion"] = "1.18.36"
extra["springBootVersion"] = "3.4.4"
extra["assertjVersion"] = "3.27.3"
extra["slf4jVersion"] = "2.0.17"
extra["jakartaPersistenceVersion"] = "3.1.0"
extra["h2DatabaseVersion"] = "2.2.224"
extra["testcontainersVersion"] = "1.20.6"
extra["springDocOpenapiVersion"] = "2.8.6"
extra["retrySpringVersion"] = "2.0.11"
extra["logbackClassicVersion"] = "1.5.18"
extra["logbackEncoderVersion"] = "8.1"

allprojects {
    group = "com.github.matielojg"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    // Permite builds sem erro em subprojetos sem main class
    tasks.withType<Jar>().configureEach {
        enabled = true
    }

    // Plugins comuns por identificação
    plugins.withId("org.jetbrains.kotlin.jvm") {
        // Shared Kotlin config (se houver)
    }

    plugins.withId("org.springframework.boot") {
        // Shared Spring Boot config (se necessário)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "revenda-pedidos-api")
        property("sonar.projectName", "Revenda Pedidos API")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.gradle.skipCompile", "true")

        val token = System.getenv("SONAR_TOKEN") ?: System.getProperty("SONAR_TOKEN")
        if (token != null) {
            property("sonar.token", token)
        } else {
            error("Missing SONAR_TOKEN (env or -DSONAR_TOKEN)")
        }

        // Inclui main/java e main/resources (sem isso o erro persiste)
        property("sonar.sources", "src/main/java,src/main/resources")
        property("sonar.tests", "src/test/java")
        property("sonar.inclusions", "**/*.java")
        property("sonar.test.inclusions", "**/*Test.java,**/*IT.java")

        // Exclui arquivos que causam duplicação
        property("sonar.exclusions", "**/build/**,**/out/**")
    }
}




