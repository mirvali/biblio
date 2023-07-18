package com.uz.biblio.repository;

import com.uz.biblio.beans.Book;
import com.uz.biblio.beans.BookSymbol;

import java.util.List;

public interface IBookRepository {
    int save(Book book);

    List<Book> getAllSortedByTitleDesc();

    List<Book> getAllGroupByAuthor();

    List<BookSymbol> findBySymbol(String param);
}
