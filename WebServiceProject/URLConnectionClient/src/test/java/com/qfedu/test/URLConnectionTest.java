package com.qfedu.test;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by helen on 2018/6/30
 */
public class URLConnectionTest {

    @Test
    public void test() throws Exception {


        URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");

        URLConnection urlConnection = url.openConnection();

        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;

        //打开输入输出开关
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        //设置请求方式
        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

        String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getDatabaseInfo xmlns=\"http://WebXml.com.cn/\" />\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";


        OutputStream out = httpURLConnection.getOutputStream();
        out.write(data.getBytes());

        if(httpURLConnection.getResponseCode() == 200){

            InputStream in = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            StringBuffer sb = new StringBuffer();
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line);
            }

            //得到响应结果
            System.out.println(sb.toString());


            //使用dom4j解析
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new StringReader(sb.toString()));

            /*List<Element> elements = doc.getRootElement()
                    .element("Body")
                    .element("getDatabaseInfoResponse")
                    .element("getDatabaseInfoResult")
                    .elements();

            System.out.println(elements);

            for (Element element : elements) {
                String text = element.getText();
                System.out.println(text);
            }*/

            List<Node> list = doc.selectNodes("//string");
            for (Node node : list) {
                System.out.println(node.getText());
            }


        }
    }
}
