package com.example.practice.Product;

import com.example.practice.Exception.ProductAlreadyPresentException;
import com.example.practice.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Validated
@RestController
@RequestMapping("/api/v2/products")
@Tag(name="Product API V2", description = "APIs for Products CRUD")
public class ProductControllerV2 {

    @Autowired
    ProductService service;

    // ---- POST ----
    @Operation(summary = "add product's")
    @ApiResponse(responseCode = "201", description = "added  Products",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "409", description = "Already product present")
    @PostMapping()
    ResponseEntity<?> addProduct(@RequestBody List<@Valid Product> product) {
        List<Product> present = service.addProduct(product);
        return ResponseHandler.generateResponse("Request OK, Product added Successfully,Check error for products failed to get added because they might already be present", HttpStatus.valueOf(201), null, present);
    }
}
