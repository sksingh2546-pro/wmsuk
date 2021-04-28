package com.wmsweb.production;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StockRepo extends CrudRepository<Stock,Long> {

    @Modifying
    @Query(value = "insert into stock(sku,expiry,qty,status,date,barcode)values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    @Transactional
    int insertProduction(String sku, String expiry, int qty, String status, String date, String barcode);

    @Query("select p from Stock p where expiry=?1 and barcode=?2 and sku=?3")
    List<Stock> getProductionData(String expiry, String barcode, String sku);

    @Modifying
    @Query(value = "update stock set qty=?1 where expiry=?2 and barcode=?3 and sku=?4", nativeQuery = true)
    @Transactional
    int updateProduction(int qty, String expiry,String barcode,String sku);

}
