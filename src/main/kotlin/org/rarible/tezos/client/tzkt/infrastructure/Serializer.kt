package org.rarible.tezos.client.tzkt.infrastructure

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.rarible.tezos.client.tzkt.models.Account
import org.rarible.tezos.client.tzkt.models.AccountTypes
import org.rarible.tezos.client.tzkt.models.Contract
import org.rarible.tezos.client.tzkt.models.Delegate
import org.rarible.tezos.client.tzkt.models.Ghost
import org.rarible.tezos.client.tzkt.models.User

object Serializer {
    @JvmStatic
    val moshiBuilder: Moshi.Builder = Moshi.Builder()
        .add(OffsetDateTimeAdapter())
        .add(LocalDateTimeAdapter())
        .add(LocalDateAdapter())
        .add(UUIDAdapter())
        .add(ByteArrayAdapter())
        .add(URIAdapter())
        .add(KotlinJsonAdapterFactory())
        .add(BigDecimalAdapter())
        .add(BigIntegerAdapter())
        .add(
            PolymorphicJsonAdapterFactory.of(Account::class.java, "type")
                .withSubtype(User::class.java, AccountTypes.USER.value)
                .withSubtype(Contract::class.java, AccountTypes.CONTRACT.value)
                .withSubtype(Delegate::class.java, AccountTypes.DELEGATE.value)
                .withSubtype(Ghost::class.java, AccountTypes.GHOST.value)
        )

    @JvmStatic
    val moshi: Moshi by lazy {
        moshiBuilder.build()
    }
}
