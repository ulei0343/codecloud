package club.codecloud.base.util.net;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * FTP工具类
 *
 * @author ulei
 * @date 2018/7/16
 */
public class FTPUtils {

    private static final Logger logger = LoggerFactory.getLogger(FTPClient.class);

    /**
     * 连接FTP服务器超时时间，30s
     */
    private static int CONNECT_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(30);

    /**
     * 初始化连接FTP服务器
     *
     * @param host     地址
     * @param port     端口
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    private static FTPClient init(String host, int port, String userName, String password) {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setConnectTimeout(CONNECT_TIMEOUT);
            // 连接FTP服务器
            ftpClient.connect(host, port);
            // 登录FTP服务器
            boolean isLogin = ftpClient.login(userName, password);
            if (!isLogin) {
                logger.error("connect to FTP Server failure:{}", host);
                ftpClient.disconnect();
                return null;
            }
            logger.info("connect to FTP Server success:{}", host);
            return ftpClient;
        } catch (Exception e) {
            logger.error("init FTPClient error", e);
        }
        return null;
    }

    /**
     * 从FTP服务器下载文件
     *
     * @param host     地址
     * @param port     端口
     * @param userName 用户名
     * @param password 密码
     * @param path     路径
     * @param fileName 文件名
     * @return
     */
    public static byte[] download(String host, int port, String userName, String password, String path, String fileName) {
        FTPClient ftpClient = init(host, port, userName, password);
        try {
            if (ftpClient == null) {
                throw new RuntimeException("init FTPClient error");
            }
            ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(path);
            InputStream input = ftpClient.retrieveFileStream(fileName);
            if (input == null) {
                logger.error("File does not exist:{}", fileName);
                return null;
            }
            logger.info("File download success:{}", fileName);
            return IOUtils.toByteArray(input);
        } catch (IOException e) {
            logger.error("download [{}/{}] from FTP Server[{}] error", path, fileName, host, e);
        } finally {
            if (ftpClient != null && ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error("disconnect to FTP Server error:{}", host, e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        byte[] content = FTPUtils.download("111.203.205.28", 21, "M20000001943", "W2ed3m1943", "/2018/07/", "20180709_M20000001943_TRANSACTION.txt");
        File file = new File("/Users/ulei/Desktop/ftpTest/20180709_M20000001943_TRANSACTION.txt");
        try {
            FileUtils.writeByteArrayToFile(file, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
