package com.design.Proxy;

/**
 * @projectName: Test
 * @package: com.design.Proxy
 * @className: ProxyImage
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:19
 * @version: 1.0
 */

public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

