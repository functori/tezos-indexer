apply(plugin = "server")

val exposedVersion: String by project

dependencies {
	implementation(project(":core"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
	implementation("com.squareup.moshi:moshi-adapters:1.12.0")
	implementation("com.squareup.okhttp3:okhttp:4.9.1")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
	implementation("org.postgresql:postgresql")
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
	ignoreFailures = true
}
