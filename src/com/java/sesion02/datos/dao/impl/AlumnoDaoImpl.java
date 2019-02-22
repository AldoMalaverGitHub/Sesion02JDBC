package com.java.sesion02.datos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		List<Alumno> listaAlumnos = new ArrayList<>();
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from Alumno");
			
			while(rs.next()){
				Alumno alumno = new Alumno();
				alumno.setId(rs.getInt(1));
				alumno.setNombres(rs.getString(2));
				alumno.setApellidos(rs.getString(3));
				alumno.setEstado(rs.getInt(4));
				listaAlumnos.add(alumno);
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
		return listaAlumnos;
	}

	@Override
	public int guardarAlumno(Alumno alumno) {
		Conexion conexion = new Conexion();
		Connection conn = conexion.getConnection();
		String sql = "Insert into Alumno(nombres, apellidos, codigoAlumno, estado) VALUES(?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = 0;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alumno.getNombres());
			pstmt.setString(2, alumno.getApellidos());
			pstmt.setString(3, alumno.getCodigoAlumno());
			pstmt.setInt(4, alumno.getEstado());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception ex){
			ex.printStackTrace();
			}
		}
				
		
		return id;
	}

	@Override
	public int actualizarAlumno(Alumno alumno) {
		Conexion conexion = new Conexion();
		Connection conn = conexion.getConnection();
		String sql = "Update Alumno set nombres = ?, apellidos = ? where id = ?";
		PreparedStatement pstmt = null;
		int resultado = 0;
			
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alumno.getNombres());
			pstmt.setString(2, alumno.getApellidos());
			pstmt.setInt(3, alumno.getId());			
			
			resultado = pstmt.executeUpdate();
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception ex){
			ex.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public int eliminarAlumno(int id) {
		Conexion conexion = new Conexion();
		Connection conn = conexion.getConnection();
		String sql = "Delete from Alumno where id = ?";
		PreparedStatement pstmt = null;
		int resultado = 0;
			
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);						
			
			resultado = pstmt.executeUpdate();
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception ex){
			ex.printStackTrace();
			}
		}
		return resultado;
	}
	
	public static void main(String[] args){
		AlumnoDao dao = new AlumnoDaoImpl();
		List<Alumno> listaAlumnos = dao.listarAlumnos();
		
		for(Alumno alumno: listaAlumnos){
			System.out.println(alumno.getId());
			System.out.println(alumno.getNombres());
			System.out.println(alumno.getApellidos());
		}
		
		/*Alumno alumno = new Alumno();
		alumno.setNombres("Carmen");
		alumno.setApellidos("Lezama");
		alumno.setCodigoAlumno("10");
		alumno.setEstado(1);
		
		System.out.println("Id generado::" + dao.guardarAlumno(alumno));*/
		
		Alumno alumno = new Alumno();
		alumno.setId(3);
		alumno.setNombres("Freddy");
		alumno.setApellidos("Mercury");
		
		System.out.println("El resultado es: " + dao.actualizarAlumno(alumno));
		
		System.out.println("El resultado de eliminaci�n es: " + dao.eliminarAlumno(1));
	}

}
