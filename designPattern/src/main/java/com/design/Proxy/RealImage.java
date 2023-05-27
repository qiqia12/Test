package com.design.Proxy;

/**
 * @projectName: Test
 * @package: com.design.Proxy
 * @className: RealImage
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:19
 * @version: 1.0
 */

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}
