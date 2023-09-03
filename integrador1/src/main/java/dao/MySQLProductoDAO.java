package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.InformeProdMasRecaudacion;
import entidades.Producto;

public class MySQLProductoDAO implements ProductoDAO {

    private Connection conn;

    public MySQLProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(Producto dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Producto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public boolean update(Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Producto> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }  

    public InformeProdMasRecaudacion selectProductoConMasRecaudacion() {
        String selectProdMasRecaudacion = "SELECT p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS total_recaudado " +
                                        "FROM producto p " +
                                        "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                                        "GROUP BY p.idProducto, p.valor " +
                                        "ORDER BY total_recaudado DESC " +
                                        "LIMIT 1;";
        PreparedStatement ps = null;
        InformeProdMasRecaudacion informe = new InformeProdMasRecaudacion();
        try {
            ps = conn.prepareStatement(selectProdMasRecaudacion);
            ResultSet rs = ps.executeQuery(selectProdMasRecaudacion);
            while (rs.next()) {
                informe = new InformeProdMasRecaudacion(rs.getString(1), rs.getInt(2), rs.getFloat(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {                                
            try {
                ps.close();
                conn.commit();       
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return informe;
    }

}
