
package com.wmsweb.changeLocation;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ChangeLocationRepository extends CrudRepository<ChangeLocation, Long> {
    @Modifying
    @Query(value = "insert into changelocation(bay_no,batch_no,sku,qty,status,old_bay,date,location_status)values(?1,?2,?3,?4,?5,?6,?7,0)", nativeQuery = true)
    @Transactional
    int insertData(String bay_no, String batch_no, String sku, String qty, String status, String old_bay, String date);

    @Modifying
    @Query(value = "update  changelocation set location_status=1 where id=?1", nativeQuery = true)
    @Transactional
    int updateLocationstatus(long id);

    @Query("select gs from ChangeLocation gs where id=?1")
    List<ChangeLocation> getLocationChange(long id);
}
