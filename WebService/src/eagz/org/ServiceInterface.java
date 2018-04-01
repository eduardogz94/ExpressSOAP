package eagz.org;
import javax.jws.WebService;

@WebService
public interface ServiceInterface {
		void add(String string);
		String show();
		boolean check();
}
