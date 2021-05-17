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
		String email = req.getParameter("profesor");
		String rol = req.getParameter("rol");
		String asig = req.getParameter("asignatura-existe");
		String nombre = req.getParameter("nombre");

		req.setAttribute("profesor", email);
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
			
			
				// req.getSession().setAttribute("asignatura", a);
				List<Acta> actas = client.target(URLHelper.getURL() + "/professor/" + email).request()
						.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
						});
				Asignatura asignatura = client.target(URLHelper.getURL_asignatura() + "/professor/" + email).request()
						.accept(MediaType.APPLICATION_JSON).get(new GenericType<Asignatura>() {
						});
				req.setAttribute("asignatura", asignatura);
				req.setAttribute("actas", actas);
				getServletContext().getRequestDispatcher("/Professor.jsp").forward(req, res);
				return;
			

		} else {
			
			// YA EXISTE LA ASIGNATURA
			// edita asignatura metiendo email y nombre en el rol correspondiente,

			a = client.target(URLHelper.getURL_asignatura() + "/" + asig).request().accept(MediaType.APPLICATION_JSON)
					.get(new GenericType<Asignatura>() {
					});

			switch (rol) {
			case "coordinador":
				a.setEmail_coordinador(email);
				a.setNombre_coordinador(nombre);
				break;
			case "secretario":
				a.setNombre_secretario(nombre);
				a.setEmail_secretario(email);

				break;
			case "presidente":
				a.setNombre_presidente(nombre);
				a.setEmail_presidente(email);

				break;
			case "vocal":
				a.setNombre_vocal(nombre);
				a.setEmail_vocal(email);
				break;
			default:
				break;
			}
			Response r = client.target(URLHelper.getURL_asignatura() + "/" + asig).request()
					.post(Entity.entity(a, MediaType.APPLICATION_JSON), Response.class);
			if (r.getStatus() == 200) {
				// actualiza las actas de la asignatura
				List<Acta> actas = client.target(URLHelper.getURL()).request().accept(MediaType.APPLICATION_JSON)
						.get(new GenericType<List<Acta>>() {
						});

				for (int i = 0; i < actas.size(); i++) {
					if (actas.get(i).getAsignatura() != null) {
						if (actas.get(i).getAsignatura().equals(asig)) {
					
							switch (rol) {
							case "coordinador":
								actas.get(i).setEmail_coordinador(email);
								actas.get(i).setNombre_coordinador(nombre);
								break;
							case "secretario":
								actas.get(i).setNombre_secretario(nombre);
								actas.get(i).setEmail_secretario(email);
								break;
							case "presidente":
								actas.get(i).setNombre_presidente(nombre);
								actas.get(i).setEmail_presidente(email);
								break;
							case "vocal":
								actas.get(i).setNombre_vocal(nombre);
								actas.get(i).setEmail_vocal(email);
								break;
							default:
								break;
							}
							String id = actas.get(i).getId();
							client.target(URLHelper.getURL() + "/" + id).request()
									.post(Entity.entity(actas.get(i), MediaType.APPLICATION_JSON), Response.class);
						} else {
							actas.remove(i--);
						}
					} else {
						actas.remove(i--);
					}
				}
				req.setAttribute("actas", actas);
				Asignatura asignatura = client.target(URLHelper.getURL_asignatura() + "/professor/" + email).request()
						.accept(MediaType.APPLICATION_JSON).get(new GenericType<Asignatura>() {
						});
				req.setAttribute("asignatura", asignatura);
				getServletContext().getRequestDispatcher("/Professor.jsp").forward(req, res);
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
