package architectureSoftware;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@RestController
public class CourseServiceController {
	
	@Value("${facultyInfo}") 
	private String facultyInfo;

	@Value("${courseInfo}") 
	private String courseInfo;
	
	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@RequestMapping("{university}/{faculty}")
	public String getFacultyInfo() {
		
		String[] infoArray = facultyInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getFacultyInfo(): " + info);
		return info; 
	}

	@RequestMapping("{university}/{faculty}/{course}")
	public String getCourseInfo() {
		
		String[] infoArray = courseInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getCourseInfo(): " + info);
		return info; 
		
	}
}
