package com.common.validcode.controller;

import cn.dsna.util.images.ValidateCode;
import com.common.redis.client.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * Created by Administrator on 2018/7/17.
 */
@Controller
@RequestMapping("/code")
public class ValidCode {
    @Autowired
    private JedisClient jedisClient;


    @RequestMapping("/phonenum")
    @ResponseBody
    public String createPhoneCode(String callback,String phonenum) {
        ValidateCode validateCode=new ValidateCode(260,130,6,100);
        String code = validateCode.getCode();//生成的图片验证码上面的内容
        System.out.println(code);
        //存储到redsis
        Jedis jedis = jedisClient.getJedis();
        //jedis.auth("redis001");
        jedisClient.set(phonenum,code,jedis);
        //设置有效期，180代表180秒为3分钟
        jedis.expire(phonenum,180);
        jedis.close();
        code=callback==null?code:callback+"('"+code+"')";
        return  code;//1234

    }
    @RequestMapping("/piccode")
    public void createPicCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        ValidateCode validateCode=new ValidateCode(260,130,4,150);
        Cookie cookie=null;
        String uuid=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie1 : cookies) {//遍历传递过来的cookie
                //如果已经存在放有reidskey的cookie,则直接使用
                if (cookie1.getName().equalsIgnoreCase("suibian")) {
                    cookie=cookie1;
                    uuid=cookie1.getValue();//取出cookie中存放的uuid
                    break;
                }
            }
        }
        if (cookie == null) {//首次访问没有cookie,.则创建新的cookie
            uuid= UUID.randomUUID().toString();//生成uuid
            cookie=new Cookie("suibian",uuid );//将uuid放到cookie中,下次用户会带过来
            //设置cookie的存贮路径，当测试的时候发现cookie取不到，总为null时，要考虑到是否是这个路径出了问题
            cookie.setPath("/");
        }
        System.out.println("redis key uuid"+uuid.toString());

        Jedis jedis = jedisClient.getJedis();
        //jedis.auth("redis001");
        jedisClient.set(uuid,validateCode.getCode(),jedis);//以uuid作为key ,将验证码放到rendis中
        System.out.println(validateCode.getCode().toString());
        jedis.expire(uuid,180);
        jedis.close();
        response.addCookie(cookie);
        BufferedImage buffImg = validateCode.getBuffImg();
        ImageIO.write(buffImg,"jpg",response.getOutputStream());
        // validateCode.write();//以io的方式写出
    }
}
