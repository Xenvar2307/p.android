package com.example.models

import com.google.gson.annotations.JsonAdapter
import org.jetbrains.exposed.sql.*
import com.rnett.exposedgson.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 30)

    override val primaryKey = PrimaryKey(id,name = "PK_Products_ID")
}

@JsonAdapter(ExposedTypeAdapter::class)
class ProductsData(id: EntityID<Int>) : IntEntity(id){
    @ExposedGSON.JsonName("name")
    var name by Products.name
}
