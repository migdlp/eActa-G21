package es.upm.dit.isst.acta.dao;

import java.util.List;

import es.upm.dit.isst.acta.model.Acta;


public interface ACTADAO {

	public Acta create(Acta acta);

	public Acta read(String id);

	public Acta update(Acta acta);

	public Acta delete(Acta acta);

	public List<Acta> readAll();

	public List<Acta> readAll(String professor);

}