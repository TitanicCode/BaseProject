package com.shunrun.weixinpay.servlet;//



import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by jackiechan on 18-7-23/上午9:57
 */
//这里没有配置访问入口，是因为在xml文件中配置完了
public class imageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage image= (BufferedImage) request.getSession().getAttribute("image");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
