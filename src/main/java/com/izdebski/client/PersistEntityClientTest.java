package com.izdebski.client;

import java.util.Date;

import org.hibernate.Session;

import com.izdebski.entities.Book;
import com.izdebski.entities.Person;
import com.izdebski.util.HibernateUtil;

public class PersistEntityClientTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Person author1 = new Person();
            author1.setName("Stephen King");

            Date publishedDate = new Date();

            Book book1 = new Book();
            book1.setIsbn("978-9730228236");
            book1.setTitle("Hibernate High-Performance Java Persistence");
            book1.setPublishedDate(publishedDate);
            book1.setAuthor(author1);


            Book book2 = new Book();
            book2.setIsbn("900-9730228524");
            book2.setTitle("Hibernate Persistence Contexts");
            book2.setPublishedDate(publishedDate);
            book2.setAuthor(author1);

            author1.getBooks().add(book1);
            author1.getBooks().add(book2);

            session.beginTransaction();
            session.save(author1);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}