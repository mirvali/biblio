package com.uz.biblio.repository.impl;

import com.uz.biblio.beans.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeneralJdbcHelper<T> {

    private final JdbcTemplate jdbcTemplate;

    public List<T> getList(String sql, Class clazz)  {
        try {
            return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
        }catch (Exception e){
            log.error("getList Exception "+ e.getMessage());
            return Collections.EMPTY_LIST;
        }

    }
    public List<T> getObjectListByParam(String sql, Object[] args, Class clazz)
    {
        try {
            BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(clazz);
            return this.jdbcTemplate.query(sql, args, rowMapper);
        }catch (Exception e){
            log.error("getListWithParams Exception "+ e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    public int insertObject(String sql, Book book) {
        try {
               Long id = jdbcTemplate.query("SELECT nextval('book_id_seq')",
                            rs -> {
                                return rs.getLong(1);
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
