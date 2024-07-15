package com.keanghor.phoneshop_night.entity;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "model_id",
                "color_id"
        })})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @ManyToOne //Many product to one model
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @DecimalMin(value = "0.00001", message = "Price must be greater than 0")
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "image_path")
    private String imagePath;
}
