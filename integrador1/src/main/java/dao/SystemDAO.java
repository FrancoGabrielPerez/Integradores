package dao;

import java.util.List;

public interface SystemDAO<T> {
    public boolean createTable();
    public int insert(T cliente) throws Exception;
    public boolean delete(Integer id);
    public T find(Integer pk);
    public boolean update(T dao);
    public List<T> selectList();
    //aca va el populatedb y create table
}
