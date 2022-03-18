val tezosOpenapiVersion: String by project
val raribleCommonVersion: String by project

dependencies {
    api("com.rarible.protocol.tezos:protocol-tezos-model:$tezosOpenapiVersion")
    implementation("com.rarible.core:rarible-core-starter:$raribleCommonVersion")
    implementation("com.rarible.core:rarible-core-telemetry-starter:$raribleCommonVersion")
    implementation("com.rarible.core:rarible-core-apm-starter:$raribleCommonVersion")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
}
