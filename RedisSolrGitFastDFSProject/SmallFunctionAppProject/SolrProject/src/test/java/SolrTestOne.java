import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/12.
 */
public class SolrTestOne {
    /**
     * 添加数据
     */
    @Test
    public  void testAdd() throws IOException, SolrServerException {
        //按照面向对象思想
        HttpSolrClient solrClient=new HttpSolrClient.Builder().withBaseSolrUrl("http://10.9.166.173:10080/solr/collection1").build();
        SolrInputDocument document=new SolrInputDocument();
        //制定数据
        document.addField("id",1233);
        document.addField("title_ik","solrj添加的");
        document.addField("content_ik","solrtcontent");
        solrClient.add(document);
        solrClient.commit();
        solrClient.close();
    }

    /**
     * 删除数据
     */
    @Test
    public  void testDelete() throws IOException, SolrServerException {
        //按照面向对象思想
        HttpSolrClient solrClient=new HttpSolrClient.Builder().withBaseSolrUrl("http://10.9.166.173:10080/solr/collection1").build();
        solrClient.deleteById("1233");
        solrClient.commit();
        solrClient.close();
    }

    /**
     * 删除数据
     */
    @Test
    public void testDeleteAll() throws IOException, SolrServerException {

        //按照面向对象思想
        HttpSolrClient solrClient = new HttpSolrClient.Builder().withBaseSolrUrl("http://10.9.166.173:10080/solr/collection1").build();
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
        solrClient.close();
    }

    /**
     * 复杂查询
     */
    @Test
    public void fuzaquery() throws IOException, SolrServerException {
        String hl="title_ik";
        HttpSolrClient solrClient = new HttpSolrClient.Builder().withBaseSolrUrl("http://10.9.166.101:10080/solr/collection1").build();
        SolrQuery solrQuery =new SolrQuery();
        // solrQuery.setQuery("*:*");//相当于solr页面上面的q
        solrQuery.setQuery("title_ik:发生");
        //solrQuery.setFilterQueries("content_ik:111");//相当于fq
        solrQuery.setSort("id", SolrQuery.ORDER.desc);//设置sort
        //solrQuery.set("df","id");//设置默认域
        // solrQuery.set("q","*:*");//相当于solrQuery.setQuery("*:*")
        solrQuery.setStart(0);
        solrQuery.setRows(10);
        solrQuery.setHighlight(true);//开启高亮
        solrQuery.addHighlightField(hl);
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList solrDocumentList = response.getResults();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        System.out.println(highlighting);
        List<Solr> solrs = new ArrayList<>();
        for (SolrDocument document : solrDocumentList) {
            Solr solr=new Solr();
            Object id = document.get("id");
            Object content_ik = document.get("content_ik");
            Object title_ik = document.get("title_ik");
            solr.setId((String) id);
            solr.setContent((String) content_ik);
            solr.setTitle((String) title_ik);
            System.out.println(id);
            Map<String, List<String>> map = highlighting.get(id);
            List<String> list = map.get(hl);
            // String s = list.get(0);
            if (list != null) {
                System.out.println(list);
                solr.setTitle(list.get(0));
            }

            solrs.add(solr);
        }

        System.out.println(solrs);
    }

    @Test
    public void test5() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder().withBaseSolrUrl("http://10.9.166.101:10080/solr/collection1").build();
        SolrDocument document = solrClient.getById("1");
        Solr solr=new Solr();
        Object id = document.get("id");
        Object content_ik = document.get("content_ik");
        Object title_ik = document.get("title_ik");
        solr.setId((String) id);
        solr.setContent((String) content_ik);
        solr.setTitle((String) title_ik);
        System.out.println(solr);
    }

    @Test
    public void testCloud() throws IOException, SolrServerException {
        CloudSolrClient cloudSolrClient=new CloudSolrClient.Builder().withZkHost("10.9.166.101:2181,10.9.166.101:2182,10.9.166.101:2183").build();
        cloudSolrClient.setDefaultCollection("collection2");
        SolrDocument document = cloudSolrClient.getById("1");
        Solr solr=new Solr();
        Object id = document.get("id");
        Object content_ik = document.get("content_ik");
        Object title_ik = document.get("title_ik");
        solr.setId((String) id);
        solr.setContent((String) content_ik);
        solr.setTitle((String) title_ik);
        System.out.println(solr);
    }
}
