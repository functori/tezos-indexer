apply(plugin = "server")

val raribleCommonVersion: String by project

dependencies {
	implementation(project(":core"))
	implementation("com.rarible.core:rarible-core-starter:$raribleCommonVersion")
	implementation("com.rarible.core:rarible-core-daemon:$raribleCommonVersion")
	implementation("com.rarible.core:rarible-core-kafka:$raribleCommonVersion")
	implementation("com.rarible.core:rarible-core-telemetry-starter:$raribleCommonVersion")
	implementation("com.rarible.core:rarible-core-apm-starter:$raribleCommonVersion")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
}
