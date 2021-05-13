

package com.wmsweb.skuList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SkuListRepository extends CrudRepository<SkuList, Long> {
    @Modifying
    @Query(value = "insert into sku(sku,cases_of_pallets,pallet_weight,p_barcode,date,expiry_date)values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    @Transactional
    int insertSku(String sku, double cases_of_pallets, double pallet_weight, String p_barcode, String date, String expiry_date);

    @Modifying
    @Query(value = "update sku set cases_of_pallets=?2, pallet_weight=?3 where sku=?1 ", nativeQuery = true)
    @Transactional
    int updateSku(String sku, double cases_of_pallets, double pallet_weight);

    @Query("select sk from SkuList sk ")
    List<SkuList> getSkuList();

    @Query("select sk from SkuList sk where sku=?1 and p_barcode=?2 ")
    List<SkuList> getSkuList(String sku,String p_barcode);
@Query("select sk from SkuList sk where sku=?1")
    List<SkuList> getSkuList(String sku);

    @Query("select sk from SkuList sk where p_barcode=?1 ")
    List<SkuList> getSkuListWithBarcode(String p_barcode);

    @Query("select sk.pallet_weight from SkuList sk where sku=?1")
    @Transactional
    public List<String> getpalletWeight(String sku);

    @Query("select sk.sku from SkuList sk")
    List<String> getAllSku();
}
