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
    @Query(value = "insert into bay(bay,capacity)values(?1,?2)", nativeQuery = true)
    @Transactional
    int insertBay(String bay, double capacity);

    @Modifying
    @Query(value = "update bay set capacity=?2 where bay=?1", nativeQuery = true)
    @Transactional
    int updateBay(String bay, double capacity);

    @Query("select bay from BayCapacity bay")
    List<BayCapacity> getBay();

    @Query("select b1 from BayCapacity b1 where bay=?1")
    List<BayCapacity> getBay(String bay);
}
