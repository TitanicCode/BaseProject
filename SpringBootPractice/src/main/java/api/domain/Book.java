package api.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by helen on 2018/6/27
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="bookId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(name="bookName")
    private String bookName;

    @Column(name="isbn")
    private String isbn;

    @Column(name="publisher")
    private String publisher;

    @Column(name="publishTime")
    private String publishTime;

    public Book() {
    }

    public Book(Long bookId, String bookName, String isbn, String publisher, String publishTime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishTime = publishTime;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
