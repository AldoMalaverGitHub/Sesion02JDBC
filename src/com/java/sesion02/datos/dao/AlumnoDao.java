package com.java.sesion02.datos.dao;

import java.util.List;

import com.java.sesion02.datos.entidades.Alumno;

public interface AlumnoDao {

	List<Alumno> listarAlumnos();
	
	int guardarAlumno(Alumno alumno);
	
	int actualizarAlumno(Alumno alumno);
	
	int eliminarAlumno(int id);
}
