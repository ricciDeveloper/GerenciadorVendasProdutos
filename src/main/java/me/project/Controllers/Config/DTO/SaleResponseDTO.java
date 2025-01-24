package me.project.Controllers.Config.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class SaleResponseDTO {
    private Long id;
    private Long clientId;
    private List<ItemResponseDTO> items;
    private LocalDateTime saleDate;

    public static class ItemResponseDTO{
        private Long productId;
        private int quantity;
        private double uniPrice;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getUniPrice() {
            return uniPrice;
        }

        public void setUniPrice(double uniPrice) {
            this.uniPrice = uniPrice;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<ItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemResponseDTO> items) {
        this.items = items;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
}
