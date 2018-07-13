package api.dao;

import api.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> queryUserList();
}
