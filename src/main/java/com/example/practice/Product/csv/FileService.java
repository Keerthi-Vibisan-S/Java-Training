package com.example.practice.Product.csv;

import com.example.practice.Product.Product;
import com.example.practice.Product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Autowired
    ProductService obj;

    Logger logger = LoggerFactory.getLogger(FileService.class);

    @Async
    public List<Product> saveProduct(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List <Product> products = parseCSVFile(file);
        System.out.println("saving list of products of size {}"+ products.size()+ ""+Thread.currentThread());
        products = obj.addProduct(products);
        long end = System.currentTimeMillis();
        System.out.println("Total time: "+ (end-start));
        return(products);
    }

    private List<Product> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Product> products = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String [] data = line.split(",");
                    Product product = new Product();
                    product.setName(data[0]);
                    product.setDescription(data[1]);
                    product.setType(data[2]);
                    product.setPrice(data[3]);
                    products.add(product);
                }
                return products;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
