package com.example.practice.Product;

import com.example.practice.Exception.ProductAlreadyPresentException;
import com.example.practice.Exception.ProductNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name="Product API", description = "APIs for Products CRUD")
public class ProductController {

    @Autowired
    ProductService service;

    // ---- GET ----
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "Found all Products",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @GetMapping()
    ResponseEntity <?> getAll()
    {
        return ResponseHandler.generateResponse("Products Data", HttpStatus.valueOf(200), service.getAll(), null);
    }

    // ---- GET BY ID ----
    @Operation(summary = "Get a single products")
    @ApiResponse(responseCode = "200", description = "Found the Products",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "200", description = "no product found")
    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable(name="id") int id) {
        try {
            return ResponseHandler.generateResponse("Data of product with Id: "+id, HttpStatus.OK, service.getById(id), null);
        } catch (ProductNotFoundException e) {
            return ResponseHandler.generateResponse("Request OK, product not found", HttpStatus.OK, null, e.getMessage());
        }
    }

    // ---- POST ----
    @Operation(summary = "add product")
    @ApiResponse(responseCode = "201", description = "added a Product",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "409", description = "Already product present")
    @PostMapping()
    ResponseEntity <?> addProduct(@RequestBody @Valid Product product) {
        try {
            service.addProduct(product);
            return ResponseHandler.generateResponse("Request OK, Product added Successfully,Check error for products failed to get added because they might already be present", HttpStatus.valueOf(201), product, null);

        } catch (ProductAlreadyPresentException e) {
            return ResponseHandler.generateResponse("You are trying to add a product which is already available", HttpStatus.valueOf(409), "-999", e.getMessage());
        }
    }

    // ---- PUT ----
    @Operation(summary = "update a product")
    @ApiResponse(responseCode = "202", description = "updated Products",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "200", description = "request ok but product not found")
    @ApiResponse(responseCode = "409", description = "product you are trying to update as is already present")
    @PutMapping("/{id}")
    ResponseEntity <?> updateProduct(@Valid @RequestBody Product product, @PathVariable(name = "id") int id) {
        try {
            service.updateProduct(product, id);
            return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.valueOf(202), product, null);
        } catch (ProductNotFoundException e) {
            return ResponseHandler.generateResponse("Request OK, product not found to be updated", HttpStatus.OK,"null", e.getMessage());
        } catch (ProductAlreadyPresentException e){
            return ResponseHandler.generateResponse("You are trying to add a product which is already available", HttpStatus.CONFLICT, "-999", e.getMessage());

        }

    }

    // ---- DELETE ----
    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "200", description = "Deleted Product",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "200", description = "Product you are trying to update is not found")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable(name="id") int id) {
        try {
            service.deleteProduct(id);
            return ResponseHandler.generateResponse("Product with id "+id+" deleted", HttpStatus.OK, "Deleted Successfully", null);
        } catch (ProductNotFoundException e) {
            return ResponseHandler.generateResponse("Request OK, product not found to be deleted", HttpStatus.OK,null, e.getMessage());
        }
    }
}
