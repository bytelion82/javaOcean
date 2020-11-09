package com.bytelion.detect;

import com.bytelion.detect.entity.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysqlApplicationTests {

    @Test
    void contextLoads() {
        Messages data = new Messages();
        data.setMoney(21).setName("张三");
        System.out.println(data);
    }

}
