package me.project.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;


    @OneToMany(mappedBy = "client")
    @JsonIgnore  // 🔥 Impede que a API retorne a lista de vendas no JSON
    private List<SalesModel> sales;
    public Long getId() {
        return id;
    }
    public List<SalesModel> getSales() {
        return sales;
    }

    public void setSales(List<SalesModel> sales) {
        this.sales = sales;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
