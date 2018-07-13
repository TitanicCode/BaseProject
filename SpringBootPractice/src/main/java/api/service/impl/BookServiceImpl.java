package api.service.impl;


import api.dao.BookDao;
import api.domain.Book;
import api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by helen on 2018/6/27
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getBookList() {
        return bookDao.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.findOne(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book updateByBookId(Long id, Book book) {
        book.setBookId(id);
        return bookDao.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.delete(id);
    }
}
