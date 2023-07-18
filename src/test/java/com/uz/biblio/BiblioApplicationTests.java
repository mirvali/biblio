package com.uz.biblio;

import com.uz.biblio.beans.Book;
import com.uz.biblio.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class BiblioApplicationTests {

    @Autowired
    private BookService bookService;

    private Book book;


    @BeforeEach
    public void setup(){
        book = new Book(10,"Title_1","Author_1","desc");
        bookService.insertBook(book);
    }

    @AfterEach
    public void afterEach(){

    }

    @Test
    @DisplayName("Getting all books")
    void getAllBook(){

    }
}
