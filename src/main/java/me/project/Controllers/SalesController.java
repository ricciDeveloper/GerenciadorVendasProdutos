package me.project.Controllers;

import me.project.Model.ClientModel;
import me.project.Model.ProductModel;
import me.project.Model.SalesModel;
import me.project.Repository.ClientRepository;
import me.project.Repository.ProductRepository;
import me.project.Service.ClientService;
import me.project.Service.ProductService;
import me.project.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class SalesController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;


    private final SaleService service;

    public SalesController(SaleService service) {
        this.service = service;
    }
    @GetMapping("/listarVendas")
    public ResponseEntity<Iterable<SalesModel>> findAllSales(){
        return ResponseEntity.ok(service.findAllSales());
    }

    @PostMapping("/criarNovaVenda")
    public ResponseEntity<SalesModel> createSale(@RequestParam Long clientId,@RequestParam List<Long> productsId){
        //find client in database
        ClientModel clientModel = clientRepository.findById(clientId)
                        .orElseThrow(() -> new RuntimeException("Client not found"));

        //find products in database
        List<ProductModel> products = productRepository.findAllById(productsId);

        if (products.isEmpty()){
            throw new RuntimeException("Not found products");
        }

        //Create this sale and associate datas.
        SalesModel sale = new SalesModel();
        sale.setClient(clientModel);
        sale.setProducts(products);

        sale.setSaleDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        service.createSale(sale);

        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @DeleteMapping("/deletarVenda")
    public ResponseEntity<Void> deleteSale (@RequestParam Long id){
        service.deleteSale(id);
        return ResponseEntity.ok().build();
    }
}
