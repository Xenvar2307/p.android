package com.example.plugins

import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import kotlinx.serialization.Serializable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*

@Serializable
data class ExposedProduct(val id: Int,val title: String, val quantity: Int, val price : Double)

class ProductService(private val database: Database) {
    object Products : Table() {
        val id = integer("id").autoIncrement()
        val title = varchar("title", length = 50)
        val quantity = integer("quantity")
        val price = double("price")

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Products)
            Products.insert{
                it[title] = "Piłka"
                it[quantity] = 58
                it[price] = 34.50
            }

            Products.insert{
                it[title] = "Szalik"
                it[quantity] = 120
                it[price] = 23.99
            }
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun create(user: ExposedProduct): Int = dbQuery {
        Products.insert {
            it[title] = Products.title
            it[quantity] = Products.quantity
            it[price] = Products.price
        }[Products.id]
    }

    suspend fun read(id: Int): ExposedProduct? {
        return dbQuery {
            Products.select { Products.id eq id }
                .map { ExposedProduct(it[Products.id],it[Products.title], it[Products.quantity], it[Products.price]) }
                .singleOrNull()
        }
    }

    suspend fun update(id: Int, user: ExposedProduct) {
        dbQuery {
            Products.update({ Products.id eq id }) {
                it[title] = Products.title
                it[quantity] = Products.quantity
                it[price] = Products.price
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            Products.deleteWhere { Products.id.eq(id) }
        }
    }
}