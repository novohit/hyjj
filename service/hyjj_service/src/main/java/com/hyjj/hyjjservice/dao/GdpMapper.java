package com.hyjj.hyjjservice.dao;

import com.hyjj.hyjjservice.dataobject.Gdp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GdpMapper {
    List<Gdp> selectCurrentYearData();
    List<Gdp> selectPassYearData();
    List<Gdp> searchGdpData(String district,String year);
    int updateGdpData(@Param("gdpObj") Gdp gdpObj);
    Gdp selectGdpDataById(Integer id);
    int deleteGdpDataById(Integer id);
    int insertGdpData(@Param("gdpObj") Gdp gdpObj);
}
