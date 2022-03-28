apply(plugin = "server")

plugins {
	id("com.expediagroup.graphql") version "5.2.+"
}

val exposedVersion: String by project // TODO: should be removed
val tezosOpenapiVersion: String by project

dependencies {
	implementation(project(":core"))
	api("com.rarible.protocol.tezos:protocol-tezos-indexer-api:$tezosOpenapiVersion")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
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
	implementation("com.expediagroup:graphql-kotlin-spring-client:5.3.2")

	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.rarible.protocol.tezos:protocol-tezos-indexer-client:$tezosOpenapiVersion")
	testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
}

tasks.test {
	ignoreFailures = true
}
