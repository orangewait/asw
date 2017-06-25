package architectureSoftware;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@RestController
public class CourseServiceController {
	
	@Value("${facInfo}") 
	private String facInfo;

	@Value("${couInfo}") 
	private String couInfo;
	
	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@RequestMapping("course/{university}/{faculty}")
	public String getFacultyInfo(@PathVariable String faculty) {
		
		String[] infoArray = facInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getFacultyInfo(): " + info);
		return info; 
	}

	@RequestMapping("course/{university}/{faculty}/{course}")
	public String getCourseInfo(@PathVariable String faculty,@PathVariable String course) {
		
		String[] infoArray = couInfo.split(",");
		int i = (int) (Math.round(Math.random()*(infoArray.length-1)));
		String info = infoArray[i];
		logger.info("getCourseInfo(): " + info);
		return info; 
	}
}
