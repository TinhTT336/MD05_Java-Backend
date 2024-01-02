package com.example.model.dto;

public class OrderResponseDTO {
    private Long id;
    private String address;
    private String phone;
    private Float totalPrice;
    private Integer status=0;
    private Long userId;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long id, String address, String phone, Float totalPrice, Integer status, Long userId) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
