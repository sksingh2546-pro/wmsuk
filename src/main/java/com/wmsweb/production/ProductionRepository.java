

package com.wmsweb.production;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductionRepository extends CrudRepository<Production, Long> {
    @Modifying
    @Query(value = "insert into production(expiry,qty,sku,status,barcode,p_barcode)values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    @Transactional
    int insertProduction(String expiry, int qty, String sku, String status,String barcode,String p_barcode);

    @Modifying
    @Query(value = "update production set qty=?2 where expiry=?1 and sku=?3 and status=?4 and barcode=?5 and p_barcode=?6", nativeQuery = true)
    @Transactional
    int updateProduction(String expiry, int qty, String sku, String status,String barcode,String p_barcode);

    @Query("select p from Production p where expiry=?1 and sku=?2 and status=?3 and barcode=?4 ")
    List<Production> getProductionData(String expiry, String sku, String status,String barcode);

    @Modifying
    @Query(value = "delete from production where qty=0", nativeQuery = true)
    @Transactional
    int deleteProduction();

    @Query("select p from Production p ")
    List<Production> getAllProductionData();
    
    @Query("select p.sku from Production p where status='PASS'")
    List<String> getAllStateList();

    @Query("select p from Production p where day(date)=day(CURDATE()-1) ")
    List<Production> getTodayProductionData();

 /*   @Query("select p from Production p where bay_no=?1")
    List<Production> getAllProductionData(String bayNo);
*/
    @Query("select p from Production p where sku=?1 and status=?2 order by expiry asc")
    List<Production> getProductionData(String sku, String status);

    @Query("select p.sku from Production p where sku like %?1% ")
    List<String> getAllSku(String sku);

    @Query("select sk from Production sk where sku=?1 and status=?2")
    List<Production> getQuantity(String sku, String status);

  @Query("select sk from Production sk where sku=?1 and status=?2 and barcode=?3 and expiry=?4")
    List<Production> getQuantity(String sku, String status,String barcode,String expiry);

    @Query("select p.expiry from Production p ")
    List<String> getBatchNo();

    @Query("select p from Production p where sku=?1 OR expiry=?2 OR barcode=?3 OR p_barcode=?4")
    List<Production> getSearchProduct(String sku, String expiry, String barcode,String p_barcode);

    @Query("select p from Production p")
    public List<Production> getProductionComplete();

    @Query("select p.expiry from Production p where sku=?1")
    public List<String> getBatchNo(String sku);

    @Query("select p.barcode from Production p where sku=?1 and expiry=?2")
    public List<String> getBay(String sku,String expiry);


    @Query("select SUM(p.qty) from Production p where  sku like %?1% and status='PASS'")
    int getAllQty(String sku);

    @Query("select sk from Production sk where barcode=?1 ")
    List<Production> getProductionDetails(String barcode);


}
