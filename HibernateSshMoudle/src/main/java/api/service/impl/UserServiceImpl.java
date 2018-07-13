package api.service.impl;


import api.dao.UserDao;
import api.pojo.User;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public List<User> queryUserList() {
        List<User> userList = userDao.queryUserList();
        return userList;
    }
}
