package com.uz.biblio.repository.impl;

import com.uz.biblio.beans.Book;
import com.uz.biblio.beans.BookSymbol;
import com.uz.biblio.repository.IBookRepository;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class BookRepository extends GeneralRepositoryImpl implements IBookRepository {

    private final String SQL_INSERT_BOOK = "insert into book(id, title, author, description) values(?,?,?,?)";
    private final String SQL_All_BOOK = "select * from book order by title desc";
    private final String SQL_GET_AUTHOR_BOOK = "SELECT author, STRING_AGG(title, ',' ORDER BY title) AS title\n" +
            "FROM  book\n" +
            "GROUP BY author";
    private final String SQL_GET_BY_SYMBOL = "SELECT author, sum((CHAR_LENGTH(title) - " +
            "CHAR_LENGTH(REPLACE(lower(title), ?, ''))) / CHAR_LENGTH(?)) as symbolCount \n" +
            "from book \n" +
            "group by author\n" +
            "order by 2 desc\n" +
            "limit 10";



    @Override
    public int save(Book book) {
        return insertObject(SQL_INSERT_BOOK,book);
    }

    @Override
    public List<Book> getAllSortedByTitleDesc() {
        return getList(SQL_All_BOOK, Book.class);
    }

    @Override
    public List<Book> getAllGroupByAuthor() {
        return getList(SQL_GET_AUTHOR_BOOK, Book.class);
    }

    @Override
    public List<BookSymbol> findBySymbol(String param) {
        return getListWithParams(SQL_GET_BY_SYMBOL,new Object[]{param, param}, BookSymbol.class);
    }
}
