package es.upm.dit.isst.acta.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.isst.acta.model.Acta;

/**
 * Servlet implementation class FormCreaActaServlet
 */
@WebServlet("/FormCreaACTAServlet")
public class FormCreaACTAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormCreaACTAServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String email_coordinador = (String) req.getSession().getAttribute("email_coordinador");
		String email_coordinador = (String) req.getParameter("email_coordinador");
		
		
		if (email_coordinador.indexOf("upm.es") > 0) {
			Acta acta = new Acta();
			acta.setNombre_alumno(req.getParameter("nombre_alumno"));
			acta.setEmail_alumno(req.getParameter("email_alumno"));
			acta.setNota(Double.parseDouble(req.getParameter("nota")));
			acta.setId(req.getParameter("email_alumno")+req.getParameter("asignatura"));
			acta.setStatus(1);
			acta.setAsignatura(req.getParameter("asignatura"));
			acta.setNombre_coordinador(req.getParameter("nombre_coordinador"));
			acta.setEmail_coordinador(email_coordinador);
			
			//aqui se cogería el nombre y email del vocal, secretario, presidente (si no hay se ponen por defecto
			acta.setNombre_vocal(req.getParameter("nombre_vocal")!="" ? req.getParameter("nombre_vocal") : "Vocal");
			acta.setEmail_vocal(req.getParameter("email_vocal")!="" ? req.getParameter("email_vocal") : "vocal@upm.es");
			acta.setNombre_secretario(req.getParameter("nombre_secretario")!="" ? req.getParameter("nombre_secretario") : "Secretario");
			acta.setEmail_secretario(req.getParameter("email_secretario")!="" ? req.getParameter("email_secretario") : "secretario@upm.es");
			acta.setNombre_presidente(req.getParameter("nombre_presidente")!="" ? req.getParameter("nombre_presidente") : "Presidente");
			acta.setEmail_presidente(req.getParameter("email_presidente")!="" ? req.getParameter("email_presidente") : "presidente@upm.es");

			Client client = ClientBuilder.newClient(new ClientConfig());
			Response r = client.target(URLHelper.getURL()).request()
					.post(Entity.entity(acta, MediaType.APPLICATION_JSON), Response.class);
			if (r.getStatus() == 200) {
				req.getSession().setAttribute("acta", acta);
				getServletContext().getRequestDispatcher("/Acta.jsp").forward(req, resp);
				return;
			}
		}
		getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}