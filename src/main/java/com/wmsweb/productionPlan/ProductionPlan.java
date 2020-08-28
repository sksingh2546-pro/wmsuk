// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.productionPlan;

import javax.persistence.*;

@Entity
@Table(name = "production_plan")
public class ProductionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sku;
    private long batch_no;
    private int qty;
    private String line_no;
    private String date;

    public String getLine_no() {
        return this.line_no;
    }

    public void setLine_no(String line_no) {
        this.line_no = line_no;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public long getBatch_no() {
        return this.batch_no;
    }

    public void setBatch_no(long batch_no) {
        this.batch_no = batch_no;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
