package architectureSoftware;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@RestController
public class UniversityServiceController {
	
	@Value("${info}") 
	private String info;
	
	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@RequestMapping("university/{university}")
	public String getUniversityInfo(@PathVariable String university) {
		
		String[] infoArray = info.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getUniversityInfo(): " + info);
		return info; 
	}
}
