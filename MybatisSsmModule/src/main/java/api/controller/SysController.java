package api.controller;

import api.pojo.sys.SysMenu;
import api.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */
@RequestMapping("api")
@Controller
public class SysController {
    @Autowired
    private SysMenuService sysMenuService;
    @ResponseBody
    @RequestMapping("sys/selectAllSysMenu")
    public List<SysMenu> selectAllSysMenu(){
        return sysMenuService.selectAllSysMenu();
    }
}
