package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.InformeClienteMasFacturacion;
import entidades.Cliente;

public class MySQLClienteDAO implements ClienteDAO{

    private Connection conn;    

    public MySQLClienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(Cliente dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Cliente find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public boolean update(Cliente dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Cliente> selectList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectList'");
    }

   
    public List<InformeClienteMasFacturacion> selectClientesConMasFacturacion() {
        String selectClientesConMasFacturacion = "SELECT c.nombre, c.email, SUM(p.valor * fp.cantidad) AS total_facturado " +
            "FROM Cliente c " +
            "JOIN factura ON c.idCliente = factura.idCliente " +
            "JOIN factura_producto fp ON factura.idFactura = fp.idFactura " +
            "JOIN producto p ON fp.idProducto = p.idProducto " +
            "GROUP BY c.idCliente, c.nombre, c.email " +
            "ORDER BY SUM(p.valor * fp.cantidad) DESC; "; 
        PreparedStatement ps = null;
        List<InformeClienteMasFacturacion> informe = new ArrayList<>();
        try {
            ps = conn.prepareStatement(selectClientesConMasFacturacion);
            ResultSet rs = ps.executeQuery(selectClientesConMasFacturacion);
            while (rs.next()) {
                InformeClienteMasFacturacion detail = new InformeClienteMasFacturacion(rs.getString(1), rs.getString(2), rs.getFloat(3));
                informe.add(detail);
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
