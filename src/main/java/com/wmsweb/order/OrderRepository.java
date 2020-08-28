

package com.wmsweb.order;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Modifying
    @Query(value = "insert into order_list(sku,order_id,batch_no,qty,bay,date,permit_no)values(?1,?2,?3.?4,?5,?6,?7)", nativeQuery = true)
    @Transactional
    int insertOrderID(String sku, long order_id, String batch_no, int qty, String bay, String date, String permit_no);
}
