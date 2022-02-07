package org.rarible.tezos.client.tzkt.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TzKTDBClient {

    companion object {
        lateinit var instance: TzKTDBClient
        private val dbUrl = "jdbc:postgresql://localhost:5432/tzkt_db"
        private val dbUser = "tzkt"
        private val dbPass = "qwerty"
        var database = Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)
    }

    init {
        instance = this
    }

    fun main(args: Array<String>) {
        //an example connection to H2 DB
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create (Cities)

            // insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
            val stPeteId = Cities.insert {
                it[name] = "St. Petersburg"
            } get Cities.id

            // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
            println("Cities: ${Cities.selectAll()}")
        }
    }

    object Cities: IntIdTable() {
        val name = varchar("name", 50)
    }
}