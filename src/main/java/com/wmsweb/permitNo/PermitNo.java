
package com.wmsweb.permitNo;

import javax.persistence.*;

@Entity
@Table(name = "permit_no")
public class PermitNo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long order_id;
    private String permit_no;

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

    public String getPermit_no() {
        return this.permit_no;
    }

    public void setPermit_no(String permit_no) {
        this.permit_no = permit_no;
    }
}
