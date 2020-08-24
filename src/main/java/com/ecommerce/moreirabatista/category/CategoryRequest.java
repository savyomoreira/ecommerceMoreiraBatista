package com.ecommerce.moreirabatista.category;

import org.springframework.lang.NonNull;

public class CategoryRequest {
    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
