package com.mahavira.partnersms.storemanagement.domain.entitiy;

import com.mahavira.partnersms.inventory.domain.entity.Boardgame;

/**
 * Created by norman on 18/07/18.
 *
 */

public class ProductSelected {

    private Boardgame product;
    private boolean selected;

    public Boardgame getProduct() {
        return product;
    }

    public void setProduct(Boardgame product) {
        this.product = product;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
