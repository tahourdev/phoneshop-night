package com.keanghor.phoneshop_night.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Phone shop API",
                description = "Doing CRUD Operation",
                summary = "This phone shop api will add, delete, create and update",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Enghour",
                        email = "enghourh5@gmail.com"
                ),
                license = @License(
                        name = "Your license number"
                ),
                version = "v1"
        )
)
public class OpenApiConfig {
}
