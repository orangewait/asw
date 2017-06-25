package architectureSoftware;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@RestController
public class FacultyServiceController {
	
	@Value("${universityInfo}") 
	private String universityInfo;

	@Value("${facultyInfo}") 
	private String facultyInfo;
	
	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@RequestMapping("{university}")
	public String getUniversityInfo() {
		
		String[] infoArray = universityInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getUniversityInfo(): " + info);
		return info; 
		
	}

	@RequestMapping("{university}/{faculty}")
	 public String getFacultyInfo(){
		 
		String[] infoArray = facultyInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("FACULTYSERVICECONTROLLER : getFacultyInfo(): " + info);
		return info; 
		
	}
}
