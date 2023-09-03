package dao;

import dto.InformeProdMasRecaudacion;
import entidades.Producto;

public interface ProductoDAO extends CRUDDAO<Producto> {
	public InformeProdMasRecaudacion selectProductoConMasRecaudacion();
}
