
package com.wmsweb.model;

import java.util.List;

public class PermitListModel {
    private long order_id;
    private List<String> permit_list;

    public PermitListModel(long order_id, List<String> permit_list) {
        this.order_id = order_id;
        this.permit_list = permit_list;
    }

    public long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public List<String> getPermit_list() {
        return this.permit_list;
    }

    public void setPermit_list(List<String> permit_list) {
        this.permit_list = permit_list;
    }
}
