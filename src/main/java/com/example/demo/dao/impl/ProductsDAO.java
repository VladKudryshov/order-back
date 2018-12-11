package com.example.demo.dao.impl;

import com.example.demo.core.mappers.MarketMapper;
import com.example.demo.core.mappers.MarketProductMapper;
import com.example.demo.core.mappers.ProductMapper;
import com.example.demo.core.utils.ProjectUtils;
import com.example.demo.dao.IProductDAO;
import com.example.demo.entity.MarketProduct;
import com.example.demo.entity.Product;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductsDAO implements IProductDAO {
    public static final String GET_PRODUCT_BY_MARKET = "SELECT" +
            "  p.id," +
            "  p.name," +
            "  p.landing_product," +
            "  p.category_id," +
            "  plm.description," +
            "  plm.id internal_id," +
            "  plm.producer," +
            "  plm.dimension," +
            "  plm.price" +
            " FROM products p" +
            "  INNER JOIN price_list_market plm ON p.id = plm.product_id" +
            " WHERE plm.market_id = :market_id";
    private final static Logger LOGGER = ProjectUtils.getLogger(ProductsDAO.class);
    public static final String ADD_PRODUCT_TO_MARKET = "SELECT * FROM add_product_to_market(:market_id,:name,:product_info::PRODUCT_INFO)";
    public static final String GET_PRODUCT_BY_LANDING = "SELECT * FROM products WHERE landing_product = :landing_product";
    public static final String REMOVE_MARKET_PRODUCT_BY_ID = "DELETE FROM price_list_market WHERE id = :internal_id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Product save(Product entity) {
        return null;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public void removeById(Integer entityId) {

    }

    @Override
    public Optional<Product> getById(String entityId) {
        return Optional.empty();
    }

    @Override
    @Deprecated
    public List<Product> getAll() {
        return null;
    }

    public List<MarketProduct> getAllByMarket(Integer id) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("market_id", id, Types.INTEGER);

        try {
            return namedParameterJdbcTemplate
                    .query(GET_PRODUCT_BY_MARKET, params, new MarketProductMapper());

        } catch (DataAccessException e) {
            LOGGER.error("Can't get markets: ", e);
        }
        return Collections.emptyList();
    }

    @Override
    public MarketProduct addProductToMarket(Integer id, MarketProduct product) {

        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("market_id", id)
                    .addValue(ProductMapper.PRODUCT_NAME, product.getName())
                    .addValue("product_info", product.productInfoPgType());

            List<MarketProduct> query = namedParameterJdbcTemplate
                    .query(ADD_PRODUCT_TO_MARKET, parameters, new MarketProductMapper());


            return query.get(0);

        } catch (Exception e) {
            if (e.getCause() instanceof PSQLException) {
                LOGGER.error("Can't save market. Reason {}", e.getCause().getMessage());
            }
        }
        return null;
    }

    @Override
    public Optional<MarketProduct> getMarketProduct(Integer productId, Integer marketId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("market_id", marketId, Types.INTEGER)
                .addValue("product_id", productId, Types.INTEGER);
        String query = GET_PRODUCT_BY_MARKET;
        query += " AND p.id = :product_id";
        try {
            return namedParameterJdbcTemplate
                    .query(query,
                            params,
                            new MarketProductMapper())
                    .stream()
                    .findFirst();

        } catch (Exception e) {
            LOGGER.error("Can't get product: ", e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteMarketProduct(Integer internalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(MarketProductMapper.PRODUC_INTERNAL_ID, internalId, Types.INTEGER);
        try {
            namedParameterJdbcTemplate.execute(REMOVE_MARKET_PRODUCT_BY_ID,
                    params,
                    PreparedStatement::execute);
        } catch (DataAccessException e) {
            LOGGER.error("Can't delete product from market: ", e);
        }
    }

    @Override
    public Optional<Product> getByLandingProduct(String landingProduct) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue(ProductMapper.LANDING_PRODUCT, landingProduct, Types.VARCHAR);
        try {
            return namedParameterJdbcTemplate
                    .query(GET_PRODUCT_BY_LANDING,
                            params,
                            new ProductMapper())
                    .stream()
                    .findFirst();

        } catch (Exception e) {
            LOGGER.error("Can't get product: ", e);
        }
        return Optional.empty();
    }
}
