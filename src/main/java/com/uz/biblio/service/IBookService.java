package com.uz.biblio.service;

import com.uz.biblio.beans.Book;
import com.uz.biblio.beans.BookSymbol;
import java.util.List;


public interface IBookService {
    public List<Book> getAllBookList();
    public List<BookSymbol> getBySymbol(String param);
    public List<Book> getAllGroupByAuthor();
    public int insertBook(Book book);
}
