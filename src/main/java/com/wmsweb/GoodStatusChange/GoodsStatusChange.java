

package com.wmsweb.GoodStatusChange;

import javax.persistence.*;

@Entity
@Table(name = "goodsstatuschange")
public class GoodsStatusChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bay_no;
    private String batch_no;
    private String qty;
    private String sku;
    private String status;
    private String good_status;

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

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGood_status() {
        return this.good_status;
    }

    public void setGood_status(String good_status) {
        this.good_status = good_status;
    }
}
