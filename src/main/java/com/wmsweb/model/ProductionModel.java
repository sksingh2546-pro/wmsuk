
package com.wmsweb.model;

public class ProductionModel {
    private String sku;
    private long batch_no;
    private int qty;
    private String bay_no;
    private String status;
    private String date;

    public ProductionModel(String sku, long batch_no, int qty, String bay_no, String status, String date) {
        this.sku = sku;
        this.batch_no = batch_no;
        this.qty = qty;
        this.bay_no = bay_no;
        this.status = status;
        this.date = date;
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

    public String getBay_no() {
        return this.bay_no;
    }

    public void setBay_no(String bay_no) {
        this.bay_no = bay_no;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
