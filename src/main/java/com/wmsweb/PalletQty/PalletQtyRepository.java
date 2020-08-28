


package com.wmsweb.PalletQty;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PalletQtyRepository extends CrudRepository<PalletQty, Long> {
    @Modifying
    @Query(value = "insert into palletqty (sku,pallet_qty)values(?1,?2)", nativeQuery = true)
    @Transactional
    int insertData(String sku, String pallet_qty);

    @Query("select pq from PalletQty pq")
    List<PalletQty> getPalletQty();
}
