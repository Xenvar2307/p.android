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
    val productService = ProductService(database)

    routing {
        // Create user
        post("/products") {
            val user = call.receive<ExposedProduct>()
            val id = productService.create(user)
            call.respond(HttpStatusCode.Created, id)
        }
        // Read user
        get("/products/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val product = productService.read(id)
            if (product != null) {
                call.respond(HttpStatusCode.OK, product)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        // Update user
        put("/products/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val product = call.receive<ExposedProduct>()
            productService.update(id, product)
            call.respond(HttpStatusCode.OK)
        }
        // Delete user
        delete("/products/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            productService.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}
