package com.wgw.payweixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/27.
 */
//你要注意这里建的文件是servlet，不是class（右键java文件夹，菜单最底下有个servlet）
//这里的name要和pay.jsp中的form action="/paymentServlet" 一致
@WebServlet(name = "paymentServlet")
public class PaymentServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //servlet就必然会涉及Tomcat，涉及Tomcat版本就必然会涉及乱码问题，所以要解决post和get乱码问题
        //解决post乱码问题（要注意这里的乱码是针对于请求方的）
        request.setCharacterEncoding("UTF-8");
        //解决get乱码问题（要注意这里的乱码是针对于请求方的）
        String serverInfo = request.getServletContext().getServerInfo();
        System.out.println(serverInfo);
        //如果serverInfo是7开头就进行get乱码解决，如果是8或者以上开头就不需要考虑get乱码问题了

        String commodityName=request.getParameter("body");
        String price="1";


    }
}
