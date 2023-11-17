package com.example.plugins

import com.example.models.Products
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import com.rnett.exposedgson.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

    }
}

fun Application.customerRouters()
{
    routing {
        products()
    }
}

fun Route.products(){
    get("/products"){
        val products = Products.selectAll()
        call.respond(products)
    }

    post("/addproduct"){
        val product = call.receive<Products>()
        call.respond(product)
    }
}
