package es.upm.dit.isst.acta.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Asignatura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String nombre;
	
	private String email_coordinador;
	private String email_vocal;
	private String email_secretario;
	private String email_presidente;

	private String nombre_coordinador;
	private String nombre_vocal;
	private String nombre_secretario;
	private String nombre_presidente;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getNombre_coordinador() {
		return nombre_coordinador;
	}
	public void setNombre_coordinador(String nombre_coordinador) {
		this.nombre_coordinador = nombre_coordinador;
	}
	public String getNombre_vocal() {
		return nombre_vocal;
	}
	public void setNombre_vocal(String nombre_vocal) {
		this.nombre_vocal = nombre_vocal;
	}
	public String getNombre_secretario() {
		return nombre_secretario;
	}
	public void setNombre_secretario(String nombre_secretario) {
		this.nombre_secretario = nombre_secretario;
	}
	public String getNombre_presidente() {
		return nombre_presidente;
	}
	public void setNombre_presidente(String nombre_presidente) {
		this.nombre_presidente = nombre_presidente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email_coordinador == null) ? 0 : email_coordinador.hashCode());
		result = prime * result + ((email_presidente == null) ? 0 : email_presidente.hashCode());
		result = prime * result + ((email_secretario == null) ? 0 : email_secretario.hashCode());
		result = prime * result + ((email_vocal == null) ? 0 : email_vocal.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombre_coordinador == null) ? 0 : nombre_coordinador.hashCode());
		result = prime * result + ((nombre_presidente == null) ? 0 : nombre_presidente.hashCode());
		result = prime * result + ((nombre_secretario == null) ? 0 : nombre_secretario.hashCode());
		result = prime * result + ((nombre_vocal == null) ? 0 : nombre_vocal.hashCode());
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
		Asignatura other = (Asignatura) obj;
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombre_coordinador == null) {
			if (other.nombre_coordinador != null)
				return false;
		} else if (!nombre_coordinador.equals(other.nombre_coordinador))
			return false;
		if (nombre_presidente == null) {
			if (other.nombre_presidente != null)
				return false;
		} else if (!nombre_presidente.equals(other.nombre_presidente))
			return false;
		if (nombre_secretario == null) {
			if (other.nombre_secretario != null)
				return false;
		} else if (!nombre_secretario.equals(other.nombre_secretario))
			return false;
		if (nombre_vocal == null) {
			if (other.nombre_vocal != null)
				return false;
		} else if (!nombre_vocal.equals(other.nombre_vocal))
			return false;
		return true;
	}
	
	
}
