package guzzi.project.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("UserDao")
public interface UserDao {

    /*
     * selectUserById - userId 로 user info 조회 (토큰 관련 로직에 연결)
     * @author 남현정
     * @param paramMap
     * @return paramMap
     * @exception SQLException
     * @exception Exception
     * */
    Map<String,Object> selectUserById(Map<String,Long> paramMap);
}
