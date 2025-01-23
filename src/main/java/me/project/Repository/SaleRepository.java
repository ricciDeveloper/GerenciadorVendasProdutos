package me.project.Repository;

import me.project.Model.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SalesModel, Long> {

}
