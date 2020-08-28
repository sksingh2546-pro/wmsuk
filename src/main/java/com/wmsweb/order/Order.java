

package com.wmsweb.order;

import javax.persistence.*;

@Entity
@Table(name = "order_list")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long order_id;
    private String sku;
    private String batch_no;
    private int qty;
    private String bay;
    private String permit_no;
    private String date;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBatch_no() {
        return this.batch_no;
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

    public String getBay() {
        return this.bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPermit_no() {
        return this.permit_no;
    }

    public void setPermit_no(String permit_no) {
        this.permit_no = permit_no;
    }
}
