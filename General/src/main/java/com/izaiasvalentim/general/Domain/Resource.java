package com.izaiasvalentim.general.Domain;

import java.util.List;

public class Resource {
    private long id;
    private String name;
    private List<Item> items;
    private String itemCode;
    private Double stock;

    public Resource(List<Item> items, Double stock) {
        this.items = items;
        this.stock = stock;
    }

    public Resource() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        if (!this.items.isEmpty()) {
            this.name = this.items.getFirst().getName();

        } else {
            // É para gerar uma exception personalizada.
            this.name = "";
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode() {
        if (!this.items.isEmpty()) {
            this.itemCode = this.items.getFirst().getCode();
        } else {
            // É para gerar uma exception personalizada.
            this.itemCode = "";
        }
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }
}

