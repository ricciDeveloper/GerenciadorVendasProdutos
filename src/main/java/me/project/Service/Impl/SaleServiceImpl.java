package me.project.Service.Impl;

import me.project.Model.SalesModel;
import me.project.Repository.SaleRepository;
import me.project.Service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository repository;

    public SaleServiceImpl(SaleRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<SalesModel> findAllSales() {
        return repository.findAll();
    }

    @Override
    public void createSale(SalesModel sale) {
        repository.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        repository.deleteById(id);
    }
}
