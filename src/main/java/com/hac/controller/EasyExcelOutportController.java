package com.hac.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author hac
 * @date 2025/8/2 17:47
 */
@RestController
@RequestMapping(value = "/api")
public class EasyExcelOutportController {

    /**
     * ● 如果类中 所有字段都没有加 @ExcelProperty → EasyExcel 默认导出所有字段。
     * ● 如果类中 有任何一个字段加了 @ExcelProperty → EasyExcel 只导出加了注解的字段，忽略其他未标注的字段。
     *
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/outport")
    public void export(HttpServletResponse response) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        users.add(User.builder().name("tom").age(19).addr("北京").sex("0").mail("tom@163.com").build());
        users.add(User.builder().name("jack").age(20).addr("广州").sex("1").mail("jack@163.com").build());
        users.add(User.builder().name("lucy").age(21).addr("深圳").sex("0").mail("lucy@163.com").build());

        // 当你手动编写 setSex() 时，Lombok 不会覆盖它（因为检测到已存在）
        User u1 = User.builder().name("lily").age(22).addr("杭州").mail("lily@163.com").build();
        u1.setSex("1");
        System.out.println(u1);

        // 生成带日期时间后缀的文件名
        String dateTimeSuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileName = "用户列表" + dateTimeSuffix + ".xlsx";

        // 设置响应头
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + encodedFileName + "\";" +
                        "filename*=utf-8''" + encodedFileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; filename=" + encodedFileName);
        response.setCharacterEncoding("utf-8");

        // 使用EasyExcel写入响应流
        EasyExcel.write(response.getOutputStream(), User.class)
                .sheet("用户列表")
                .doWrite(users);
    }
}

@Data
@Builder
class User {
    @ExcelProperty("姓名")
    @ColumnWidth(20)
    private String name;

    @ExcelProperty("年龄")
    @ColumnWidth(20)
    private Integer age;

    @ExcelProperty("地址")
    @ColumnWidth(20)
    private String addr;


    @ExcelProperty("性别")
    @ColumnWidth(10)
    private String sex;

    private String mail;

    public void setSex(String sex) {
        if ("1".equals(sex)) {
            this.sex = "男";
        } else if ("0".equals(sex)) {
            this.sex = "女";
        }
    }
}
