package org.rarible.tezos.client.tzkt.models

enum class AccountTypes(val value : String) {
    USER("user"), DELEGATE("delegate"), CONTRACT("contract"), GHOST("ghost")
}