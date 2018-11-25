package com.example.demo.core.mappers;

import com.example.demo.core.utils.ProjectUtils;
import com.example.demo.dao.impl.UsersDAO;
import com.example.demo.entity.Market;
import com.google.common.collect.Lists;
import io.micrometer.core.lang.NonNullApi;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MarketMapper implements RowMapper<Market>{
    private final static Logger LOGGER = ProjectUtils.getLogger(MarketMapper.class);

    public static final String MARKET_NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PHONES = "phones";
    public static final String MARKET_ID = "market_id";

    @Override
    public Market mapRow(ResultSet resultSet, int i) throws SQLException {
        Market market = new Market();
        market.setId(resultSet.getInt(MARKET_ID));
        market.setName(resultSet.getString(MARKET_NAME));
        market.setDescription(resultSet.getString(DESCRIPTION));
        Array array = resultSet.getArray(PHONES);
        Optional.ofNullable(array)
                .ifPresent((arr)-> {
                    try {
                        String[] phones = (String[]) arr.getArray();
                        market.setPhones(Lists.newArrayList(phones));
                    } catch (SQLException e) {
                        LOGGER.error("Can't get array phones: ", e);
                    }
                });
        return market;
    }
}
