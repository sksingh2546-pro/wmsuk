// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.changeLocation;

import javax.persistence.*;

@Entity
@Table(name = "changelocation")
public class ChangeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bay_no;
    private String batch_no;
    private String sku;
    private String qty;
    private String status;
    private String old_bay;
    private String date;
    private String location_status;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBay_no() {
        return this.bay_no;
    }

    public void setBay_no(String bay_no) {
        this.bay_no = bay_no;
    }

    public String getBatch_no() {
        return this.batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
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

    public String getLocation_status() {
        return this.location_status;
    }

    public void setLocation_status(String location_status) {
        this.location_status = location_status;
    }

    public String getOld_bay() {
        return this.old_bay;
    }

    public void setOld_bay(String old_bay) {
        this.old_bay = old_bay;
    }
}
