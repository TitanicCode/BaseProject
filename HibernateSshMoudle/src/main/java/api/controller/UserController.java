package api.controller;


import api.dto.R;
import api.pojo.User;
import api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/queryUserList",method= RequestMethod.GET)
    public R queryUserList(){
        List<User> userList = userService.queryUserList();
        return R.create().put(userList);
    }
}
