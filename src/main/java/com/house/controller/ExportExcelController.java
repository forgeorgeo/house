package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dao.UserCourseMapper;
import com.house.pojo.CourseInfo;
import com.house.service.ImportService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExportExcelController {

    @Autowired
    private ImportService importService;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/index")
    public String getTestHtml() {
        return "index";
    }

    @RequestMapping("/list")
    public ModelAndView itemsList() {
        String sql = "select * from dianzu";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        ModelAndView mav = new ModelAndView("items");
        mav.addObject("list", list);
        return mav;
    }


    //访问http://localhost:9002/index
    //会自动跳转到点击导出的按钮
//    @RequestMapping(value = "/index")
//    public String getIndexJsp() {
//        return "outExcel";
//    }

    //同上
    @RequestMapping(value = "/import")
    public String getImportJsp() {
        return "import";
    }

    /**
     *  导出数据到Excel
     * */
    @GetMapping(value = "/outExcel")
    public Result outExcelDemo(HttpServletResponse response) throws IOException {
        try {
            //创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("sheet1");

            HSSFRow row = sheet.createRow(0);
            //创建第一行，这里即是表头。行的最小值是0，代表每一行
            //在这一行创建单元格，并且将这个单元格的内容设为“账号”，下面同理。
            //列的最小值标识也是0
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("课程名");
            row.createCell(2).setCellValue("课程id");
            row.createCell(3).setCellValue("课程信息");
            row.createCell(4).setCellValue("工作量");

            //测试数据 = 11
            List<CourseInfo> courseInfos = userCourseMapper.getCourseInfos("11");
            for (int i = 0; i < courseInfos.size(); i++) {
                HSSFRow row1 = sheet.createRow(i+1);
                row1.createCell(0).setCellValue(i+1);
                row1.createCell(1).setCellValue(courseInfos.get(i).getCourseName());
                row1.createCell(2).setCellValue(courseInfos.get(i).getId());
                row1.createCell(3).setCellValue(courseInfos.get(i).getCourseInfo());
            }

            //输出Excel文件
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename = demo.xls");
            response.setContentType("application/x-xls");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"");
        }
        return new Result(true, StatusCode.SUCCESS,"", null);
    }


    @RequestMapping(value = "/upload")
    @ResponseBody
    public Result importExcel(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> map= new HashMap<>();
        try {
            // 获取传入文件
            InputStream inputStream = file.getInputStream();
            return importService.readExcelFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR,"");
        }
    }





}