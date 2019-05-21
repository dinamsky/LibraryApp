package repository;

import entities.Book;
import specifications.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookRepository implements Repository<Book, Integer> {
    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public void update(Book book) {
        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.getTransaction().commit();    }

    public void delete(Integer integer){
        entityManager.getTransaction().begin();
        Book book = getByPk(integer);
        entityManager.remove(book);
        entityManager.getTransaction().commit();    }

        public void delAll(){


        }

    public Book getByPk(Integer integer) {
        return
        entityManager.find(Book.class, integer );
         }


//    public List getAll() {
//        //named queries
//        TypedQuery<Book> query = entityManager.createNamedQuery("Book.fileAll",Book.class);
//        List<Book> books = query.getResultList();
//        //JPQL
//        Query query1 = entityManager.createQuery("SELECT b from Book  b");
//        List<Book> books1 = query1.getResultList();
//        //Criteria API
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = criteriaQuery.from(Book.class);
//        criteriaQuery.select(root);
//        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
//        List<Book> books2 = typedQuery.getResultList();
//
//
//        return books;  }
//
//        public Book getByTitle(String title){
//        //Named query
//            TypedQuery<Book> query = entityManager.createNamedQuery("Book.findByTitle",Book.class);
//            query.setParameter("title",title);
//            Book book = query.getSingleResult();
////            TypedQuery<Book> query1 = entityManager.createQuery("SELECT b from Book  b where title = :title",Book.class);
////            query1.setParameter("title",title);
//            //criteria
//            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
//            Root<Book> root = criteriaQuery.from(Book.class);
//
//            Predicate condition = criteriaBuilder.equal(root.<String>get("title"),title);
//            criteriaQuery.select(root).where(condition);
//            TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
//            Book books2 = typedQuery.getSingleResult();
//        return book;
//
//        }

        public  List<Book> getBySpec(Specification spec){
        try{
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(spec.getType());
        Root<Book> root = criteriaQuery.from(spec.getType());
        Predicate condition =  spec.toPredicate(root,criteriaBuilder);
        criteriaQuery.where(condition);
        return entityManager.createQuery(criteriaQuery).getResultList();}
        catch(NullPointerException e){
            e.printStackTrace();
        }
return null;
        }
}

