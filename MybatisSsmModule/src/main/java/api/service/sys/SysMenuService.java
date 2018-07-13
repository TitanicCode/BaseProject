package api.service.sys;

import api.pojo.sys.SysMenu;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */
public interface SysMenuService {
    List<SysMenu> selectAllSysMenu();
}
