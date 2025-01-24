package me.project.Service.Impl;

import jakarta.transaction.Transactional;
import me.project.Model.ProductModel;
import me.project.Model.SaleItem;
import me.project.Model.SalesModel;
import me.project.Repository.ProductRepository;
import me.project.Repository.SaleRepository;
import me.project.Service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository repository;
    private final ProductRepository productRepository;

    public SaleServiceImpl(SaleRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }


    @Override
    public List<SalesModel> findAllSales() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void createSale(SalesModel sale) {
        for (SaleItem item: sale.getItems()){
            ProductModel productModel = productRepository.findById(item.getProduct().getId_product())
                    .orElseThrow(()-> new RuntimeException("Product not find" + item.getProduct().getId_product()));

            //check stock
            if(productModel.getQuantity() < item.getQuantity()){
                throw new RuntimeException("not enough stock" + productModel.getName());
            }

            //update stock
            productModel.setQuantity(productModel.getQuantity() - item.getQuantity());
            productRepository.save(productModel);


            //defines the sale
            item.setSale(sale);
            item.setUniPrice(productModel.getValue_sell());
        }
        repository.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        repository.deleteById(id);
    }
}
