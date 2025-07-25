plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
    kotlin("plugin.spring")
    application
}

val springBootVersion = rootProject.extra["springBootVersion"] as String
val junitVersion = rootProject.extra["junitVersion"] as String
val assertjVersion = rootProject.extra["assertjVersion"] as String

dependencies {
    // Módulos do projeto
    implementation(project(":resale-core-domain"))
    implementation(project(":resale-core-usecase"))
    implementation(project(":resale-core-gateway"))
    implementation(project(":resale-adapter-repository-jpa"))
    implementation(project(":resale-adapter-gateway-validator"))
    implementation(project(":resale-adapter-gateway-api"))
    // Banco em memória
    implementation("com.h2database:h2")
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-aop:$springBootVersion")

    val springDocOpenapiVersion = rootProject.extra["springDocOpenapiVersion"] as String
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocOpenapiVersion")
    
    // DataDog
    val datadogVersion = rootProject.extra["datadogVersion"] as String
    implementation("com.datadoghq:dd-trace-api:$datadogVersion")
    implementation("com.datadoghq:dd-trace-ot:$datadogVersion")
    implementation("io.micrometer:micrometer-registry-datadog:1.12.4")
    
    // Logback
    val logbackClassicVersion = rootProject.extra["logbackClassicVersion"] as String
    implementation("ch.qos.logback:logback-classic:$logbackClassicVersion")
    val logbackEncoderVersion = rootProject.extra["logbackEncoderVersion"] as String
    implementation("net.logstash.logback:logstash-logback-encoder:$logbackEncoderVersion")

    // Testes de integração
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

application {
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = project.hasProperty("enableBootJar") || true
    mainClass.set("com.github.matielojg.revenda.RevendaPedidosApiApplication")
}

tasks.test {
    useJUnitPlatform()
    // Desabilita o agente DataDog durante os testes
    systemProperty("dd.jmxfetch.enabled", "false")
    systemProperty("dd.trace.enabled", "false")
    systemProperty("dd.profiling.enabled", "false")
    // Desabilita detecção de cgroup para evitar CgroupV2Subsystem issues
    systemProperty("jdk.containerized", "false")
    jvmArgs(
        "-Ddd.agent.host=", 
        "-Ddd.agent.port=0", 
        "-Djdk.containerized=false",
        "-XX:-UseContainerSupport",
        "-Djdk.cgroup.enabled=false"
    )
}
