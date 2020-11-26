package com.cecile.cyriaque.EcommerceWebSite.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AddCategory implements Serializable {
    @NotEmpty(message = "Ce champ ne peux pas etre vide")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
