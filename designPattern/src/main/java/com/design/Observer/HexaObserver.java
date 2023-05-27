package com.design.Observer;

/**
 * @projectName: Test
 * @package: com.design.Observer
 * @className: HexaObserver
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/27 17:21
 * @version: 1.0
 */

public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}

