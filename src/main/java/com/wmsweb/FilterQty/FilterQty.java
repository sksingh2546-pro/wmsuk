package com.wmsweb.FilterQty;

import javax.persistence.*;

@Entity
@Table(name = "filter_qty")
public class FilterQty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sku;
    private int qty;
    private String bay;
    private String date;
    private String line_no;
    private long batch_no;

    public long getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(long batch_no) {
        this.batch_no = batch_no;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLine_no() {
        return line_no;
    }

    public void setLine_no(String line_no) {
        this.line_no = line_no;
    }

    public String getBay() {
        return bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }
}
