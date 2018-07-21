
import com.customer.common.pojo.User;
import com.customer.common.reflect.ReflectUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/17.
 */
public class TestReflect {
    @Test
    public  void test1() throws Exception {

        ReflectUtil reflectUtil=new ReflectUtil();

        Map<String, String[]> map = new HashMap<>();
        map.put("name",new String[]{"zhangsan","lisi"});

        map.put("password",new String[]{"123456"});
        map.put("hobby",new String[]{"123456","dasdasd"});
        map.put("age",new String[]{"18"});
        map.put("abc",new String[]{"18","22"});
        map.put("birthday",new String[]{"2000-07-12"});
        //ReflectUtil.registConvert(Date.class,new MyDateConvert());
        //User user = ReflectUtil.convertData(map, User.class);
        //System.out.println(user);
        User user = reflectUtil.reflectUser(map, User.class);
        System.out.println(user);
    }
}
