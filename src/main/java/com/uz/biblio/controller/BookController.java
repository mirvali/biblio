package com.uz.biblio.controller;

import com.uz.biblio.beans.Book;
import com.uz.biblio.beans.BookSymbol;
import com.uz.biblio.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/book")
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping("/getall")
    public ResponseEntity<List<Book>> getAll() {
        try {
            List<Book> bookList = bookService.getAllBookList();
            if (bookList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(bookService.getAllBookList(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{param}")
    public ResponseEntity<List<BookSymbol>> getBySymbol(@PathVariable String param) {
        try {
            List<BookSymbol> bookSymbols = bookService.getBySymbol(param);
            if (bookSymbols.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(bookSymbols, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-author")
    public ResponseEntity<List<Book>> getByAuthor() {
        try {
            List<Book> books = bookService.getAllGroupByAuthor();
            if (books.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            books.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .sorted(Comparator.comparing(Book::getAuthor))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> insertBook(@RequestBody Book book) {
        try {
            int code = bookService.insertBook(book);
            if (code > 1) {
                return new ResponseEntity<>("Saved successfuly!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Something wrong while saving book!", HttpStatus.OK);
        } catch (NullPointerException ne){
            return new ResponseEntity<>("Please fill the mandatory fields!",HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
