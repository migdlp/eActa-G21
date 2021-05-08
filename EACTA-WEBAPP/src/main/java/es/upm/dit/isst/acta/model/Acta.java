package es.upm.dit.isst.acta.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Acta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String email_alumno;
	private String asignatura;
	private boolean firma_vocal;
	private boolean firma_secretario;
	private boolean firma_presidente;
	
	private String email_coordinador;
	private String email_vocal;
	private String email_secretario;
	private String email_presidente;
	
	
	private int status;
	
	private double nota;
	private boolean es_definitiva;
	
	public Acta() {
	   
}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail_alumno() {
		return email_alumno;
	}

	public void setEmail_alumno(String email_alumno) {
		this.email_alumno = email_alumno;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public boolean isFirma_vocal() {
		return firma_vocal;
	}

	public void setFirma_vocal(boolean firma_vocal) {
		this.firma_vocal = firma_vocal;
	}

	public boolean isFirma_secretario() {
		return firma_secretario;
	}

	public void setFirma_secretario(boolean firma_secretario) {
		this.firma_secretario = firma_secretario;
	}

	public boolean isFirma_presidente() {
		return firma_presidente;
	}

	public void setFirma_presidente(boolean firma_presidente) {
		this.firma_presidente = firma_presidente;
	}

	public String getEmail_coordinador() {
		return email_coordinador;
	}

	public void setEmail_coordinador(String email_coordinador) {
		this.email_coordinador = email_coordinador;
	}

	public String getEmail_vocal() {
		return email_vocal;
	}

	public void setEmail_vocal(String email_vocal) {
		this.email_vocal = email_vocal;
	}

	public String getEmail_secretario() {
		return email_secretario;
	}

	public void setEmail_secretario(String email_secretario) {
		this.email_secretario = email_secretario;
	}

	public String getEmail_presidente() {
		return email_presidente;
	}

	public void setEmail_presidente(String email_presidente) {
		this.email_presidente = email_presidente;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public boolean isEs_definitiva() {
		return es_definitiva;
	}

	public void setEs_definitiva(boolean es_definitiva) {
		this.es_definitiva = es_definitiva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((email_alumno == null) ? 0 : email_alumno.hashCode());
		result = prime * result + ((email_coordinador == null) ? 0 : email_coordinador.hashCode());
		result = prime * result + ((email_presidente == null) ? 0 : email_presidente.hashCode());
		result = prime * result + ((email_secretario == null) ? 0 : email_secretario.hashCode());
		result = prime * result + ((email_vocal == null) ? 0 : email_vocal.hashCode());
		result = prime * result + (es_definitiva ? 1231 : 1237);
		result = prime * result + (firma_presidente ? 1231 : 1237);
		result = prime * result + (firma_secretario ? 1231 : 1237);
		result = prime * result + (firma_vocal ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(nota);
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
		if (email_alumno == null) {
			if (other.email_alumno != null)
				return false;
		} else if (!email_alumno.equals(other.email_alumno))
			return false;
		if (email_coordinador == null) {
			if (other.email_coordinador != null)
				return false;
		} else if (!email_coordinador.equals(other.email_coordinador))
			return false;
		if (email_presidente == null) {
			if (other.email_presidente != null)
				return false;
		} else if (!email_presidente.equals(other.email_presidente))
			return false;
		if (email_secretario == null) {
			if (other.email_secretario != null)
				return false;
		} else if (!email_secretario.equals(other.email_secretario))
			return false;
		if (email_vocal == null) {
			if (other.email_vocal != null)
				return false;
		} else if (!email_vocal.equals(other.email_vocal))
			return false;
		if (es_definitiva != other.es_definitiva)
			return false;
		if (firma_presidente != other.firma_presidente)
			return false;
		if (firma_secretario != other.firma_secretario)
			return false;
		if (firma_vocal != other.firma_vocal)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(nota) != Double.doubleToLongBits(other.nota))
			return false;
		if (status != other.status)
			return false;
		return true;
	}






}