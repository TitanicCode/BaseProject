package api.service;

import api.pojo.User;

import java.util.List;

public interface UserService {

    //当业务层添加方法后，要去spring-service.xml中看一下事务设置是否合适，进行相应的添加删除事务的配置
    List<User> queryUserList();

}
