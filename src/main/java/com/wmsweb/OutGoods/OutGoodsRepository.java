
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
    @Query(value = "insert into outgoods (batch_no,bay_no,sku,qty,order_id)values(?1,?2,?3,?4,?5)", nativeQuery = true)
    @Transactional
    int insertData(long batch_no, String bay_no, String sku, int qty, long order_id);

    @Query("select og from OutGoods og")
    List<OutGoods> getoutGoodsData();

    @Query("select og from OutGoods og where order_id=?1")
    List<OutGoods> getOutGoodsData(long order_id);
}
