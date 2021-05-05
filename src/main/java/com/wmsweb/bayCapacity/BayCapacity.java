

package com.wmsweb.bayCapacity;

import javax.persistence.*;

@Entity
@Table(name = "bay")
public class BayCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bay;
    private double capacity;
    private  int barcode;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBay() {
        return this.bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }
}
