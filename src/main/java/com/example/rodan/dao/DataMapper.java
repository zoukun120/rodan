package com.example.rodan.dao;

import com.example.rodan.entity.KF0002;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DataMapper {

    @Select("select * from ${tableName} WHERE TIME BETWEEN #{startTime} AND #{endTime}  ORDER BY TIME")
    List<KF0002> findDatasByTime(@Param("tableName") String tableName,@Param("startTime") String startTime, @Param("endTime") String endTime);
}
