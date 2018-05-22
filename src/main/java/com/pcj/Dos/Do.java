package com.pcj.Dos;

import java.io.IOException;
/**不让主线程死亡 wait只有在同步块里才会生效
 * */
public class Do {
    public static void main(String[] args)  {
        System.out.println(1);
        synchronized (Do.class){
            while (true){
                try {
                    Do.class.wait();
                    System.out.println(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
