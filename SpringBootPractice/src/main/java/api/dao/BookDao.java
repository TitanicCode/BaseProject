package api.dao;


import api.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by helen on 2018/6/27
 */
public interface BookDao extends JpaRepository<Book, Long> {
}
