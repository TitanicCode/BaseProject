import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2018/7/12.
 */
public class FastDFSProject {
    @Test
    public void test1() throws IOException, MyException {
        ClientGlobal.init("/home/jackiechan/Documents/ideaworkspace/fastdfsdemo/src/main/resources/trackerserver.properties");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer=null;
        StorageClient storageClient=new StorageClient(trackerServer,storageServer);
        String[] strings = storageClient.upload_file("/home/jackiechan/Desktop/背景2.jpg", "jpg", null);
        for (String string : strings) {

            System.out.println(string);
        }
    }
}
