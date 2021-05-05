// 
// Decompiled by Procyon v0.5.36
// 

package com.wmsweb.bayCapacity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BayCapacityRepository extends CrudRepository<BayCapacity, Long> {
    @Modifying
    @Query(value = "insert into bay(bay,capacity,barcode)values(?1,?2,?3)", nativeQuery = true)
    @Transactional
    int insertBay(String bay, double capacity, int  barcode);

    @Modifying
    @Query(value = "update bay set capacity=?2, bay=?1 where barcode=?3", nativeQuery = true)
    @Transactional
    int updateBay(String bay, double capacity, int barcode);

    @Query("select bay from BayCapacity bay")
    List<BayCapacity> getBay();

    @Query("select b1 from BayCapacity b1 where barcode=?1")
    List<BayCapacity> getBay(int barcode);
}
