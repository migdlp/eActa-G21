package es.upm.dit.isst.acta.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
 * Servlet implementation class FormAdminServlet
 */
@WebServlet("/FormDefinitivaServlet")
public class FormDefinitivaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// autorizacion
		String email = req.getParameter("email_alumno");
		String asig = req.getParameter("asignatura");
		String profe = req.getParameter("profesor");

		String id = email + asig;
		System.out.print(id);

		Client client = ClientBuilder.newClient(new ClientConfig());
		Acta acta = null;
		try {
			acta = client.target(URLHelper.getURL() + "/" + id).request().accept(MediaType.APPLICATION_JSON)
					.get(Acta.class);
			System.out.print("Vale, tenemos acta");

		} catch (Exception e) {

		}

		System.out.print("El acta alomejor es nulo!");

		if (acta != null) {

			System.out.print("El acta no es nulo!");

			switch (req.getParameter("status")) {

			case "1":
				if (acta.getStatus() == 1) {
					// acta.setStatus(acta.getStatus()+1);
					if (req.getParameter("nota") != null) {
						String m = req.getParameter("nota").replace(",", ".");
						acta.setNota(Double.parseDouble(m));
						enviaCorreo(email, 1, asig, Double.parseDouble(req.getParameter("nota")),
								req.getParameter("nombre_alumno"));
						client.target(URLHelper.getURL() + "/" + id).request()
								.post(Entity.entity(acta, MediaType.APPLICATION_JSON), Response.class);
					}
				}
				break;
			case "2":
				if (req.getParameter("nota") != null) {

					String m = req.getParameter("nota").replace(",", ".");
					acta.setNota(Double.parseDouble(m));
					acta.setEs_definitiva(true);
					acta.setStatus(2);
					enviaCorreo(email, 2, asig, Double.parseDouble(req.getParameter("nota")),
							req.getParameter("nombre_alumno"));
					client.target(URLHelper.getURL() + "/" + id).request()
							.post(Entity.entity(acta, MediaType.APPLICATION_JSON), Response.class);
					System.out.print("En teoria se ha actualizado");
				}
			default:
				System.out.print("por que no se actualiza :(");

				break;
			}
			List<Acta> actas = client.target(URLHelper.getURL() + "/professor/" + acta.getEmail_coordinador()).request()
					.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
					});
			req.setAttribute("actas", actas);

		}

		Asignatura asignatura = client
				.target(URLHelper.getURL_asignatura() + "/professor/" + acta.getEmail_coordinador()).request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<Asignatura>() {
				});
		req.setAttribute("profesor", profe);
		req.setAttribute("asignatura", asignatura);
		getServletContext().getRequestDispatcher("/Professor.jsp").forward(req, resp);
	}

	public void enviaCorreo(String destino, int nivel, String asignatura, double nota, String nombre_destino) {

		System.out.print("parece que si se va a enviar");

		final String remitente = "eacta.noreply"; // Para la dirección nomcuenta@gmail.com
		// String email = req.getParameter("profe");
		String mail = destino;
		String asunto;
		String cuerpo1;
		String cuerpo2;
		String cuerpo;
		switch (nivel) {
		case 1:
			asunto = "Actualización de notas provisionales: " + asignatura;
			cuerpo1 = "Estimado " + nombre_destino + ", ";
			cuerpo2 = "Le comunicamos que su nota provisional en la asignatura de " + asignatura + " es de un " + nota
					+ ".";
			cuerpo = cuerpo1 + cuerpo2;
			break;
		default:
			asunto = "Publicación de notas definitivas: " + asignatura;
			cuerpo1 = "Estimado " + nombre_destino + ", ";
			cuerpo2 = "le comunicamos que su nota definitiva en la asignatura de " + asignatura
					+ ", a la espera del cierra de Actas; es de un " + nota + ".";
			cuerpo = cuerpo1 + cuerpo2;
			break;
		}

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", mail);
		props.put("mail.smtp.clave", "Root21root"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, mail); // Se podrían añadir varios de la misma manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "Root21root");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException me) {
			me.printStackTrace();
			System.out.println("No se envio");
			// Si se produce un error
		}

	}

}
// autorizacion
// if (acta.getStatus() == 1 || acta.getStatus() == 4 || acta.getStatus() == 6)
// {
// acta.setStatus(acta.getStatus()+1);
// if (req.getParameter("nota") != null) {
// String m = req.getParameter("nota").replace(",", ".");
// acta.setNota(Double.parseDouble(m));
// acta.setEs_definitiva(true);
// }
//
// client.target(URLHelper.getURL()+ "/" + email).request()
// .post(Entity.entity(acta, MediaType.APPLICATION_JSON), Response.class);
// }
//
//
// List<Acta> actas = client.target(URLHelper.getURL() + "/professor/"+
// acta.getEmail_coordinador())
// .request().accept(MediaType.APPLICATION_JSON)
// .get(new GenericType<List<Acta>>() {});
// req.setAttribute("actas", actas);
// }
//
// getServletContext().getRequestDispatcher("/Professor.jsp").forward(req,resp);
// }
// }
