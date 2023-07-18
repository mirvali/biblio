package com.uz.biblio.repository.impl;

import com.uz.biblio.beans.Book;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
public class GeneralJdbcHelper {
    public Logger log;
    private final JdbcTemplate jdbcTemplate;

    public List getList(String sql, Class clazz)  {
        try {
            List list = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
            return list;
        }catch (Exception e){
            log.error("getList Exception "+ e.getMessage());
            return Collections.EMPTY_LIST;
        }

    }
    public List getObjectListByParam(String sql, Object[] args, Class clazz)
    {
        try {
            BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(clazz);
            List list = this.jdbcTemplate.query(sql, args, rowMapper);
            return list;
        }catch (Exception e){
            log.error("getListWithParams Exception "+ e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    public int insertObject(String sql, Book book) {
        try {
               Long id = jdbcTemplate.query("SELECT nextval('book_id_seq')",
                            rs -> {
                                if (rs.next()) {
                                    return rs.getLong(1);
                                } else {
                                    throw new SQLException("Unable to retrieve value from sequence.");
                                }
                            });
                    return jdbcTemplate.queryForObject(sql, new Object[] {id,
                                                                    book.getTitle(),
                                                                    book.getAuthor(),
                                                                    book.getDescription()}, Integer.class);
        }catch (Exception e){
            log.error("getObject Exception "+ e.getMessage());
            return -1;
        }
    }

}
