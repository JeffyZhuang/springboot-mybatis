package mail;

import com.zzh.SpringbootApplication;
import com.zzh.dto.Mail;
import com.zzh.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author: zzh
 * @Description:邮件测试类
 * @Date: 2019/1/24
 */
@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MailTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSend() throws Exception {
        Mail mail = new Mail();
        mail.setEmail("270225738@qq.com");
        mail.setSubject("JeffyZhuang激活邮件");
        mail.setContent("这是一个激活邮件");
        mailService.send(mail);
    }
}