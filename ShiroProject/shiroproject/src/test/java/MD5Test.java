import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2018/8/1.
 */
public class MD5Test {
    public static void main(String[] args) {
        String password = "1234";
        String salt = "admin";
        ByteSource source = ByteSource.Util.bytes(salt);
        ByteSource byteSource = source;
        String algottName = "MD5";
        SimpleHash md5 = new SimpleHash(algottName, password, source, 1024);
        System.out.println(md5);
    }
}
