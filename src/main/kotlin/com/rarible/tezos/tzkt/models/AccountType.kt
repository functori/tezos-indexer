package com.rarible.tezos.tzkt.models

enum class AccountType(val displayName : String) {
    USER("user"),
    DELEGATE("delegate"),
    CONTRACT("contract"),
    GHOST("ghost")
}