package me.project.Controllers;

import me.project.Model.ProductModel;
import me.project.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductsController {
    private final ProductService service;

    public ProductsController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/buscarTodosProdutos")
    public ResponseEntity<Iterable<ProductModel>> findAllProducts(){
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<ProductModel>> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping("/salvarProduto")
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product){
        service.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel product){
        service.updateProduct(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
