package eagz.org;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		URL url = new URL("http://localhost:8085/test?wsdl");
    		QName qname = new QName("http://org.eagz/", "ServiceService");
    		Service service = Service.create(url, qname);
    		ServiceInterface server = service.getPort(ServiceInterface.class);
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("text/event-stream");
    		if (server.check()) {
	    		response.getWriter().write("event:TEST \n\n");
	    		response.getWriter().write(server.show());
	    		response.getWriter().flush();
		    } else {
	    		response.getWriter().write("event:TEST \n\n");
	    		response.getWriter().write("data: no command sent\n\n");
	    		response.getWriter().flush();
		    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}