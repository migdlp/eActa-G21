package es.upm.dit.isst.acta.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.glassfish.jersey.client.ClientConfig;

import es.upm.dit.isst.acta.model.Acta;
import es.upm.dit.isst.acta.model.Asignatura;

/**
 * Servlet implementation class CreaACTAPDFServlet
 */
@WebServlet("/CreaACTAPDFServlet")
public class CreaACTAPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaACTAPDFServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpClient client = ClientBuilder.newClient(new ClientConfig()ServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		String email = req.getParameter("email");
		
		try (PDDocument document = new PDDocument()) {
			ByteArrayOutputStream output =new ByteArrayOutputStream();
			
			PDPage page = new PDPage(PDRectangle.A6);
			document.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			Acta acta = client
					.target(URLHelper.getURL() + "/" + req.getParameter("email_alumno")
							+ req.getParameter("asignatura"))
					.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<Acta>() {
					});
			String firma_vocal = acta.isFirma_vocal() ? " ha firmado" : " no ha firmado";
			String firma_secretario = acta.isFirma_secretario() ? " ha firmado" : " no ha firmado";
			String firma_presidente = acta.isFirma_presidente() ? " ha firmado" : " no ha firmado";
			// Text
			int interlineado = -25;
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
			contentStream.newLineAtOffset(20, page.getMediaBox().getHeight() - 52);
			contentStream.showText("Acta de la asignatura: "+ req.getParameter("asignatura"));
			contentStream.newLineAtOffset(0,interlineado);
			contentStream.showText("Alumno: "+ req.getParameter("nombre_alumno"));
			contentStream.newLineAtOffset(0,interlineado);
			contentStream.showText("Nota: "+ req.getParameter("nota"));
			contentStream.newLineAtOffset(0,interlineado*2);
			contentStream.showText("Coordinador: "+ req.getParameter("email_coordinador"));
			contentStream.newLineAtOffset(0,interlineado);
			contentStream.showText("Presidente: "+ req.getParameter("email_presidente")+ firma_presidente);
			contentStream.newLineAtOffset(0,interlineado);
			contentStream.showText("Secretario: "+ req.getParameter("email_secretario")+ firma_secretario);
			contentStream.newLineAtOffset(0,interlineado);
			contentStream.showText("Vocal: "+ req.getParameter("email_vocal")+ firma_vocal);
			
			
			contentStream.endText();

			// Image
//			PDImageXObject image = PDImageXObject.createFromByteArray(document,
//					IOUtils.toByteArray(CreaACTAPDFServlet.class.getResourceAsStream("/java.png")), "Java Logo");
//			contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);

			contentStream.close();

			document.save(output);
			
			//DESCARGA ACTA.PDF 	
			File resultFile = File.createTempFile("Acta-"+acta.getAsignatura()+"-"+ acta.getNombre_alumno()+"-",".pdf");
	       
	       
			try(OutputStream outputStream = new FileOutputStream(resultFile)) {
	            output.writeTo(outputStream);
	            
	        }
			System.out.println("Please find your PDF File here: " + resultFile.getAbsolutePath());
			
//			if ( (document != null)){
//				resp.setContentType("application/pdf");
//				resp.setHeader("Content-Disposition"
//						, String.format("attachment; filename=\"%s\"", "acta.pdf"));
//				resp.setContentLength(document.length);
//				resp.getOutputStream().write(document);
//			}
			
		}
		
//		Envia a la vista de profesor
		req.setAttribute("profesor", email);
		Asignatura asignatura = client.target(URLHelper.getURL_asignatura() + "/professor/" + email).request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<Asignatura>() {
				});
		req.setAttribute("asignatura", asignatura);

		List<Acta> actas = client.target(URLHelper.getURL() + "/professor/" + email).request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Acta>>() {
				});
		req.setAttribute("actas", actas);

		getServletContext().getRequestDispatcher("/Professor.jsp").forward(req, resp);

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
