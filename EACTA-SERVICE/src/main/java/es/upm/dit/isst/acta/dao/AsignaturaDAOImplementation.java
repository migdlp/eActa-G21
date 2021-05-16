package es.upm.dit.isst.acta.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.acta.model.Acta;
import es.upm.dit.isst.acta.model.Asignatura;

public class AsignaturaDAOImplementation implements AsignaturaDAO {

	private static AsignaturaDAOImplementation instancia = null;
	private AsignaturaDAOImplementation() {}
	
	public static AsignaturaDAOImplementation getInstance() {
		if( null == instancia)
			instancia = new AsignaturaDAOImplementation();
		return instancia;
		
	}
	
	@Override
	public Asignatura create(Asignatura asignatura) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		try {
			session.save(asignatura);
		}catch(Exception e) {
			asignatura = null;
			}
		session.getTransaction().commit();
		session.close();
		return asignatura;
	}

	@Override
	public Asignatura read(String nombre) {
		  Session session = SessionFactoryService.get().openSession();

		  session.beginTransaction();

		  Asignatura asignatura = null;
		  asignatura = session.get(Asignatura.class, nombre);
		  session.getTransaction().commit();

		  session.close();

		  return asignatura;
	}

	@Override
	public Asignatura update(Asignatura asignatura) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.update(asignatura);
		session.getTransaction().commit();
		session.close();
		return asignatura;
	}

	@Override
	public Asignatura delete(Asignatura asignatura) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(asignatura);
		session.getTransaction().commit();
		session.close();
		return asignatura;
	}
	
	@Override
	public List<Asignatura> readAll() {
		List<Asignatura> asignaturas = new ArrayList<Asignatura> ();
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		asignaturas.addAll(session.createQuery("from Asignatura").list());
		session.getTransaction().commit();
		session.close();
		return asignaturas;		
	}
	
	@Override
	public Asignatura readAll(String profesor) {
		
		List<Asignatura> all = this.readAll();
		
        for (Asignatura asignatura : all) {

            if (asignatura.getEmail_coordinador().equals(profesor))
                return asignatura;
	        if (asignatura.getEmail_secretario().equals(profesor))
	        	return asignatura;
	        if (asignatura.getEmail_vocal().equals(profesor))
	        	return asignatura;
	        if (asignatura.getEmail_presidente().equals(profesor))
	        	return asignatura;
        }
        return null;
	}
		
	
}
