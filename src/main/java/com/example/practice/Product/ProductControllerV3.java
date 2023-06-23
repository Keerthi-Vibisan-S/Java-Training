package com.example.practice.Product;

import com.example.practice.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
        List<Product> p = new ArrayList<>();
        for(MultipartFile f: files) {
            try {
                service.saveProduct(f);
            } catch (Exception e) {
                return ResponseHandler.generateResponse("OOP's Error while adding data from file", HttpStatus.INTERNAL_SERVER_ERROR, "-999", "The file might contain wrong format of data");
            }
        }
        return ResponseHandler.generateResponse("Data from file added Successfully", HttpStatus.valueOf(201), "Data added", null);
    }

//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    private Job job;


//    @PostMapping("/importProducts")
//    public void importCsvToDBJob() {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
//        try {
//            jobLauncher.run(job, jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
//                 JobParametersInvalidException e) {
//            System.out.println("========================= Here =======================");
//            e.printStackTrace();
//        }
//    }
}
