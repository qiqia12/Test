package com.design.Proxy;

/**
 * @projectName: Test
 * @package: com.design.Proxy
 * @className: ProxyPatternDemo
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:19
 * @version: 1.0
 */

public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}

