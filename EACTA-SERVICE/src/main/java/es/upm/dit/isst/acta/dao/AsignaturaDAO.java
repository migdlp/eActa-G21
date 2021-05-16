package es.upm.dit.isst.acta.dao;

import java.util.List;

import es.upm.dit.isst.acta.model.Asignatura;

public interface AsignaturaDAO {
	public Asignatura create(Asignatura asignatura);

	public Asignatura read(String nombre);

	public Asignatura update(Asignatura asignatura);

	public Asignatura delete(Asignatura asignatura);
	
	public List<Asignatura> readAll();

	public Asignatura readAll(String profesor);

}
