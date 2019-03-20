package txt;

import club.codecloud.base.util.net.HttpUtils;
import club.codecloud.base.util.time.DateFormatUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ByData {

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 11; i++) {
            String fileName = "/Users/lei/Documents/Code/IdeaProjects/codecloud/code-demo/src/test/java/txt/" + i + ".json";
            String data = FileUtils.readFileToString(new File(fileName)).toString();
            JSONArray items = JSON.parseObject(data).getJSONObject("data").getJSONArray("items");
            for (int x = 0; x < items.size(); x++) {

                String bindTime = DateFormatUtils.formatDate(DateFormatUtils.DATE_TIME_FORMAT_1, items.getJSONObject(x).getJSONObject("entity").getDate("bindTime"));
                Integer userId = items.getJSONObject(x).getJSONObject("entity").getInteger("userId");
                String wechatName = items.getJSONObject(x).getJSONObject("entity").getString("wechatName");
                Integer days = items.getJSONObject(x).getJSONObject("entity").getInteger("days");
                String url = "http://m.babyfs.cn/api/m/promoter/bind_user_detail_info?user_id=" + userId;
                String response = HttpUtils.get(url, 3000,
                        ImmutableMap.of("x-auth-token", "JtDEUbbqxNMAUX6venXcN1t96qvuTydWy95mpJpiN6gFp3dTSUBqAdy/RhGFA5P5lVhSN8ySMrFe9bWGZ5dW/Q=="), null, StandardCharsets.UTF_8);
                JSONObject responseData = JSON.parseObject(response).getJSONObject("data");
                Integer takeCourseNum = responseData.getInteger("takeCourseNum");
                String subscribe = responseData.getInteger("subscribe").equals(1) ? "是" : "否";
                String item = bindTime + "\t" + userId + "\t" + days +
                        "\t" + takeCourseNum + "\t" + subscribe + "\t" + wechatName;
                System.out.println(item);
            }
        }
    }
}
