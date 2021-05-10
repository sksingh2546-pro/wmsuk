
package com.wmsweb.OutGoods;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OutGoodsRepository extends CrudRepository<OutGoods, Long> {
    @Modifying
    @Query(value = "insert into outgoods (batch_no,barcode,sku,qty,order_id,expiry,p_barcode)values(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    @Transactional
    int insertData(String batch_no, String barcode, String sku, int qty, long order_id,String expiry,String p_barcode);

    @Query("select og from OutGoods og")
    List<OutGoods> getOutGoodsData();

    @Query("select og from OutGoods og where order_id=?1")
    List<OutGoods> getOutGoodsData(long order_id);
}
