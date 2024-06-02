package com.example.springbootcustomermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data // Hỗ trợ phần tạo Constructor và các phương thức getter / setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    private String gender;
    private String address;
}