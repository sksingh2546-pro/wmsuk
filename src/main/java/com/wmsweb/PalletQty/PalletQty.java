// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.PalletQty;

import javax.persistence.*;

@Entity
@Table(name = "palletqty")
public class PalletQty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sku;
    private String pallet_qty;

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPallet_qty() {
        return this.pallet_qty;
    }

    public void setPallet_qty(String pallet_qty) {
        this.pallet_qty = pallet_qty;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
