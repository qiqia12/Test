package com.design.Observer;

/**
 * @projectName: Test
 * @package: com.design.Observer
 * @className: OctalObserver
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:21
 * @version: 1.0
 */

public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}

