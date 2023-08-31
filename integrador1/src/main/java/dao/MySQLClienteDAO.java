package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

public class MySQLClienteDAO implements SystemDAO<Cliente>{

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

   
    public List<Cliente> selectClientesConMasFacturacion() {
        //consultar si hayq ue abrir conexion en cada metodo
        String selectClientesConMasFacturacion = "SELECT c.idCliente, c.nombre, c.email " + /* , SUM(p.valor * fp.cantidad) AS total_facturado " + */
            "FROM Cliente c " +
            "JOIN factura ON c.idCliente = factura.idCliente " +
            "JOIN factura_producto fp ON factura.idFactura = fp.idFactura " +
            "JOIN producto p ON fp.idProducto = p.idProducto " +
            "GROUP BY c.idCliente, c.nombre, c.email " +
            "ORDER BY SUM(p.valor * fp.cantidad) DESC; "; 
        PreparedStatement ps = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            ps = conn.prepareStatement(selectClientesConMasFacturacion);
            ResultSet rs = ps.executeQuery(selectClientesConMasFacturacion);
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {                    
                    ps.close();
                    conn.commit();
                    conn.close(); //consultar si es necesario cerrar la conexion aca
                } catch (Exception e) {
                     e.printStackTrace();
                }
            }
        }
        return clientes;
    }

    @Override
    public boolean createTable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTable'");
    }
    
}
