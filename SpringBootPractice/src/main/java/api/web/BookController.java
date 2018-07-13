package api.web;

import api.domain.Book;
import api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by helen on 2018/6/27
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    static Map<Long, Book> map = new HashMap<Long, Book>();

    static {

        for (long id = 0; id < 10; id++) {
            map.put(id, new Book(id, "name" + id, "isbn" + id, "publisher" + id, "2018-06-27"));
        }
    }

    @GetMapping("list")
    public List<Book> getList(){
        List<Book> bookList = new ArrayList<Book>(map.values());

        return bookList;
    }

    @GetMapping("books")
    public List<Book> geBooktList(){
        List<Book> bookList = bookService.getBookList();
        return bookList;
    }

    @GetMapping("books/{id}")
    public Book getBookById(@PathVariable("id") Long id){
        return bookService.getBookById(id);
    }

    @PostMapping("books")
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @PutMapping("books/{id}")
    public Book updateBookBtId(@PathVariable("id") Long id, @RequestBody Book book){
        return bookService.updateByBookId(id, book);
    }

    @DeleteMapping("books/{id}")
    public String deleteBookById(@PathVariable("id") Long id){
        bookService.deleteBookById(id);
        return "success";
    }
}
