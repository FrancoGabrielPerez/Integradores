package dao;

import java.util.List;

import dto.InformeClienteMasFacturacion;
import entidades.Cliente;

public interface ClienteDAO extends CRUDDAO<Cliente> {
	public List<InformeClienteMasFacturacion> selectClientesConMasFacturacion();
}
