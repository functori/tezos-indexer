package org.rarible.tezos.client.tzkt.db

import org.jetbrains.exposed.sql.Database

class TzKTDBClient {

    companion object {
        lateinit var instance: TzKTDBClient
        private val dbUrl = "jdbc:postgresql://localhost:5432/tzkt_db"
        private val dbUser = "tzkt"
        private val dbPass = "qwerty"
    }

    init {
        instance = this
        Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)
    }
}