package cn.wangkf.monday;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile  boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param ports
     */
    public NioServer(Integer[] ports) throws IOException {
        try{
            Selector selector = Selector.open(); //创建多路复用器

            // 监听多个端口
            for (int port : ports) {
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false); //设置为异步非阻塞模式

                ServerSocket serverSocket = serverSocketChannel.socket();
                // 绑定端口、注册到Selector
                InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
                serverSocket.bind(inetSocketAddress);

                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("监听端口：" + port);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run(){
        while (!this.stop) {
            try {
               selector.select(1000); //selector每隔1s都被唤醒一次
               Set<SelectionKey> selectedKeys = selector.selectedKeys();
               Iterator<SelectionKey> it = selectedKeys.iterator();
               SelectionKey key = null;
               while (it.hasNext()) {
                   key = it.next();
                   it.remove();
                   try {
                       handleInput(key);
                   } catch (Exception e) {
                       if (key !=null) {
                           key.cancel();
                           if(key.channel() !=null)
                               key.channel().close();
                       }
                   }
               }
            }catch (Throwable t) {
                t.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try{
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 处理新接入的 channel
    private void handleInput(SelectionKey key) throws IOException{
        if (!key.isValid()) {
            return;
        }

        final SocketChannel sc;
        // 判断是不是有新的客户端连接
        if (key.isAcceptable()) {
            //Accept the new connection
            ServerSocketChannel server = (ServerSocketChannel)key.channel();
            sc = server.accept();//接收客户端的连接请求，完成TCP三次握手
            sc.configureBlocking(false);//设置为异步非阻塞
            //Add the new connection to the selector
            sc.register(selector, SelectionKey.OP_READ);
        }
        // 判断是不是有可读的channel
        else if (key.isReadable()) {
            // Read the data
            sc = (SocketChannel)key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

            int count = sc.read(readBuffer);
            if (count > 0 ) {
                readBuffer.flip(); //将缓冲区当前的limit设置为position,position设置为0
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes,"UTF-8");
                System.out.println("The time server receive order :" + body);

                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                doWrite(sc, currentTime);
            } else if (count <0) {
                //对端链路关闭
                key.cancel();
                sc.close();
            } else {
                //读到0字节，忽略
            }
        }
    }

    /**
     * 将应答消息异步发送给客户端
     * @param channel
     * @param response
     * @throws IOException
     */
    private void doWrite(SocketChannel channel, String response) throws IOException{
        if (response != null && response.trim().length() >0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}