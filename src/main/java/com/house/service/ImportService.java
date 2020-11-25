package com.house.service;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.pojo.UserInfo;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Tim
 * @Date 2020/10/23 15:45
 */
@Service
public class ImportService {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 读取excel中的数据,生成list入库
     */
    public Result readExcelFile(InputStream is) {
        XSSFWorkbook workbook = null;
        List<UserInfo> userInfoList = new ArrayList<>();
        Map<String,Object> map =new HashMap<>();
        try{
            workbook = new XSSFWorkbook(is);
            //通常第一行是备注 第二行是标题，所以从第三行开始读取数据
            if(workbook != null) {
                XSSFSheet sheet = workbook.getSheetAt(0); //获得excel第一个sheet
                int lastRowNum = sheet.getLastRowNum(); //得到行数
                //从第2行开始读取数据
                for (int i = 1; i < lastRowNum + 1; i++){
                    if (sheet.getRow(i) == null) {
                        continue;
                    }
                    Row row = sheet.getRow(i);
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(Double.valueOf(this.checkValue(row.getCell(0)).toString()).intValue());//获得列的值
                    userInfo.setPwd(this.checkValue(row.getCell(1)).toString());
                    userInfo.setUserName(this.checkValue(row.getCell(2)).toString());
                    userInfo.setUserNumber(this.checkValue(row.getCell(3)).toString());
                    userInfo.setUserCollege(this.checkValue(row.getCell(4)).toString());
                    userInfo.setUserAge(this.checkValue(row.getCell(5)).toString());
                    userInfo.setUserTel(this.checkValue(row.getCell(6)).toString());
                    userInfo.setUserSex(this.checkValue(row.getCell(7)).toString());
                    userInfo.setUserEmail(this.checkValue(row.getCell(8)).toString());
                    userInfo.setType(Double.valueOf(this.checkValue(row.getCell(9)).toString()).intValue());
                    userInfoList.add(userInfo);
                }
                System.out.println("测试:========================"+ userInfoList);
                //偷懶，直接便利：
                for (UserInfo userInfo : userInfoList) {
                    userInfoService.insertTeacherInfo(userInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true, StatusCode.SUCCESS,"导入成功", null);
    }

    private Cell checkValue(Cell cellValue) {
        if (null != cellValue) {
            // 以下是判断数据的类型
            switch (cellValue.getCellType()) {
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue.setCellValue(cellValue.getStringCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue.setCellValue(cellValue.getBooleanCellValue() + "");
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue.setCellValue("");
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue.setCellValue("非法字符");
                    break;
                default:
                    break;
            }
        }
        return cellValue;
    }

}
