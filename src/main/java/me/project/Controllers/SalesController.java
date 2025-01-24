package me.project.Controllers;

import me.project.Controllers.Config.DTO.SaleRequestDTO;
import me.project.Controllers.Config.DTO.SaleResponseDTO;
import me.project.Model.ClientModel;
import me.project.Model.ProductModel;
import me.project.Model.SaleItem;
import me.project.Model.SalesModel;
import me.project.Repository.ClientRepository;
import me.project.Repository.ProductRepository;
import me.project.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO request) {
        ClientModel clientModel = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        List<SaleItem> items = new ArrayList<>();

        for (SaleRequestDTO.ItemDTO itemDTO : request.getItems()){
            System.out.println("Produto ID: " + itemDTO.getProductId() + ", Quantidade: " + itemDTO.getQuantity());
            ProductModel productModel = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found" + itemDTO.getProductId()));
            SaleItem item = new SaleItem();
            item.setProduct(productModel);
            item.setQuantity(itemDTO.getQuantity());
            item.setUniPrice(productModel.getValue_sell());
            items.add(item);
        }

        SalesModel sale = new SalesModel();

        sale.setClient(clientModel);
        sale.setItems(items);
        sale.setSaleDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        service.createSale(sale);


        //DTO of answer
        SaleResponseDTO responseDTO = new SaleResponseDTO();
        responseDTO.setClientId(sale.getId());
        responseDTO.setClientId(sale.getClient().getId());
        responseDTO.setSaleDate(sale.getSaleDate());


        List<SaleResponseDTO.ItemResponseDTO> itemResponseList = new ArrayList<>();
        for(SaleItem item: sale.getItems()){
            SaleResponseDTO.ItemResponseDTO itemDTO = new SaleResponseDTO.ItemResponseDTO();
            itemDTO.setProductId(item.getProduct().getId_product());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUniPrice(item.getUniPrice());
            itemResponseList.add(itemDTO);
        }

        responseDTO.setItems(itemResponseList);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/deletarVenda")
    public ResponseEntity<Void> deleteSale (@RequestParam Long id){
        service.deleteSale(id);
        return ResponseEntity.ok().build();
    }
}
