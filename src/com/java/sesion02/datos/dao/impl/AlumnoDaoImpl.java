package com.java.sesion02.datos.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.java.sesion02.datos.dao.AlumnoDao;
import com.java.sesion02.datos.entidades.Alumno;
import com.java.sesion02.datos.util.Conexion;

public class AlumnoDaoImpl implements AlumnoDao{

	@Override
	public List<Alumno> listarAlumnos() {
		Conexion conexion = new Conexion();
		Connection conn = conexion.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from Alumno");
			
			while(rs.next()){
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}finally{
			
			try{
			if(conn != null)
				conn.close();
			
			if(rs != null)
				rs.close();
			
			if(stmt != null)
				stmt.close();
			
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public int guardarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarAlumno(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args){
		AlumnoDao dao = new AlumnoDaoImpl();
		dao.listarAlumnos();
	}

}
