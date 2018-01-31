package com.example.rodan.Utils;

import com.example.rodan.entity.KF0002;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ExcelUtil {

    public  static void createExcelSheet(List<KF0002> valuelist,String outPath,String fileName) throws Exception{
        /**创建工作薄*/
        XSSFWorkbook xs = new XSSFWorkbook();
        XSSFSheet sheet = xs.createSheet("sheet1");

        /**创建单元格，并设置值表头 设置表头居中 */
        XSSFCellStyle style = xs.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        System.out.println("数组个数："+valuelist.size());
        System.out.println("KF0002属性个数："+valuelist.get(0).getClass().getDeclaredFields().length );
        //遍历List获取数据
        for (int i = 0; i < valuelist.size(); i++) {//valuelist.size() 8钱多
            KF0002 data = valuelist.get(i);

            XSSFRow row = sheet.createRow(i); //创建行
            row.setRowStyle(style);

            Field[] fields = data.getClass().getDeclaredFields(); //拿到实体类所有的属性

            for (int j = 0; j <fields.length ; j++) {
                String name = fields[j].getName();
                Method m = data.getClass().getMethod("get"+name);
                String value = String.valueOf(m.invoke(data));    //调用getter方法获取属性值
                if(i==0){//再第一行 插入表头
                    row.createCell(j).setCellValue(name);
                }
                else {
                    row.createCell(j).setCellValue(value);
                }
            }

        }

        //写到本地磁盘中
        createFile(xs,outPath,fileName);
    }

    /*

     */
    public static boolean createFile(XSSFWorkbook xs,String path,String fileName){
        Boolean bool = false;
        String f = path+fileName;
        System.out.println(f);
        File file = new File(f);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is "+fileName);
                //创建文件成功后，写入内容到文件里
                try
                {
                    FileOutputStream fout = new FileOutputStream(file);
                    xs.write(fout);
                    fout.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }
}
