package guzzi.project.mapper;

import guzzi.project.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    public List<UserVO> findAllMemberBySelect();

    /*
     * selectUserById - userId 로 user info 조회 (토큰 관련 로직에 연결)
     * @author 남현정
     * @param paramMap
     * @return paramMap
     * @exception SQLException
     * @exception Exception
     * */
}


