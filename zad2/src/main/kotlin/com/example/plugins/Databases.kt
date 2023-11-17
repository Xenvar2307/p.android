package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    val categoryService = CategoryService(database)
    val productService = ProductService(database)

    routing {
        // Create product
        post("/products") {
            val product = call.receive<ExposedProduct>()
            val id = productService.create(product)
            call.respond(HttpStatusCode.Created, id)
        }
        //Read all products
        get("/products") {
            val products = productService.readAll()
            call.respond(HttpStatusCode.OK, mapOf("Products" to products))

        }
        // Read product
        get("/products/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val product = productService.read(id)
            if (product != null) {
                call.respond(HttpStatusCode.OK, product)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        // Update product
        put("/products/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val product = call.receive<ExposedProduct>()
            productService.update(id, product)
            call.respond(HttpStatusCode.OK)
        }
        // Delete product
        delete("/products/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            productService.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }

    routing {
        // Create category
        post("/categories") {
            val category = call.receive<ExposedCategory>()
            val id = categoryService.create(category)
            call.respond(HttpStatusCode.Created, id)
        }
        //Read all categories
        get("/categories") {
            val categories = categoryService.readAll()
            call.respond(HttpStatusCode.OK, mapOf("Categories" to categories))

        }
        // Read category
        get("/categories/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val category = categoryService.read(id)
            if (category != null) {
                call.respond(HttpStatusCode.OK, category)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        // Update category
        put("/categories/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val category = call.receive<ExposedCategory>()
            categoryService.update(id, category)
            call.respond(HttpStatusCode.OK)
        }
        // Delete category
        delete("/categories/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            categoryService.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }

}
