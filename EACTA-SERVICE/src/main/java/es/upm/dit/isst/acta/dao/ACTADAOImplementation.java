package es.upm.dit.isst.acta.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.acta.model.Acta;

public class ACTADAOImplementation implements ACTADAO {
	private static ACTADAOImplementation instancia = null;

	private ACTADAOImplementation() {
	}

	public static ACTADAOImplementation getInstance() {
		if (null == instancia)
			instancia = new ACTADAOImplementation();
		return instancia;

	}

	@Override
	public Acta create(Acta acta) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		try {
			System.out.println("creando acta en dao");
			session.save(acta);
			
		} catch (Exception e) {
			acta = null;
		}
		session.getTransaction().commit();
		session.close();
		return acta;
	}

	@Override
	public Acta read(String id) {

		Session session = SessionFactoryService.get().openSession();

		session.beginTransaction();

		Acta acta = null;
		acta = session.get(Acta.class, id);
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
		List<Acta> actas = new ArrayList<Acta>();
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		actas.addAll(session.createQuery("from Acta").list());
		session.getTransaction().commit();
		session.close();
		return actas;
	}

	@Override
	public List<Acta> readAll(String professor) {
		if (professor == null)
			return null;
		List<Acta> res = new ArrayList<Acta>();
		List<Acta> all = this.readAll();
		

		if (all.size() == 0)
			return res;
		for (Acta acta : all) { 	

			if (acta.equals(null))
				continue;
			if (acta.getEmail_coordinador() != null) {
				if (acta.getEmail_coordinador().equals(professor)) {
					res.add(acta);
					continue;
				}
			}
			if (acta.getEmail_secretario() != null) {
				if (acta.getEmail_secretario().equals(professor)) {
					res.add(acta);
					continue;
				}
			}

			if (acta.getEmail_vocal() != null) {
				if (acta.getEmail_vocal().equals(professor)) {
					res.add(acta);
					continue;
				}
			}
			if (acta.getEmail_presidente() != null) {
				if (acta.getEmail_presidente().equals(professor)) {
					res.add(acta);
					continue;
				}
			}
			if (acta.getEmail_alumno() != null) {
				if (acta.getEmail_alumno().equals(professor)) {
					res.add(acta);
					continue;
				}
			}
		}
		return res;
	}

}
