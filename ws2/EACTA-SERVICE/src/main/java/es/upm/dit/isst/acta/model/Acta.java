package es.upm.dit.isst.acta.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Acta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String emailalumno;
	//private String password;
	private String asignatura;
	private String emailcoordinador;
	private int status;
	
	private double notaprovisional;
	
	public Acta() {
	   
}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailalumno() {
		return emailalumno;
	}

	public void setEmailalumno(String emailalumno) {
		this.emailalumno = emailalumno;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getEmailcoordinador() {
		return emailcoordinador;
	}

	public void setEmailcoordinador(String emailcoordinador) {
		this.emailcoordinador = emailcoordinador;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getNotaprovisional() {
		return notaprovisional;
	}

	public void setNotaprovisional(double notaprovisional) {
		this.notaprovisional = notaprovisional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((emailalumno == null) ? 0 : emailalumno.hashCode());
		result = prime * result + ((emailcoordinador == null) ? 0 : emailcoordinador.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(notaprovisional);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acta other = (Acta) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (emailalumno == null) {
			if (other.emailalumno != null)
				return false;
		} else if (!emailalumno.equals(other.emailalumno))
			return false;
		if (emailcoordinador == null) {
			if (other.emailcoordinador != null)
				return false;
		} else if (!emailcoordinador.equals(other.emailcoordinador))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(notaprovisional) != Double.doubleToLongBits(other.notaprovisional))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}