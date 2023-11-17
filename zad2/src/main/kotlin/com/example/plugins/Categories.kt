package com.example.plugins

import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import kotlinx.serialization.Serializable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*

@Serializable
data class ExposedCategory(val id: Int,val title: String, val popularity: Int, val producent : String)

class CategoryService(private val database: Database) {
    object Categories : Table() {
        val id = integer("id").uniqueIndex() //unsure if that works
        val title = varchar("title", length = 50)
        val popularity = integer("popularity")
        val producent = varchar("producent", length = 50)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Categories)

            Categories.insert{
                it[id] = 1
                it[title] = "Sportowe"
                it[popularity] = 13
                it[producent] = "Wilson"
            }


        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun create(category: ExposedCategory): Int = dbQuery {
        Categories.insert {
            it[id] = Categories.id
            it[title] = Categories.title
            it[popularity] = Categories.popularity
            it[producent] = Categories.producent
        }[Categories.id]
    }

    suspend fun read(id: Int): ExposedCategory? {
        return dbQuery {
            Categories.select { Categories.id eq id }
                .map { ExposedCategory(it[Categories.id],it[Categories.title], it[Categories.popularity], it[Categories.producent]) }
                .singleOrNull()
        }
    }

    suspend fun readAll(): List<ExposedCategory> {
        return dbQuery { CategoryService.Categories.selectAll().map(::resultRowToCategory) }
    }

    private fun resultRowToCategory(row: ResultRow) = ExposedCategory(
        id = row[CategoryService.Categories.id],
        title = row[CategoryService.Categories.title],
        popularity = row[CategoryService.Categories.popularity],
        producent = row[CategoryService.Categories.producent]

    )

    suspend fun update(id: Int, category: ExposedCategory) {
        dbQuery {
            Categories.update({ Categories.id eq id }) {
                it[Categories.id] = Categories.id
                it[title] = Categories.title
                it[popularity] = Categories.popularity
                it[producent] = Categories.producent
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            Categories.deleteWhere { Categories.id.eq(id) }
        }
    }
}
