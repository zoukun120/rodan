package com.example.rodan.service;

import com.example.rodan.dao.DataMapper;
import com.example.rodan.entity.KF0002;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    private DataMapper dataMapper;

    public List<KF0002> getData(String tableName,String startTime, String endTime) {
        System.out.println("服务！！！");
        startTime += " 00:00:00";
        endTime += " 00:00:00";
        List<KF0002> datasByTime = dataMapper.findDatasByTime(tableName,startTime, endTime);
        return datasByTime;
    }
}
