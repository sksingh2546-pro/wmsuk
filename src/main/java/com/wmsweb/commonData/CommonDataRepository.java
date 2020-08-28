// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.commonData;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommonDataRepository extends CrudRepository<CommonData, Long> {
    @Modifying
    @Query(value = "insert into common_data(order_id,type,priority,date,status)values(?1,?2,?3,?4,0)", nativeQuery = true)
    @Transactional
    public int insertData(long order_id, String type, String priority, String date);

    @Modifying
    @Query(value = "update  common_data set status=?2 where id=?1", nativeQuery = true)
    @Transactional
    public int updateCommonDataStatus(long id, int status);

    @Modifying
    @Query(value = "update  common_data set status=4 where id=?1", nativeQuery = true)
    @Transactional
    public int cancelOrder(long id);

    @Query("select cd from CommonData cd where status !=2 and status!=4")
    public List<CommonData> getCommonData();
}
