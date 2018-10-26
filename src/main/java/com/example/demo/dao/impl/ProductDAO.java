package com.example.demo.dao.impl;

import com.example.demo.core.mappers.PriceMapper;
import com.example.demo.core.mappers.ProductMapper;
import com.example.demo.core.utils.JDBCUtils;
import com.example.demo.dao.IProductDAO;
import com.example.demo.entity.Market;
import com.example.demo.entity.Price;
import com.example.demo.entity.Product;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDAO implements IProductDAO {
    @Override
    public Product save(Product entity) {
        return null;
    }

    @Override
    public List<Product> getProductFromMarket(Market market) {
        try {
            Connection connection = JDBCUtils.getAccountsConnection();
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement("SELECT" +
                    "  p.product_id," +
                    "  p.name product," +
                    "  plm.price," +
                    "  plm.dimension" +
                    " FROM price_list_market plm" +
                    " INNER JOIN product p on p.product_id = plm.product_id" +
                    " INNER JOIN market m on m.market_id = plm.market_id" +
                    " WHERE m.market_id = ?");
            preparedStatement.setInt(1, market.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = Lists.newArrayList();
            while (resultSet.next()) {
                Product product = new ProductMapper().entity(resultSet).convert();
                Price price = new PriceMapper().entity(resultSet).convert();
                product.setPrice(price);
                products.add(product);
            }
            resultSet.close();
            connection.close();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public boolean removeById(Integer entityId) {
        return false;
    }

    @Override
    public Product getEntityById(Integer entityId) {
        return null;
    }

    @Override
    public List<Product> getEntities() {
        return null;
    }
}
