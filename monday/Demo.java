package cn.wangkf.monday;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {

    class Node<V> {

        private V value;

        private V next;

        private V prev;

    }

    private Node node;

    private HashMap map;

    public static void main(String[] args)  {

        LinkedList a = new LinkedList();
        a.add("a");

        a.remove("a");

        String str = new String("1sadfdsf");
        WeakReference<String> weakReferenceStr = new WeakReference<String>(str);
        String strRef = weakReferenceStr.get();

        WeakHashMap weakHashMap = new WeakHashMap();


        CopyOnWriteArrayList<String> zz = new CopyOnWriteArrayList<>();
        a.add("a");
        a.remove("a");

        ConcurrentLinkedQueue<String> b = new ConcurrentLinkedQueue<>();
        b.add("a");
        b.remove("a");

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("a");



        ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("父类数据:threadLocal");
        inheritableThreadLocal.set("父类数据:inheritableThreadLocal");


        ReentrantLock  e = new ReentrantLock();


        e.lock();



        HashMap<String, String> v = new HashMap<>();
        v.put("a", "b");


    }

    public static void nioTest() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        String fileName = "E://data//test1.txt";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("字节数:" + transferCount + ", 耗时:" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }

    public static void bioTest() throws IOException {
        Socket socket = new Socket("localhost", 889);

        String fileName = "E://data//test1.txt";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("字节数:" + total + ", 耗时:" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }


}
