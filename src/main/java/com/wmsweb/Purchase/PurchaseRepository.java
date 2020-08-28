// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.Purchase;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    @Modifying
    @Query(value = "insert into purchase (order_id,permit_no,sku,qty,date,status)values(?1,?2,?3,?4,?5,0)", nativeQuery = true)
    @Transactional
    int insertData(long order_id, String permit_no, String sku, int qty, String date);

    @Modifying
    @Query(value = "update purchase set status=1 where order_id=?1", nativeQuery = true)
    @Transactional
    int updatePurchaseStatus(long order_id);

    @Query("select pr from Purchase pr where status=0")
    List<Purchase> getPurchaseDetails();

    @Query("select pr from Purchase pr where status=0 and sku=?1 and order_id=?2")
    List<Purchase> getPurchaseDetails(String sku, long order_id);

    @Query("select pr from Purchase pr where order_id=?1")
    List<Purchase> getPurchaseList(long order_id);

    @Query("select pr from Purchase pr where order_id=?1")
    List<Purchase> getQuantity(long order_id);

    @Modifying
    @Query(value = "update purchase set status=3 where order_id=?1", nativeQuery = true)
    @Transactional
    int changeStatusWithOrderid(long order_id);
}
