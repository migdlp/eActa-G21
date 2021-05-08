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
		String emailcoordinador = (String) req.getSession().getAttribute("profesor");
		System.out.println(emailcoordinador);
		if (emailcoordinador.indexOf("upm.es") > 0) {
			Acta acta = new Acta();
			
			acta.setEmail_alumno(req.getParameter("emailalumno"));
			acta.setNota(Double.parseDouble(req.getParameter("notaprovisional")));
			acta.setId(req.getParameter("emailalumno")+req.getParameter("asignatura"));
			acta.setStatus(1);
			acta.setAsignatura((req.getParameter("asignatura")));
			acta.setEmail_coordinador(emailcoordinador);
			//aqui se cogería el email vocal, secretario, presidente
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