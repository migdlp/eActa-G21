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
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.isst.acta.model.Acta;
import es.upm.dit.isst.acta.model.Asignatura;

/**
 * Servlet implementation class FirmaServlet
 */
@WebServlet("/FirmaServlet")
public class FirmaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirmaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String profesor = req.getParameter("profesor");
		System.out.println(profesor);
		Client client = ClientBuilder.newClient(new ClientConfig());
		Acta acta = client
				.target(URLHelper.getURL() + "/" + req.getParameter("email_alumno")
						+ req.getParameter("asignatura"))
				.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<Acta>() {
				});
		System.out.println(acta.getId());
		if (acta.getEmail_secretario() != null) {
			if (acta.getEmail_secretario().equals(profesor)) {
				acta.setFirma_secretario(true);
			}
		}

		if (acta.getEmail_vocal() != null) {
			if (acta.getEmail_vocal().equals(profesor)) {
				acta.setFirma_vocal(true);
			}
		}
		if (acta.getEmail_presidente() != null) {
			if (acta.getEmail_presidente().equals(profesor)) {
				acta.setFirma_presidente(true);
			}
		}
		
		client.target(URLHelper.getURL() + "/" + acta.getId()).request()
		.post(Entity.entity(acta, MediaType.APPLICATION_JSON), Response.class);
		
//		Envia a la vista de profesor
		
		req.setAttribute("profesor", profesor);
		Asignatura asignatura = client.target(URLHelper.getURL_asignatura() + "/professor/" + profesor).request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<Asignatura>() {
				});
		req.setAttribute("asignatura", asignatura);

		List<Acta> actas = client.target(URLHelper.getURL() + "/professor/" + profesor).request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
				});
		req.setAttribute("actas", actas);

		getServletContext().getRequestDispatcher("/Professor.jsp").forward(req, res);

	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
