package com.distribuida.servicios;

import com.distribuida.configuracion.Dbconfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {
//@Inject
      private Jdbi jdbi = Dbconfig.jdbi();

     @Override
     public Book findById(Integer id) {
         List<Book> books= new ArrayList<>();
         try {
             System.out.println("BUSCANDO UNO");

             Handle handle = jdbi.open();
             handle.registerRowMapper(Book.class,
                     (rs, ctx) -> new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"), (rs.getInt("price"))));
             books = handle.createQuery("SELECT id, isbn, title, author, price FROM books where id = :id")
                     .bind("id", id)
                     .mapTo(Book.class)
                     .list();

         } catch (Exception ex) {
             ex.printStackTrace();
         }
         return books.get(0);
    }
    @Override
    public List<Book> findAll() {
        List<Book> books= new ArrayList<>();
        try {
            System.out.println("BUSCANDO TODOS");
            Handle handle = jdbi.open();
            handle.registerRowMapper(Book.class,
                    (rs, ctx) -> new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"), (rs.getInt("price"))));
            books = handle.createQuery("SELECT id, isbn, title, author, price FROM books")
                    .mapTo(Book.class)
                    .list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
return books;
    }


    @Override
    public String  delete(Integer id) {
         int count = 0;
        try {
            System.out.println("ELIMINANDO: ");
            Handle handle = jdbi.open();
            count = handle.createUpdate("delete from books where id = :id")
                    .bind("id", id)
                    .execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "ELIMINADO CORRECTAMENTE" + count;
    }


    public Book update(Book book,Integer id) {
        try {
            Handle handle = jdbi.open();
            handle.createUpdate("update books set id = :id, isbn = :isbn, title = :title, author = :author, price = :price where id = :id")
                    .bind("id", id)
                    .bind("isbn", book.getIsbn())
                    .bind("title", book.getTitle())
                    .bind("author", book.getAuthor())
                    .bind("price", book.getPrice())
                    .execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return book;
    }


    public Book insert(Book book) {
            try {
            Handle handle = jdbi.open();
            handle.createUpdate("insert into books (id, isbn, title, author, price) values (:id, :isbn, :title, :author, :price)")
                    .bind("id", book.getId())
                    .bind("isbn", book.getIsbn())
                    .bind("title", book.getTitle())
                    .bind("author", book.getAuthor())
                    .bind("price", book.getPrice())
                    .execute();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return book;
        }

}
