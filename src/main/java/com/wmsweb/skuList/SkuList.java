
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
}
