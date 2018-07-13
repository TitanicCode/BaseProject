package api.service.impl.sys;

import api.dao.sys.SysMenuMapper;
import api.pojo.sys.SysMenu;
import api.service.sys.SysMenuService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public List<SysMenu> selectAllSysMenu() {
        List<SysMenu> sysMenus = sysMenuMapper.selectByExample(null);
        return sysMenus;
    }
}
