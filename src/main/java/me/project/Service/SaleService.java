package me.project.Service;


import me.project.Model.SalesModel;


import java.util.List;


public interface SaleService {
    List<SalesModel> findAllSales();
    void createSale(SalesModel sale);
    void deleteSale(Long id);
}
