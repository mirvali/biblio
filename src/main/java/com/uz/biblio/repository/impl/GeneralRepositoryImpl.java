package com.uz.biblio.repository.impl;

import com.uz.biblio.beans.Book;
import com.uz.biblio.repository.IGeneralRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@Repository
public class GeneralRepositoryImpl implements IGeneralRepository {

    public Logger log = LoggerFactory.getLogger(GeneralRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List getList(String sql, Class clazz)  {
        try {
            List list = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
            return list;
        }catch (Exception e){
            log.error("getList Exception "+ e.getMessage());
            return Collections.EMPTY_LIST;
        }

    }
    public List getListWithParams(String sql, Object[] args, Class clazz)
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
                    return jdbcTemplate.update(sql, new Object[] {id,
                                                                    book.getTitle(),
                                                                    book.getAuthor(),
                                                                    book.getDescription()});
        }catch (Exception e){
            log.error("getObject Exception "+ e.getMessage());
            return -1;
        }
    }




}
