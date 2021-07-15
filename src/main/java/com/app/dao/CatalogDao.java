package com.app.dao;

import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories(){
        RowMapper<Category> rowMapper = (rs, rowNum) -> mapCategory(rs);
        return jdbcTemplate.query("SELECT * FROM categories", rowMapper);
    }

    private Category mapCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();

        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));

        return category;
    }

    public void storeCategory(Category category){
        jdbcTemplate.update("INSERT INTO categories (name) VALUES (?)", category.getName());
    }

    public List<Subcategory> getSubcategories(){
        RowMapper<Subcategory> rowMapper = (rs, rowNum) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id, s.name AS s_name, c.id AS c_id, c.name AS c_name " +
                "FROM subcategories s " +
                "INNER JOIN categories c ON s.category_id = c.id", rowMapper);
    }

    public List<Subcategory> getSubcategories(long categoryId){
        RowMapper<Subcategory> rowMapper = (rs, rowNum) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id, s.name AS s_name, c.id AS c_id, c.name AS c_name " +
                "FROM subcategories s " +
                "INNER JOIN categories c ON s.category_id = c.id " +
                "WHERE s.category_id = ? ", rowMapper, categoryId);
    }

    private Subcategory mapSubcategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("c_id"));
        category.setName(resultSet.getString("c_name"));

        Subcategory subcategory = new Subcategory();
        subcategory.setCategory(category);
        subcategory.setId(resultSet.getLong("s_id"));
        subcategory.setName(resultSet.getString("s_name"));

        return subcategory;
    }

    public void storeSubcategory(Subcategory subcategory){
        jdbcTemplate.update("INSERT INTO subcategories (name, category_id) VALUES (?, ?)", subcategory.getName(), subcategory.getCategory().getId());
    }

    public List<CatalogItem> getCatalogItems(){
        RowMapper<CatalogItem> rowMapper = (rs, rowNum) -> mapCatalogItem(rs);
        return jdbcTemplate.query("SELECT c.id AS item_id, c.name AS item_name, c.description, c.price, " +
                "s.id AS sub_id, s.name AS sub_name, t.id AS cat_id, t.name AS cat_name " +
                "FROM catalog c " +
                "INNER JOIN subcategories s ON c.subcategory_id = s.id " +
                "INNER JOIN categories t ON s.category_id = t.id", rowMapper);
    }
    public List<CatalogItem> getCatalogItemsById(long id){
        RowMapper<CatalogItem> rowMapper = (rs, rowNum) -> mapCatalogItem(rs);
        return jdbcTemplate.query("SELECT c.id AS item_id, c.name AS item_name, c.description, c.price, " +
                "s.id AS sub_id, s.name AS sub_name, t.id AS cat_id, t.name AS cat_name " +
                "FROM catalog c " +
                "INNER JOIN subcategories s ON c.subcategory_id = s.id " +
                "INNER JOIN categories t ON s.category_id = t.id WHERE c.subcategory_id = ?", rowMapper, id);
    }

    private CatalogItem mapCatalogItem(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("cat_id"));
        category.setName(resultSet.getString("cat_name"));

        Subcategory subcategory = new Subcategory();
        subcategory.setCategory(category);
        subcategory.setId(resultSet.getLong("sub_id"));
        subcategory.setName(resultSet.getString("sub_name"));

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setSubcategory(subcategory);
        catalogItem.setId(resultSet.getLong("item_id"));
        catalogItem.setName(resultSet.getString("item_name"));
        catalogItem.setDescription(resultSet.getString("description"));
        catalogItem.setPrice(new BigDecimal(resultSet.getString("price")));
//        catalogItem.setBrandId(resultSet.getLong("brand_id"));
//        catalogItem.setImage(resultSet.getString("image"));

        return catalogItem;
    }

    public void storeCatalogItem(CatalogItem catalogItem){
        jdbcTemplate.update("INSERT INTO catalog (subcategory_id, name, description, price) VALUES (?, ?, ?, ?)",
                catalogItem.getSubcategory().getId(), catalogItem.getName(), catalogItem.getDescription(), catalogItem.getPrice());
    }
}
