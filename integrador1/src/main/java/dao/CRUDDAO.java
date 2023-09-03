package dao;

import java.util.List;

public interface CRUDDAO<T> {
    public int insert(T object) throws Exception;
    public boolean delete(Integer id);
    public T find(Integer id);
    public boolean update(T object);
    public List<T> selectList();
}
