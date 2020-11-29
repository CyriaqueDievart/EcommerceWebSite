package com.cecile.cyriaque.EcommerceWebSite.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddProduct implements Serializable {
    @NotEmpty(message = "Ce champ ne peux pas etre vide")
    private String name;
    @NotEmpty(message = "Ce champ ne peux pas etre vide")
    private String description;
    @NotNull
    private Long price;
    @NotNull
    private Long category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
