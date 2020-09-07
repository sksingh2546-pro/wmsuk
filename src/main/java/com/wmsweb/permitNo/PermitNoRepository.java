

package com.wmsweb.permitNo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PermitNoRepository extends CrudRepository<PermitNo, Long> {
    @Modifying
    @Query(value = "insert into permit_no(order_id,permit_no)values(?1,?2)", nativeQuery = true)
    @Transactional
    int insertPermitNo(long order_id, String permit_no);

    @Query("select p.permit_no from PermitNo p where order_id=?1")
    List<String> getPermitList(long id);
    
    @Query("select p.permit_no from PermitNo p")
    List<String> getPermitList();

}
