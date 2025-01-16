package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository //se ocupa de accesul la date
public class MasinaJdbcDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Masina> findAll() {
        String sql = "select * from masina";
        return jdbcTemplate.query(sql, new MasinaMapper());
    }

    public Masina findById(String nr_inmatriculare) {
        String sql = "select * from masina where  nr_inmatriculare=?";
        return jdbcTemplate.queryForObject(sql, new MasinaMapper(), nr_inmatriculare);
    }

    public int deleteById(String nr_inmatriculare) {
        String sql = "delete from masina where  nr_inmatriculare=?";
        return jdbcTemplate.update(sql, nr_inmatriculare);
    }


    public int insert(Masina p) {
        String sql = "insert into masina values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, p.getNr_inmatriculare(), p.getMarca(), p.getAnul_fabricatiei(), p.getCuloarea(), p.getNr_km());
    }

    public int update(Masina p) {
        String sql = "update masina set nr_inmatriculare=?, marca=?, anul_fabricatiei=?, culoarea=?  where  nr_km=?";
        return jdbcTemplate.update(sql, p.getNr_inmatriculare(), p.getMarca(), p.getAnul_fabricatiei(), p.getCuloarea(), p.getNr_km());
    }

    public int getNumarMasiniByMarca(String marca) {
        List<Masina> list = findAll();
        return (int) list.stream()
                .filter(l -> Objects.equals(l.getMarca(), marca))
                .count();
    }

    public int nrMasinisub1000() {
        List<Masina> list = findAll();
        return (int) list.stream()
                .filter(a -> a.getNr_km() > 100000)
                .count();
    }

    public List<Masina> masiniSub5Ani() {
        List<Masina> list = findAll();
        int an = LocalDate.now().getYear();
        return list.stream()
                .filter(a -> an - a.getAnul_fabricatiei() > 5)
                .collect(Collectors.toList());


    }

}
