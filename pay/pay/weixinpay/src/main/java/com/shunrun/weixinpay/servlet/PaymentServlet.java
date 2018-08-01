package com.shunrun.weixinpay.servlet;//



import com.shunrun.weixinpay.util.PayCommonUtil;
import com.shunrun.weixinpay.util.ZxingUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by jackiechan on 18-7-23/上午9:33
 */
public class PaymentServlet extends javax.servlet.http.HttpServlet {
    private Random random = new Random();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //获取要购买的商品的名字,实际开发中,用户传递过来的是一个商品的id 我们从服务器内部获取信息
        request.setCharacterEncoding("UTF-8");
        String body = request.getParameter("body");
        //价格默认1分
        String price = "1";
        ///牵扯到tomcat版本的问题,所以一定主要字符集编码问题

        String serverInfo = request.getServletContext().getServerInfo();
        System.out.println(serverInfo);
        int orderid=random.nextInt(1000000000);
        //将请求参数封装好并按照要求排序签名
        try {
            //通过网络请求请求微信下单服务器,获取二维码的字符串
            String url = PayCommonUtil.weixin_pay(price, body, orderid + "");//下单并获取微信扫描的链接地字符串
            //将字符串转成图片,放到session
            BufferedImage image = ZxingUtil.createImage(url, 300, 300);
            request.getSession().setAttribute("orderid",orderid);
            request.getSession().setAttribute("image", image);
            //跳转页面
            response.sendRedirect("/pay.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
