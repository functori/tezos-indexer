package com.rarible.protocol.tezos.api.util.tzkt.db

// see: https://gist.github.com/quangIO/a623b5caa53c703e252d858f7a806919

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject

/**
 * Created by quangio.
 */

fun <T : Any> Table.jsonb(name: String, klass: Class<T>): Column<T> = registerColumn(name, JsonB(klass))


private class JsonB<out T : Any>(private val klass: Class<T>) : ColumnType() {
    override fun sqlType() = "jsonb"

    private fun valueToPGobject(value: Any?, index: Int): PGobject {
        val obj = PGobject()
        obj.type = "jsonb"
        obj.value = when (value) {
            null -> null
            else -> value as String
        }
        return obj
    }

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        val obj: PGobject = valueToPGobject(value = value, index = index)
        super.setParameter(stmt, index, obj)
    }

    /*
    override fun setParameter(stmt: PreparedStatement, index: Int, value: Any?) {
        val obj = PGobject()
        obj.type = "jsonb"
        obj.value = when(value) {
            null->null
            else->value as String
        }
        stmt.setObject(index, obj)
    }
     */

    override fun valueFromDB(value: Any): Any {
        value as PGobject
        return try {
           // jsonMapper.readValue(value.value, klass)
            value.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Can't parse JSON: $value")
        }
    }


    override fun notNullValueToDB(value: Any): Any = value.toString()
    override fun nonNullValueToString(value: Any): String = "'$value'"
}
