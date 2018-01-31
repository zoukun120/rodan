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

//  访问地址 http://localhost:8080/getdata/2017-12-01/2017-12-02
    @GetMapping("/getdata/{startTime}/{endTime}")
    public void getData(@PathVariable String startTime,@PathVariable String endTime) throws Exception { //@RequestBody DataResult dataResult
        String filename = "KF0002_"+startTime+".xlsx";
        startTime += " 00:00:00";
        endTime += " 00:00:00";
        List<KF0002> data = dataMapper.findDatasByTime(startTime, endTime);
        String outPath = "G:\\数据库\\";
        ExcelUtil.createExcelSheet(data,outPath,filename);
    }
}
