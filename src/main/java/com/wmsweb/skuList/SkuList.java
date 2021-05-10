
package com.wmsweb.skuList;

import javax.persistence.*;

@Entity
@Table(name = "sku")
public class SkuList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sku;
    private double cases_of_pallets;
    private double pallet_weight;
    private String p_barcode;
    private String date;
    private String expiry_date;

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

    public double getCases_of_pallets() {
        return this.cases_of_pallets;
    }

    public void setCases_of_pallets(double cases_of_pallets) {
        this.cases_of_pallets = cases_of_pallets;
    }

    public double getPallet_weight() {
        return pallet_weight;
    }

    public void setPallet_weight(double pallet_weight) {
        this.pallet_weight = pallet_weight;
    }

    public String getP_barcode() {
        return p_barcode;
    }

    public void setP_barcode(String p_barcode) {
        this.p_barcode = p_barcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

}
