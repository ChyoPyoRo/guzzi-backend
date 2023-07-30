package guzzi.project.service;


import guzzi.project.mapper.UserMapper;
import guzzi.project.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<UserVO> findAllMemberBySelect() {
        return userMapper.findAllMemberBySelect();
    }
}
