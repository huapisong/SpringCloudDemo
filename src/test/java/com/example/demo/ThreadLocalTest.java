package com.example.demo;

public class ThreadLocalTest {
    /** 下面的例子中，创建了两个线程，然后线程对各自的局部变量进行递增的操作。每个线程中的局部变量的初始值都是100。 */
    // ThreadLocal中的值
    static class Bank {
        ThreadLocal<Integer> t = new ThreadLocal<Integer>() {
            // 重写里面的方法就可以修改初始值了
            @Override
            protected Integer initialValue(){
                return 100;
            }
        };
        public int get() {
            return t.get();
        }
        public void set() {
            // 获取值
            t.set(t.get() + 10);
        }
        public void set(Integer value) {
            // 获取值
            t.set(value);
        }
    }
    /** 定义对ThreadLocal的操作，也就是在原来的基础上进行加10的操作，然后打印出结果。 */
    // 对ThreadLocal的操作
    static class Transfer implements Runnable {
        Bank bank;
        public Transfer(Bank bank) {
            this.bank = bank;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                //
                bank.set();
                // 输出不同线程的ThreadLocal的值
                System.out.println(Thread.currentThread() + "：：：" + bank.get());
            }
        }
    }
    /** 创建两个线程并启动，主线程等待这两个线程执行完成。最值得注意的就是主线程中输出的bank.get()，输出的初始值100。 */
    // 创建线程并等待线程执行完成
    public static void main(String[] args) throws InterruptedException {
        //
        Bank bank = new Bank();
        // bank.set(50);
        // 多个线程都是同时操作一个变量，但是不同线程的结果是互不影响的
        Transfer t = new Transfer(bank);
        Thread t1 = new Thread(t);
        t1.start();
        Thread t2 = new Thread(t);
        t2.start();
        t1.join();
        t2.join();
        // 需要注意的是，这个是main（） 线程中的变量，输出的是 ThreadLocal<Integer> t 的初始值， 也就是100
        System.out.println(bank.get());

    }
}



