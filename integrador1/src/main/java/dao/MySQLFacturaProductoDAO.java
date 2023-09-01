package dao;

import java.sql.Connection;
import java.util.List;

import entidades.FacturaProducto;

public class MySQLFacturaProductoDAO implements SystemDAO<FacturaProducto> {

    private Connection conn;    

    public MySQLFacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(FacturaProducto dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public FacturaProducto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public boolean update(FacturaProducto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<FacturaProducto> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }    
}
