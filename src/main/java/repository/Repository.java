package repository;

public interface Repository<T, PK> {
    void add(T t);
    void update(T t);
    void delete(PK pk);

}