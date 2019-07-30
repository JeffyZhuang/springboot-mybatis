import com.zzh.SpringbootApplication;
import com.zzh.util.CryptUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2019/3/28
 */
@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CipherTest {
    @Test
    public void testSend() throws Exception {
        CryptUtils util = new CryptUtils("abcdefghijklmnop"); // 密钥
        System.out.println("cardNo:"+util.encryptData("1234")); // 加密
        System.out.println("exp:"+util.decryptData("34+Jzs4KkwaCQWVyyAgwLA==")); // 解密
    }
}
