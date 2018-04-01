package eagz.org;

import java.util.ArrayList;
import javax.jws.WebService;

@WebService(endpointInterface = "eagz.org.ServiceInterface")
public class Service implements ServiceInterface {

		public ArrayList<String> arr = new ArrayList<String>();	
		
		public void add(String string) {
			arr.add(string);
		}
		
		public String show() {
			return "data: " + arr.remove(0) + "\n\n";
		}
		
		public boolean check() {
			if (arr.size() == 0) {
				return false;
			} else {
				return true;
			}	
		}
}
