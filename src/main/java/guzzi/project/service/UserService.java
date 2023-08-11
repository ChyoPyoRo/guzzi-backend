package guzzi.project.service;

import guzzi.project.vo.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> findAllMemberBySelect();

}
