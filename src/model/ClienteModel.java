package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.TipoCliente;
import util.MySqlDBConexion;

public class ClienteModel {

	public List<Cliente> consultaCliente(String nombre){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			 
			String sql = "Select c.* , t.nombre from Cliente c inner join tipo_cliente t "
					+ "on c.idTipoCliente = t.idTpoCliente "
					+ "where c.nombres = ? ";

			pstm = con.prepareStatement(sql);
			pstm.setString(1, String.valueOf(nombre));
			
			
			rs = pstm.executeQuery();
					
			Cliente objCliente = null;
			TipoCliente objTipoCliente = null;
			
			while(rs.next()){
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));

				objTipoCliente = new TipoCliente();
				objTipoCliente.setIdTipoCliente(rs.getInt(6));
				objTipoCliente.setDescripcion(rs.getString(7));

				objCliente.setIdTipoCliente(objTipoCliente);
				
				data.add(objCliente);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public List<Cliente> listaCliente(){
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "Select c.* , t.nombre from Cliente c inner join tipo_cliente t "
					+ "on c.idTipoCliente = t.idTpoCliente "
					+ "where c.nombres";

					
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
					
			Cliente objCliente = null;
			TipoCliente objTipoCliente = null;
			while(rs.next()){
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));

				objTipoCliente = new TipoCliente();
				objTipoCliente.setIdTipoCliente(rs.getInt(6));
				objTipoCliente.setDescripcion(rs.getString(7));

				objCliente.setIdTipoCliente(objTipoCliente);
				
				data.add(objCliente);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}
