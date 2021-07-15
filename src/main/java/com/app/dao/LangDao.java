package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;

@Repository
public class LangDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HashMap<String, String> getTranslations(long langId, String page) {
        return jdbcTemplate.query("SELECT key, text FROM translations " +
                "WHERE lang_id = ? AND page = ? " +
                "OR lang_id = ? AND page = 'all'",
                (ResultSet rs) -> {
                    HashMap<String, String> result = new HashMap<>();

                    while (rs.next()) {
                        result.put(rs.getString("key"), rs.getString("text"));
                    }

                    return result;
                },langId, page, langId);
    }

    public HashMap<Long, String> getLanguages() {
        return jdbcTemplate.query("SELECT id, name FROM langs", (ResultSet rs) -> {
            HashMap<Long, String> result = new HashMap<>();

            while (rs.next()) {
                result.put(rs.getLong("id"), rs.getString("name"));
            }

            return result;
        });
    }
}
