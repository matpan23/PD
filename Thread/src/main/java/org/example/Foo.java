package org.example;



public class Foo {
    int bar = 0;

    public static void main(String args[]){
        (new Foo()).unsafeCall();
    }
    void unsafeCall() {
        final Foo thisObj = this;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                thisObj.bar = 1;
            }
        };
        Thread t = new Thread(r);
        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bar= " + bar);
    }
}
