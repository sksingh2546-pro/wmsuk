
package com.wmsweb.model;

public class OrderDetailsModel {
    private long order_id;
    private String vehicle_no;
    private String party_name;
    private String weight;
    private String state;
    private int quantity;
    private int status;

    public OrderDetailsModel(long order_id, String vehicle_no, String party_name, String weight, String state, int quantity, int status) {
        this.order_id = order_id;
        this.vehicle_no = vehicle_no;
        this.party_name = party_name;
        this.weight = weight;
        this.state = state;
        this.quantity = quantity;
        this.status = status;
    }

    public long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getVehicle_no() {
        return this.vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getParty_name() {
        return this.party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
