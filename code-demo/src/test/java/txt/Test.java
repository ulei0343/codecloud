package txt;

import club.codecloud.base.util.net.HttpUtils;
import com.google.common.collect.ImmutableMap;

import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) {

        String url = "http://m.babyfs.cn/api/m/promoter/bind_user_detail_info?user_id=" + 3602548;
        String response = HttpUtils.get(url, 3000,
                ImmutableMap.of("x-auth-token", "JtDEUbbqxNMAUX6venXcN1t96qvuTydWy95mpJpiN6gFp3dTSUBqAdy/RhGFA5P5lVhSN8ySMrFe9bWGZ5dW/Q=="), null, StandardCharsets.UTF_8);
        System.out.println(response);

    }
}

