package club.codecloud.demo.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 伪异步I/O
 * @author lei
 */
public class TimeServerPool {

    public static void main(String[] args) {
        int port = 8001;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("server start in port:" + port);
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
            while (true) {
                Socket socket = server.accept();
                System.out.println("socket connected");
                threadPoolExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
