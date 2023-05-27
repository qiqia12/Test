package com.qiqi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.security.krb5.internal.crypto.Des;

/**
 * @projectName: Test
 * @package: com.qiqi
 * @className: DesignApplication
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 10:30
 * @version: 1.0
 */

@SpringBootApplication
@MapperScan("com.qiqi.mapper")
public class DesignApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesignApplication.class,args);
    }
}
