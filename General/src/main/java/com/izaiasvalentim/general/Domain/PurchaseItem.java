package com.izaiasvalentim.general.Domain;

import java.util.UUID;

public class PurchaseItem {

    private UUID id;
    private Resource item;
    private Purchase purchase;
    private long quantity;

    public PurchaseItem(Resource item, long quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public PurchaseItem() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Resource getItem() {
        return item;
    }

    public void setItem(Resource item) {
        this.item = item;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}

