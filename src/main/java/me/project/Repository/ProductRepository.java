package me.project.Repository;

import me.project.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long > {

}
