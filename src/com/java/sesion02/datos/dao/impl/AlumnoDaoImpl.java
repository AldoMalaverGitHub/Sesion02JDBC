package com.java.sesion02.datos.dao.impl;

import java.sql.Connection;
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
		List<Alumno> listaAlumnos = dao.listarAlumnos();
		
		for(Alumno alumno: listaAlumnos){
			System.out.println(alumno.getId());
			System.out.println(alumno.getNombres());
			System.out.println(alumno.getApellidos());
		}
	}

}
