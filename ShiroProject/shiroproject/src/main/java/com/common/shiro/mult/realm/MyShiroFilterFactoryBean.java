package com.common.shiro.mult.realm;



import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackiechan on 18-7-27/上午9:38
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    void initMap() {
        //从数据库中查询生成keyvalue放到map
        Map<String, String> map = new HashMap<>();
        map.put("/list.jsp","authc");
        map.put("/login.jsp","anon");
        map.put("/login.action","anon");
        map.put("/logout.action", "logout");
        map.put("/**", "authc");

        setFilterChainDefinitionMap(map);
    }
}
