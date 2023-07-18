package com.uz.biblio.service;

import com.uz.biblio.beans.Book;
import com.uz.biblio.beans.BookSymbol;
import com.uz.biblio.repository.impl.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBookList(){
        return bookRepository.getAllSortedByTitleDesc();
    }

    public List<BookSymbol> getBySymbol(String param){
        List<BookSymbol> bookSymbols = bookRepository.findBySymbol(param);
        return bookSymbols.stream()
                .filter(b -> b.getSymbolCount() > 0).collect(Collectors.toList());
    }

    public List<Book> getAllGroupByAuthor() {
        return bookRepository.getAllGroupByAuthor();
    }

    public int insertBook(Book book){
        return bookRepository.save(book);
    }

}
