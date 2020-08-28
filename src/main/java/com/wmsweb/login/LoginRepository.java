
package com.wmsweb.login;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {
    @Modifying
    @Query(value = "insert into login(user_name,password)values(?1,?2)", nativeQuery = true)
    @Transactional
    int insertData(String user_name, String paqssword);

    @Query("select l from Login l where user_name=?1 and password=?2")
    List<Login> getLogin(String user_name, String password);
}
