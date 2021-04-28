
package com.wmsweb.model;

public class ProductionModel {
    private String sku;
    private String  batch_no;
    private int qty;
    private String status;
    private String date;

    public ProductionModel(String sku, String batch_no, int qty,  String status, String date) {
        this.sku = sku;
        this.batch_no = batch_no;
        this.qty = qty;
        this.status = status;
        this.date = date;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
