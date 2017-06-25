package architectureSoftware;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@RestController
public class FacultyServiceController {
	
	@Value("${uniInfo}") 
	private String uniInfo;

	@Value("${facInfo}") 
	private String facInfo;
	
	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@RequestMapping("faculty/{university}")
	public String getUniversityInfo(@PathVariable String university) {
		
		String[] infoArray = uniInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getUniversityInfo(): " + info);
		return info; 
	}

	@RequestMapping("faculty/{university}/{faculty}")
	public String getFacultyInfo(@PathVariable String university,@PathVariable String faculty) {
		
		String[] infoArray = facInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getFacultyInfo(): " + info);
		return info; 
	}
}
