package com.example.practice.Product;

import com.example.practice.Exception.ProductAlreadyPresentException;
import com.example.practice.Exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    @Autowired
    ProductRepository dao;

    // Get all Product
    List<Product> getAll() {
        return dao.findAll();
    }

    // Save product
    void addProduct(Product product) throws ProductAlreadyPresentException {
        try{
            dao.save(product);
        } catch (Exception e) {
            throw new ProductAlreadyPresentException("Product Already Present");
        }
    }

    List <Product> addProduct(List<Product> product) {
        List <Product> present = new ArrayList<>();

        for(Product p: product) {
            try{
                dao.save(p);
            } catch (Exception e) {
                present.add(p);
                //throw new ProductAlreadyPresentException("Product Already Present");
            }
            finally {
                continue;
            }
        }
        return present;
    }

    Product getById(int id) throws ProductNotFoundException {
        try{
            Product obj = dao.findById(id).get();
            return obj;
        }
        catch (Exception e) {
            throw new ProductNotFoundException("Product Not found");
        }
    }

    // Updating Product
    void updateProduct(Product p, int id) throws ProductNotFoundException, ProductAlreadyPresentException {
            Product existing = getById(id);
            //System.out.println(existing);
            existing.setName(p.getName());
            existing.setDescription(p.getDescription());
            existing.setType(p.getType());
            existing.setPrice(p.getPrice());
            try{
                dao.save(existing);
            } catch (Exception e) {
                throw new ProductAlreadyPresentException("Product you are trying to update as is already present.");
            }
    }

    void deleteProduct(int id) throws ProductNotFoundException {
        // Checking if present or not
            getById(id); // Not present exception is thrown
            dao.deleteById(id);
    }


    // ------- Handling CSV File code -----
    // Multi-threading @Async
    @Async // This is to seperate the process from the main thread
    void saveProduct(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        parseCSVFile(file);
        long end = System.currentTimeMillis();
        System.out.println("Total time: "+ (end-start));
    }

    void parseCSVFile(final MultipartFile file) throws Exception {
        final List<Product> products = new ArrayList<>();
        try {
            // Executer Service Seperate for Multi-thread handling
            System.out.println(Runtime.getRuntime().availableProcessors());
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");

                    executorService.execute(() -> {
                        Product product = new Product();
                        if (data[0].trim().length() > 0) {
                            product.setName(data[0]);
                        }
                        product.setDescription(data[1]);
                        if (data[2].trim().length() > 0) {
                            product.setType(data[2]);
                        }
                        if (data[3].trim().length() > 0) {
                            product.setPrice(data[3]);
                        }
                        //System.out.println("Thread in work: "+Thread.currentThread());
                            try {
                                dao.save(product);
                            } catch (Exception e) {
                                //e.printStackTrace();
                            }
                    });
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (final IOException e) {
            System.out.println("Failed to parse CSV file: " + e);
            throw new Exception("Failed to parse CSV file: " + e);
        }
    }

}

