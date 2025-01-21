package me.project.Service;

import me.project.Model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductModel> findAllProducts();
    Optional<ProductModel> findById(Long id);
    void createProduct(ProductModel product);
    void updateProduct(Long id, ProductModel product);
    void deleteProduct(Long id);


}
