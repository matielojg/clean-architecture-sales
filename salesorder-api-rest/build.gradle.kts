plugins {
    java
    id("org.springframework.boot")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":salesorder-core-usecase"))
    implementation(project(":salesorder-core-gateway"))
    implementation(project(":salesorder-core-domain"))
    implementation(project(":salesorder-adapter-repository-jpa"))
    implementation(project(":salesorder-adapter-gateway-distributor"))
    implementation(project(":salesorder-adapter-gateway-logger"))

    val springBootVersion = rootProject.extra["springBootVersion"] as String
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-aop:$springBootVersion")

    val springDocOpenapiVersion = rootProject.extra["springDocOpenapiVersion"] as String
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocOpenapiVersion")

    val logbackClassicVersion = rootProject.extra["logbackClassicVersion"] as String
    implementation("ch.qos.logback:logback-classic:$logbackClassicVersion")
    val logbackEncoderVersion = rootProject.extra["logbackEncoderVersion"] as String
    implementation("net.logstash.logback:logstash-logback-encoder:$logbackEncoderVersion")
    
    val datadogVersion = rootProject.extra["datadogVersion"] as String
    implementation("com.datadoghq:dd-trace-api:$datadogVersion")
    implementation("com.datadoghq:dd-trace-ot:$datadogVersion")
    implementation("io.micrometer:micrometer-registry-datadog:1.12.4")

    val junitVersion = rootProject.extra["junitVersion"] as String
    val assertjVersion = rootProject.extra["assertjVersion"] as String
    val mockitoVersion = rootProject.extra["mockitoVersion"] as String
    val junitPlatformLauncherVersion = rootProject.extra["junitPlatformLauncherVersion"] as String
    val h2DatabaseVersion = rootProject.extra["h2DatabaseVersion"] as String
    val postgresqlVersion = rootProject.extra["postgresqlVersion"] as String


    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformLauncherVersion")
    runtimeOnly("com.h2database:h2:$h2DatabaseVersion")
    runtimeOnly("org.postgresql:postgresql:$postgresqlVersion")

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
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = project.hasProperty("enableBootJar")
}

tasks.named<Jar>("jar") {
    enabled = true
}