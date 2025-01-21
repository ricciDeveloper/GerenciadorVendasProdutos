package me.project.Service.Impl;

import me.project.Model.ProductModel;
import me.project.Repository.ProductRepository;
import me.project.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    //Injeção do repository via construtor
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<ProductModel> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<ProductModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void createProduct(ProductModel product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductModel product) {
        Optional<ProductModel> productAtt = repository.findById(id);
        if (productAtt.isPresent()) {
            repository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
