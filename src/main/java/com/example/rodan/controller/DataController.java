package com.example.rodan.controller;

import com.example.rodan.Utils.ExcelUtil;
import com.example.rodan.dao.DataMapper;
import com.example.rodan.entity.DataResult;
import com.example.rodan.entity.KF0002;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    @Autowired
    private DataMapper dataMapper;

    //  访问地址 http://localhost:8080/getdata/KF0002_201806/2018-06-01/2018-06-31
    @GetMapping("/getdata/{tableName}/{startTime}/{endTime}")
    public @ResponseBody String getData(@PathVariable String tableName,@PathVariable String startTime, @PathVariable String endTime) throws Exception { //@RequestBody DataResult dataResult
        String filename = "KF0002_" + startTime + ".xlsx";
        startTime += " 00:00:00";
        endTime += " 00:00:00";
        Long consume = 0L;
        try {
            System.err.println("开始获取数据。。。");
            Long start_time = System.currentTimeMillis();
            List<KF0002> data = dataMapper.findDatasByTime(tableName,startTime, endTime);
            String outPath = "G:\\论文\\罗单数据\\";
            System.err.println("获取数据成功，正在到处到excel。。。");
            ExcelUtil.createExcelSheet(data, outPath, filename);
            Long end_time = System.currentTimeMillis();
            consume =(end_time-start_time)/1000;
            System.err.println("结束获取数据。。。");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "获取数据成功！共耗时 " + consume + "s";
    }
}
