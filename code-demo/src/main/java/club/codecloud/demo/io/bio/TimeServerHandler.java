package club.codecloud.demo.io.bio;

import club.codecloud.base.util.time.DateFormatUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);

            while (true) {
                String body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("the body:" + body);
                String currentTime = "Time".equalsIgnoreCase(body) ? DateFormatUtils.formatDate(DateFormatUtils.DATE_TIME_FORMAT, new Date()) : "Error";
                System.out.println("response:" + currentTime);
                out.println(currentTime);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (this.socket != null) {
                try {
                    System.out.println("socket closed");
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
