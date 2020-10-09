
package com.wmsweb.Purchase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long order_id;
    private String permit_no;
    private String sku;
    private int qty;
    private int status;
    private String date;
    private String bay_no;
    private String batch_no;

    public long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermit_no() {
        return this.permit_no;
    }

    public void setPermit_no(String permit_no) {
        this.permit_no = permit_no;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public String getBay_no() {
        return bay_no;
    }

    public void setBay_no(String bay_no) {
        this.bay_no = bay_no;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return this.id == purchase.id && this.order_id == purchase.order_id && this.qty == purchase.qty && this.status == purchase.status && this.permit_no.equals(purchase.permit_no) && this.sku.equals(purchase.sku) && this.date.equals(purchase.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.order_id, this.permit_no, this.sku, this.qty, this.status, this.date);
    }
}
