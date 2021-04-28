

package com.wmsweb.OutGoods;

import javax.persistence.*;

@Entity
@Table(name = "outgoods")
public class OutGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String batch_no;
    private String barcode;
    private String sku;
    private int qty;
    private long order_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }


}
