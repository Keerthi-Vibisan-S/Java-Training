package com.example.practice.Product;

import com.example.practice.Exception.ProductAlreadyPresentException;
import com.example.practice.Exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List <Product> addProduct(List<Product> product) {
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
}
