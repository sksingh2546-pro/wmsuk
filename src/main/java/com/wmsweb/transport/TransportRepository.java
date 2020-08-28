// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.transport;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface TransportRepository extends CrudRepository<Transport, Long> {
    @Query("select t.driver_name from Transport t")
    Set<String> getDriverName();

    @Query("select t.contact_no from Transport t")
    Set<String> getContactNo();

    @Query("select t.vehicle_no from Transport t")
    Set<String> getVehicleNo();

    @Query("select t.party_name from Transport t")
    Set<String> getPartyName();

    @Query("select t.address from Transport t")
    Set<String> getAddress();

    @Query("select t from Transport t order by date desc")
    List<Transport> getTransportData();

    @Query("select t from Transport t where day(date)=day(curdate()-1)")
    List<Transport> getAllTransportData();

    @Query("select t from Transport t where order_id=?1")
    List<Transport> getTransportStatus(long oder_id);

    @Modifying
    @Query(value = "update transport set status=?2 where order_id=?1", nativeQuery = true)
    @Transactional
    int updateStatus(long order_id, int status);

    @Query("select t from Transport t where date between ?1 and ?2 ")
    List<Transport> getOrder(String from, String to);

    @Modifying
    @Query(value = "update transport set status=3 where order_id=?1", nativeQuery = true)
    @Transactional
    int changeTransportStatusWithOrderId(long order_id);

    @Modifying
    @Query(value = "update transport set status=1 where order_id=?1", nativeQuery = true)
    @Transactional
    int updateStatus(long order_id);

    @Modifying
    @Query(value = "update transport set status=4 where order_id=?1", nativeQuery = true)
    @Transactional
    int cancelOrderNotRunning(long order_id);

    @Query("select ts from Transport ts where status!=2 and status!=4")
    List<Transport> getOrderId();

    @Modifying
    @Query(value = "update transport set total_qty=?2,total_weight=?3 where order_id=?1", nativeQuery = true)
    @Transactional
    public int updatetransport(long order_id, String total_Qty, String total_weight);

}
