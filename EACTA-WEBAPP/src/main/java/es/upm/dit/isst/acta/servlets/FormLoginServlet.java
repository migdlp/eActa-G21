package es.upm.dit.isst.acta.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.isst.acta.model.Acta;
import es.upm.dit.isst.acta.model.Asignatura;

/**
 * Servlet implementation class FormLoginServlet
 */

@WebServlet("/FormLoginServlet")
public class FormLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Client client = ClientBuilder.newClient(new ClientConfig());

		// autenticacion1

		if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
			req.getSession().setAttribute("admin", true);
			List<Acta> actas = client.target(URLHelper.getURL()).request().accept(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Acta>>() {
					});
			req.setAttribute("actas", actas);
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req, resp);
			return;
		}

		// autenticacion2
		if (email.indexOf("@upm.es") > -1) {
			
			req.getSession().setAttribute("profesor", email);
			

			Asignatura asignatura = client.target(URLHelper.getURL_asignatura() + "/professor/" + email).request()
					.accept(MediaType.APPLICATION_JSON).get(new GenericType<Asignatura>() {
					});
			req.setAttribute("asignatura", asignatura);
			
			List<Asignatura> asignaturas = client.target(URLHelper.getURL_asignatura()).request()
					.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Asignatura>>() {
					});
			req.setAttribute("asignaturas", asignaturas);
			
			if(asignatura==null) {
				getServletContext().getRequestDispatcher("/RegistroProfesor.jsp").forward(req, resp);
				return;
			}
			//Coge las actas y las envia a la vista del profesor

			List<Acta> actas = client.target(URLHelper.getURL() + "/professor/" + email).request()
					.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
					});
			
			req.setAttribute("actas", actas);

			
			getServletContext().getRequestDispatcher("/Professor.jsp").forward(req, resp);
			return;
		}

		// autenticacion3
		List<Acta> actas = null;
		Acta acta = null;
		req.getSession().setAttribute("alumno", email);
		try {
			actas = client.target(URLHelper.getURL() + "/professor/" + email).request()
					.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
					});
			
			System.out.println(acta);
		} catch (Exception e) {
		}
		if (!actas.isEmpty()) {
			req.getSession().setAttribute("actas", actas);
			getServletContext().getRequestDispatcher("/Acta.jsp").forward(req, resp);
			return;
		}
		List<Asignatura> asignaturas = client.target(URLHelper.getURL_asignatura()).request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Asignatura>>() {
				});
		
		req.setAttribute("asignaturas", asignaturas);
		getServletContext().getRequestDispatcher("/RegistroAlumno.jsp").forward(req, resp);
	}
}