package com.example.demo.dao.impl;

import com.example.demo.core.mappers.MarketMapper;
import com.example.demo.core.utils.DataBaseUtils;
import com.example.demo.core.utils.ProjectUtils;
import com.example.demo.dao.IMarketDAO;
import com.example.demo.entity.Market;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MarketsDAO implements IMarketDAO {
    private final static Logger LOGGER = ProjectUtils.getLogger(MarketsDAO.class);
    private static final String SQL_DELETE_MARKETS_BY_ID = "DELETE FROM market WHERE market_id = :market_id";
    private static final String SQL_CREATE_MARKET = "INSERT INTO market (name,description,phones) VALUES(:name,:description,:phones) RETURNING market.market_id";
    private static final String SQL_GET_MARKET_BY_ID = "SELECT * FROM market WHERE market_id = :market_id";
    public static final String SQL_GET_MARKETS = "SELECT * FROM market";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Market save(Market entity) {
        KeyHolder holder = new GeneratedKeyHolder();

        try {
            Array array = DataBaseUtils
                    .getAccountsConnection()
                    .createArrayOf(DataBaseUtils.DB_VARCHAR, entity.getPhones().toArray(new String[0]));

            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue(MarketMapper.MARKET_NAME, entity.getName())
                    .addValue(MarketMapper.DESCRIPTION, entity.getDescription())
                    .addValue(MarketMapper.PHONES, array);

            namedParameterJdbcTemplate.update(SQL_CREATE_MARKET, parameters, holder);
            entity.setId(Objects.requireNonNull(holder.getKey()).intValue());
        } catch (SQLException e) {
            LOGGER.error("Can't save market", e);
            throw new RuntimeException(e.getMessage());
        }

        return entity;
    }

    @Override
    public Market update(Market entity) {
        return null;
    }

    @Override
    public void removeById(Integer entityId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(MarketMapper.MARKET_ID, entityId, Types.INTEGER);
        try {
            namedParameterJdbcTemplate.execute(SQL_DELETE_MARKETS_BY_ID,
                    params,
                    PreparedStatement::execute);
        } catch (DataAccessException e) {
            LOGGER.error("Can't delete market: ", e);
        }
    }

    @Override
    public Optional<Market> getById(Integer entityId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(MarketMapper.MARKET_ID, entityId, Types.INTEGER);

        try {
            return namedParameterJdbcTemplate
                    .query(SQL_GET_MARKET_BY_ID,
                            params,
                            new MarketMapper())
                    .stream()
                    .findFirst();

        } catch (Exception e) {
            LOGGER.error("Can't get market: ", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Market> getAll() {
        try {
            return namedParameterJdbcTemplate
                    .getJdbcOperations()
                    .query(SQL_GET_MARKETS, new MarketMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Can't get markets: ", e);
        }
        return Collections.emptyList();
    }
}
