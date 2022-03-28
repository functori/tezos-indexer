import com.expediagroup.graphql.plugin.gradle.graphql

plugins {
    id("com.expediagroup.graphql") version "5.2.+"
}

val tezosOpenapiVersion: String by project
val raribleCommonVersion: String by project

dependencies {
    api("com.rarible.protocol.tezos:protocol-tezos-model:$tezosOpenapiVersion")
    implementation("com.rarible.core:rarible-core-starter:$raribleCommonVersion")
    implementation("com.rarible.core:rarible-core-telemetry-starter:$raribleCommonVersion")
    implementation("com.rarible.core:rarible-core-apm-starter:$raribleCommonVersion")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

//    implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
//    implementation("com.graphql-java:graphql-java-tools:5.2.4")

    implementation("com.expediagroup:graphql-kotlin-spring-client:5.3.2")
}

graphql {
    client {
        endpoint = "https://rarible-hangzhounet.dipdup.net/v1/graphql"
        packageName = "net.dipdup.rarible"
    }
}
