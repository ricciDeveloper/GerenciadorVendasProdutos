package me.project.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;
    private String name;
    private double value_buy;
    private double value_sell;
    private int quantity;
    private double percent;
    //Getters and setters


    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue_buy() {
        return value_buy;
    }

    public void setValue_buy(double value_buy) {
        this.value_buy = value_buy;
    }

    public double getValue_sell() {
        return value_sell;
    }

    public void setValue_sell(double value_sell) {
        this.value_sell = value_sell;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
