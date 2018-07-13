package api.service;



import api.domain.Book;

import java.util.List;

/**
 * Created by helen on 2018/6/27
 */
public interface BookService {

    List<Book> getBookList();

    Book getBookById(Long id);

    Book saveBook(Book book);

    Book updateByBookId(Long id, Book book);

    void deleteBookById(Long id);
}
