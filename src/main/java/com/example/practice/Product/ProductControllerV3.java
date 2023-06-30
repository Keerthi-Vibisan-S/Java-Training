package com.example.practice.Product;

import com.example.practice.ConstantStrings;
import com.example.practice.Exception.CustomException;
import com.example.practice.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v3/products")
@Tag(name="Product API V3", description = "APIs for Products CRUD")
class ProductControllerV3 {

    @Autowired
    ProductService service;

    // ---- POST ----
    @Operation(summary = "add product's via CSV files, multiple csv files supported")
    @ApiResponse(responseCode = "201", description = "added  Products",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "500", description = "Error in server while processing file")
    @PostMapping(value = "")
    ResponseEntity<?> saveData(@RequestParam(value = "files") MultipartFile [] files) {
//        System.out.println(files.length);
        for(MultipartFile f: files) {
            try {
                service.saveProduct(f);
            } catch (CustomException e) {
                return ResponseHandler.generateResponse("OOP's Error while adding data from file", HttpStatus.valueOf(e.getStatus_code()), "-999", e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseHandler.generateResponse("Data from file added Successfully", HttpStatus.valueOf(201), "Data added", null);
    }

    @Operation(summary = "Export data via CSV File")
    @ApiResponse(responseCode = "200", description = "Data Exported",
            content = {@Content(mediaType = "MultiPartFile", schema = @Schema(implementation = Product.class))})
    @ApiResponse(responseCode = "500", description = "Error in server while processing file")
    @GetMapping("")
    ResponseEntity<?> getCSV() {
        try {
            byte [] csv = service.generateCSV().getBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("attachment", "data.csv");
            return ResponseEntity.ok().headers(headers).body(csv);
        } catch (CustomException e) {
            return ResponseHandler.generateResponse(ConstantStrings.SERVER_ERROR, HttpStatus.valueOf(e.getStatus_code()), "-999", e.getMessage());
        }


    }
}
