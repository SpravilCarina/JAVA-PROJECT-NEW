package com.example.demo;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MasinaMapper implements RowMapper<Masina> {
    @Override
    public Masina mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Masina(
            rs.getString("nr_inmatriculare"),
            rs.getString("marca"),
            rs.getInt("anul_fabricatiei"),
            rs.getString("culoarea"),
            rs.getInt("nr_km")
        );
    }
}
