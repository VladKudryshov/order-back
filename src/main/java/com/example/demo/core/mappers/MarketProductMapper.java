package com.example.demo.core.mappers;

import com.example.demo.core.utils.ProjectUtils;
import com.example.demo.entity.Market;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.Dimension;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import javax.validation.constraints.NotNull;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MarketProductMapper implements RowMapper<MarketProduct>{
    private final static Logger LOGGER = ProjectUtils.getLogger(MarketProductMapper.class);

    private static final String PRODUC_ID = "id";
    public static final String PRODUC_INTERNAL_ID = "internal_id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_PRODUCER = "producer";
    private static final String PRODUCT_DIMENSION = "dimension";
    private static final String PRODUCT_PRICE = "price";
    private static final String LANDING_PRODUCT = "landing_product";

    @Override
    public MarketProduct mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        MarketProduct product = new MarketProduct();
        product.setId(resultSet.getInt(PRODUC_ID));
        product.setInternalId(resultSet.getInt(PRODUC_INTERNAL_ID));
        product.setName(resultSet.getString(PRODUCT_NAME));
        product.setLandingProduct(resultSet.getString(LANDING_PRODUCT));
        product.setDescription(resultSet.getString(PRODUCT_DESCRIPTION));
        product.setProducer(resultSet.getString(PRODUCT_PRODUCER));
        product.setDimension(Dimension.valueOf(resultSet.getString(PRODUCT_DIMENSION)));
        product.setPrice(resultSet.getDouble(PRODUCT_PRICE));
        return product;
    }
}
