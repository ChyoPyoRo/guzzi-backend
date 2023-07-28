package guzzi.project.mapper;

import guzzi.project.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserVO> findByUserId(String userid);

}
