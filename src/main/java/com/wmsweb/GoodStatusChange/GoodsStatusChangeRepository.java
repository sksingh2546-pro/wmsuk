

package com.wmsweb.GoodStatusChange;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GoodsStatusChangeRepository extends CrudRepository<GoodsStatusChange, Long> {
    @Modifying
    @Query(value = "insert into goodsstatuschange (bay_no,batch_no,qty,sku,status,good_status)values(?1,?2,?3,?4,?5,0)", nativeQuery = true)
    @Transactional
    int insertData(String bay_no, String batch_no, String qty, String sku, String status);

    @Modifying
    @Query(value = "update  goodsstatuschange set good_status=1 where id=?1", nativeQuery = true)
    @Transactional
    int updateGoodstatus(long id);

    @Query("select gs from GoodsStatusChange gs where good_status=0")
    List<GoodsStatusChange> getGoodStatusChange();
}
