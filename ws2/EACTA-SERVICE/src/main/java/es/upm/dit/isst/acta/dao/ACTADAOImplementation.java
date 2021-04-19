package es.upm.dit.isst.acta.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.acta.model.Acta;

public class ACTADAOImplementation implements ACTADAO {
	private static ACTADAOImplementation instancia = null;
	private ACTADAOImplementation() {}
	
	public static ACTADAOImplementation getInstance() {
		if( null == instancia)
			instancia = new ACTADAOImplementation();
		return instancia;
		
	}

	@Override
	public Acta create(Acta acta) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		try {
			session.save(acta);
		}catch(Exception e) {
			acta = null;
			}
		session.getTransaction().commit();
		session.close();
		return acta;
	}

	@Override
	public Acta read(String email) {

	  Session session = SessionFactoryService.get().openSession();

	  session.beginTransaction();

	  Acta acta = null;
	 // operaciones
	  session.getTransaction().commit();

	  session.close();

	  return acta;
	}

	@Override
	public Acta update(Acta acta) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(acta);
		session.getTransaction().commit();
		session.close();
		return acta;
	}

	@Override
	public Acta delete(Acta acta) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(acta);
		session.getTransaction().commit();
		session.close();
		return acta;
	}

	@Override
	public List<Acta> readAll() {
		List<Acta> actas = new ArrayList<Acta> ();
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		actas.addAll(session.createQuery("from Acta").list());
		session.getTransaction().commit();
		session.close();
		return actas;		
	}

	@Override
	public List<Acta> readAll(String professor) {
		List<Acta> res = new ArrayList<Acta>();
		for (Acta acta : this.readAll())
			if (acta.getEmailcoordinador().equals(professor))
				res.add(acta);
		return res;
	}
	
	@Override
	public List<Acta> read1(String alumno) {
		List<Acta> res = new ArrayList<Acta>();
		for (Acta acta : this.readAll())
			if (acta.getEmailalumno().equals(alumno))
				res.add(acta);
		return res;
	}
	
}
