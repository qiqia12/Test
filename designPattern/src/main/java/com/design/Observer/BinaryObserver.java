package com.design.Observer;

/**
 * @projectName: Test
 * @package: com.design.Observer
 * @className: BinaryObserver
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:21
 * @version: 1.0
 */

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
