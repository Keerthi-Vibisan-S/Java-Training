package com.example.practice.Product;

import com.example.practice.ConstantStrings;
import com.example.practice.Exception.CustomException;
import com.sun.jdi.connect.spi.ClosedConnectionException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.sql.SQLTransientConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository dao;

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    // Get all Product
    List<Product> getAll() throws CustomException {
        try{
            return dao.findAll();
        }
        catch (Exception e) // Might be DB Connection failed (here we get 5 to 6 exception)
        {
            //e.printStackTrace();
            throw new CustomException("Internal Server Error", 500);
        }
    }

    // Save product
    void addProduct(Product product) throws CustomException {
        try{
            dao.save(product);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            throw new CustomException(ConstantStrings.product_already_found, 409);
        } catch (Exception e) // Might be DB Connection failed
        {
            //e.printStackTrace();
            throw new CustomException("Internal Server Error", 500);
        }
    }

    // Optimise it ---------
    List <Product> addProduct(List<Product> product) throws CustomException {
        List <Product> present = new ArrayList<>();

        for(Product p: product) {
            try{
                dao.save(p);
            } catch (DataIntegrityViolationException | ConstraintViolationException e) {
                present.add(p);
                continue;
                //throw new ProductAlreadyPresentException("Product Already Present");
            }
            catch (Exception e) // Might be DB Connection failed
            {
                //e.printStackTrace();
                throw new CustomException("Internal Server Error", 500);
            }
        }
        return present;
    }

    Product getById(int id) throws CustomException {
        try{
            return dao.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new CustomException(ConstantStrings.product_not_found, 200);
        } catch (Exception e) // Might be DB Connection failed
        {
            //e.printStackTrace();
            throw new CustomException("Internal Server Error", 500);
        }
    }

    // Updating Product
    void updateProduct(Product p, int id) throws CustomException {
            Product existing = getById(id); // Throws not found
            //System.out.println(existing);
            existing.setName(p.getName());
            existing.setDescription(p.getDescription());
            existing.setType(p.getType());
            existing.setPrice(p.getPrice());
            try{
                dao.save(existing);
            } catch (DataIntegrityViolationException e) {
                throw new CustomException(ConstantStrings.product_already_found, 409);
            } catch (Exception e) // Might be DB Connection failed
            {
                //e.printStackTrace();
                throw new CustomException("Internal Server Error", 500);
            }
    }

    void deleteProduct(int id) throws CustomException {
        // Checking if present or not
            getById(id); // Not present exception is thrown
            dao.deleteById(id);
    }


    // ----- Handling CSV File code -----
    // Multi-threading @Async
    @Async // This is to seperate the process from the main thread
    void saveProduct(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
         parseCSVFile(file);
         long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    // Using Executor Service
    private void parseCSVFile(MultipartFile file) throws CustomException {
        // Executer Service Seperate for Multi-thread handling
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                executorService.execute(() -> {
                    Product product = new Product();
                    if (data.length == 4 && !data[0].isBlank() && !data[2].isBlank() && !data[3].isBlank()) {
                        product.setName(data[0]);
                        product.setDescription(data[1]);
                        product.setType(data[2]);
                        product.setPrice(data[3]);
                    } else return;
                    // logger.info("Thread in work: ",Thread.currentThread().getName());
                    try{
                        dao.save(product);
                    } catch (ConstraintViolationException | DataIntegrityViolationException e) {
                        logger.error("Data Already Present");
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }  catch (final IOException e) {
            // System.out.println("Failed to parse CSV file: " + e);
            throw new CustomException("Failed to read CSV File", 400);
        } catch (Exception e) // Might be DB Connection failed
        {
            throw new CustomException("Internal Server Error", 500);
        }
    }

    // Data to CSV
    String generateCSV() throws CustomException {
        StringBuffer csv = new StringBuffer();
        getAll().stream().forEach(data -> {
            csv.append(data.getName()+","+data.getDescription()+","+data.getType()+","+data.getPrice()+"\n");
        });
        return csv.toString();
    }



//    String addToDb(String [] data) {
//        Product product = new Product();
//        if (data[0].trim().length() > 0) {
//            product.setName(data[0]);
//        }
//        product.setDescription(data[1]);
//        if (data[2].trim().length() > 0) {
//            product.setType(data[2]);
//        }
//        if (data[3].trim().length() > 0) {
//            product.setPrice(data[3]);
//        }
//        //System.out.println("Thread in work: "+Thread.currentThread());
//        //logger.info("Thread in work: ",Thread.currentThread());
//        try {
//            dao.save(product);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            //products.add(product);
//            logger.error("Data Already Present");
//        }
//        return "";
//    }
//
//    List<Product> parse(MultipartFile file) throws Exception {
//        final List<Product> products = new ArrayList<>();
//        List<CompletableFuture<String>> completableFutures = new ArrayList<>();
//
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
//            String line;
//            long start = System.currentTimeMillis();
//
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                CompletableFuture<String> run = CompletableFuture.supplyAsync(() -> addToDb(data));
//                completableFutures.add(run);
//            }
//            long startTime = System.currentTimeMillis();
//
//            // Wait for all CompletableFuture instances to complete
//            CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]))
//                    .join();
//
//            // Record the end time
//            long endTime = System.currentTimeMillis();
//
//            // Calculate the elapsed time
//            long elapsedTime = endTime - startTime;
//
//            System.out.println("Total time taken: " + elapsedTime + " milliseconds");
//            return products;
//
//        } catch (final IOException e) {
//            System.out.println("Failed to parse CSV file: " + e);
//            throw new Exception("Failed to parse CSV file: " + e);
//        }
//    }

}

