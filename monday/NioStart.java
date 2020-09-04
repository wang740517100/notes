package cn.wangkf.monday;

import cn.wangkf.tuesday.String;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by stanley.wang on 2020/6/2.
 *
 * todo nio2(aio)
 *
 */
public class NioStart {
    public static void main(String[] args) throws IOException {
        // 启动服务端
        Integer[] ports = Arrays.asList(5000, 5001, 5002, 5003, 5004)
                .toArray(new Integer[5]);
        NioServer server = new NioServer(ports);
        new Thread(server,"NioServer-001").start();

        // 启动客户端
        int port = 5000;
        NioClient client = new NioClient("127.0.0.1", port);
        new Thread(client,"NioClient-001").start();

    }
}
