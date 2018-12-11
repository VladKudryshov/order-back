package com.example.demo.core.mappers;

import com.example.demo.core.utils.ProjectUtils;
import com.example.demo.entity.Product;
import com.example.demo.entity.enums.Dimension;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product>{
    private final static Logger LOGGER = ProjectUtils.getLogger(ProductMapper.class);
    public static final String PRODUC_ID = "id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_PRODUCER = "producer";
    public static final String PRODUCT_DIMENSION = "dimension";
    public static final String PRODUCT_PRICE = "price";
    public static final String LANDING_PRODUCT = "landing_product";


    @Override
    public Product mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(PRODUC_ID));
        product.setName(resultSet.getString(PRODUCT_NAME));
        product.setLandingProduct(resultSet.getString(LANDING_PRODUCT));
        return product;
    }
}
