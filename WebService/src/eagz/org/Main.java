package eagz.org;


import javax.servlet.ServletException;
import javax.xml.ws.Endpoint;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {
	public static Service s = new Service();
	
	public static void main(String[] args) throws LifecycleException {
		try {
			Integer port = 8080;
			Tomcat tomcat = new Tomcat();
			Context ctxt = tomcat.addContext("/", System.getProperty("user.dir"));
			String webService = "http://localhost:8090/test";
			tomcat.setPort(port);
			
			tomcat.addWebapp("/App", System.getProperty("user.dir") +"\\" +"WebContent");
			Tomcat.addServlet(ctxt, "Servlet", new Servlet());
			ctxt.addServletMappingDecoded("/Soap", "Servlet");
			Endpoint.publish(webService, s);
			
			tomcat.start();
			tomcat.getServer().await();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}

