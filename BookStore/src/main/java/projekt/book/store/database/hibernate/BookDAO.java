package projekt.book.store.database.hibernate;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import projekt.book.store.database.IBookDAO;
import projekt.book.store.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {

    final String GET_ALL_BOOK_QUERY = "FROM projekt.book.store.model.Book";
    final String GET_BOOKS_BY_PATTERN_QUERY = "FROM projekt.book.store.model.Book WHERE title LIKE :pattern OR author LIKE :pattern";
    final String GET_BOOK_BY_ID_QUERY = "FROM projekt.book.store.model.Book WHERE id = :id";
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(
                GET_ALL_BOOK_QUERY, Book.class);
        List<Book> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BOOKS_BY_PATTERN_QUERY, Book.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Book> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Optional<Book> persistBook(Book book) {
        return this.mergeBook(book);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BOOK_BY_ID_QUERY, Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        } finally {
            session.close();
        }
    }

/*    public Optional<Book> getBookByIdCriteriaQuery(int id){
        Session session = this.sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        criteriaQuery
                .select(bookRoot)
                .where(criteriaBuilder.equal(bookRoot.get("id)"), id));
        Query<Book> query = session.createQuery(criteriaQuery);
        try{
            return Optional.of(query.getSingleResult());

        }catch (NoResultException e){
            return Optional.empty();
        } finally {
            session.close();
        }
    }*/

    @Override
    public void deleteBook(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Book(id));
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        return this.mergeBook(book);
    }

    private Optional<Book> mergeBook(Book book){
        Session session = this.sessionFactory.openSession();
        try{
            session.beginTransaction();
            Book bookFromDB = session.merge(book);
            session.getTransaction().commit();
            return Optional.of(bookFromDB);
        } catch (Exception e) {
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
