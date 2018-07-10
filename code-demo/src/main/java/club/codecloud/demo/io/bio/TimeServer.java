package club.codecloud.demo.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞I/O
 * @author lei
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8001;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("server start in port:" + port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("socket connected");
                new Thread(new TimeServerHandler(socket)).start();
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
