package es.upm.dit.isst.acta.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import es.upm.dit.isst.acta.model.Acta;
import es.upm.dit.isst.acta.model.Asignatura;

/**
 * Servlet implementation class FormCreaAsignaturaServlet
 */
@WebServlet("/FormCreaAsignaturaServlet")
public class FormCreaAsignaturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormCreaAsignaturaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient(new ClientConfig());

		Asignatura a;
		String email= req.getParameter("profesor");
		String rol = req.getParameter("rol");
		String asig =  req.getParameter("asignatura-existe");

		if (asig.equals("nohay")) {
			a = new Asignatura();
			a.setNombre(req.getParameter("asignatura-nueva"));
			switch (rol) {
				case "coordinador":
					a.setEmail_coordinador(email);
					a.setNombre_coordinador(req.getParameter("nombre"));
					break;
				case "secretario":
					a.setNombre_secretario(req.getParameter("nombre"));
					a.setEmail_secretario(email);
	
					break;
				case "presidente":
					a.setNombre_presidente(req.getParameter("nombre"));
					a.setEmail_presidente(email);
	
					break;
				case "vocal":
					a.setNombre_vocal(req.getParameter("nombre"));
					a.setEmail_vocal(email);
					break;
				default:
					break;
			}
			Response r = client.target(URLHelper.getURL_asignatura()).request()
					.post(Entity.entity(a, MediaType.APPLICATION_JSON), Response.class);
			if (r.getStatus() == 200) {
				//req.getSession().setAttribute("asignatura", a);
				List<Acta> actas = client.target(URLHelper.getURL() + "/professor/" + email).request()
						.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
						});
				
				req.setAttribute("actas", actas);
				getServletContext().getRequestDispatcher("/professor.jsp").forward(req, res);
				return;
			}

		} else {
			// edita asignatura metiendo email y nombre en el rol correspondiente,
			// si ya existe alguien con el mismo rol vuelve a registro

			a = client.target(URLHelper.getURL_asignatura() + "/" + asig).request().accept(MediaType.APPLICATION_JSON)
					.get(new GenericType<Asignatura>() {
					});

			switch (rol) {
				case "coordinador":
					a.setEmail_coordinador(req.getParameter("email_coordinador"));
					a.setNombre_coordinador(req.getParameter("nombre_coordinador"));
					break;
				case "secretario":
					a.setNombre_secretario(req.getParameter("nombre_secretario"));
					a.setEmail_secretario(req.getParameter("email_secretario"));
	
					break;
				case "presidente":
					a.setNombre_presidente(req.getParameter("nombre_presidente"));
					a.setEmail_presidente(req.getParameter("email_presidente"));
	
					break;
				case "vocal":
					a.setNombre_vocal(req.getParameter("nombre_vocal"));
					a.setEmail_vocal(req.getParameter("email_vocal"));
					break;
				default:
					break;
			}

		}

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
