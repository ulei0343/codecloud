package txt;

import club.codecloud.base.util.net.HttpUtils;
import club.codecloud.base.util.time.DateFormatUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByData {

    public static void main(String[] args) throws IOException {


        String auth = "8/uhm9kmSLAlblP9ScI08tbehuaS1r5G+AGmuqdC0exsVV0cbgRQlNCE332vVa6bOU2t08KgyvfbFY3dg1HOBg==";
        ImmutableMap<String, String> header = ImmutableMap.of("x-auth-token", auth);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("基础数据");
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("学员昵称");
        title.createCell(1).setCellValue("打卡数");
        title.createCell(2).setCellValue("上课数");
        title.createCell(3).setCellValue("绑定时间");
        title.createCell(4).setCellValue("是否关注服务号");
        title.createCell(5).setCellValue("是否上课");
        title.createCell(6).setCellValue("是否购课");
        title.createCell(7).setCellValue("购课详情");
        int row = 1;
        for (int i = 20; i < 21; i++) {
            String getAllUserUrl = "http://m.babyfs.cn/api/m/promoter/list_users?page_size=20&page_index=" + String.valueOf(i) + "&wechat=&is_default_order=1&ascend=";
            String userListData = HttpUtils.get(getAllUserUrl, 3000, header, null, StandardCharsets.UTF_8);
            JSONArray items = JSON.parseObject(userListData).getJSONObject("data").getJSONArray("items");
            if (items.size() == 0) {
                return;
            }
            for (int x = 0; x < items.size(); x++) {
                String bindTime = DateFormatUtils.formatDate(DateFormatUtils.DATE_TIME_FORMAT_1, items.getJSONObject(x).getJSONObject("entity").getDate("bindTime"));
                Integer userId = items.getJSONObject(x).getJSONObject("entity").getInteger("userId");
                String wechatName = items.getJSONObject(x).getJSONObject("entity").getString("wechatName");
//                Integer days = items.getJSONObject(x).getJSONObject("entity").getInteger("days");
                String isAttendClass = items.getJSONObject(x).getJSONObject("entity").getInteger("isAttendClass").equals(1) ? "是" : "否";
                String hasOrder = items.getJSONObject(x).getJSONObject("entity").getInteger("hasOrder") > 0 ? "是" : "否";
                String getUserInfoUrl = "http://m.babyfs.cn/api/m/promoter/bind_user_detail_info?user_id=" + userId;
                String getUserInfoResponse = HttpUtils.get(getUserInfoUrl, 3000, header, null, StandardCharsets.UTF_8);
                JSONObject userInfo = JSON.parseObject(getUserInfoResponse).getJSONObject("data");
                Integer takeCourseNum = userInfo.getInteger("takeCourseNum");
                String subscribe = userInfo.getInteger("subscribe").equals(1) ? "是" : "否";
                String unionId = userInfo.getString("unionId");

                String orderInfo = StringUtils.EMPTY;
                if (hasOrder.equals("是")) {
                    String getOrderInfoUrl = "http://m.babyfs.cn/api/m/promoter/order?user_id=" + userId;
                    String getOrderInfoResponse = HttpUtils.get(getOrderInfoUrl, 3000, header, null, StandardCharsets.UTF_8);
                    orderInfo = JSON.parseObject(getOrderInfoResponse).getJSONObject("data").getJSONObject("data").getJSONArray("items").toJSONString();
                }


                String getUserCourseInfoUrl = "http://m.babyfs.cn/api/m/promoter/center_wxx/check_in_list?page_size=20&page_index=1&union_id=" + unionId;
                String gertUserCourseInfoResponse = HttpUtils.get(getUserCourseInfoUrl, 3000, header, null, StandardCharsets.UTF_8);
                String courseCount = JSON.parseObject(gertUserCourseInfoResponse).getJSONObject("data").getString("totalCount");


                HSSFRow row1 = sheet.createRow(row);
                row1.createCell(0).setCellValue(wechatName);
                row1.createCell(1).setCellValue(courseCount);
                row1.createCell(2).setCellValue(takeCourseNum);
                row1.createCell(3).setCellValue(bindTime);
                row1.createCell(4).setCellValue(subscribe);
                row1.createCell(5).setCellValue(isAttendClass);
                row1.createCell(6).setCellValue(hasOrder);
                row1.createCell(7).setCellValue(orderInfo);
                wb.write(new File("/Users/lei/Documents/Code/IdeaProjects/codecloud/code-demo/src/test/java/txt/data.xls"));
                row++;
            }


        }
    }
}
