package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Book;
import ru.maxima.springmvc.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from library_book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id) {
        return jdbcTemplate.query("select * from library_book where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into library_book (person_id, name, author, yeah_of_creation) values ( ?, ?, ?,?)", null, book.getName(),
                book.getAuthor(), book.getYearOfCreation());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update library_book  set name = ? , author = ? , yeah_of_creation = ? where id = ?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYearOfCreation(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from library_book where id = ?", id);
    }

    public void setOwner(Book book, Person person) {
        jdbcTemplate.update("update library_book  set person_id = ? where id = ?", person.getId(), book.getId());
    }

}
