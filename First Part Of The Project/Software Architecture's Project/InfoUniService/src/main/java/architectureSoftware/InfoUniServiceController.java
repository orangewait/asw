package architectureSoftware;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

@RestController
public class InfoUniServiceController {

	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@Value("${info.university.uri}") 
	private String universityUri;

	@Value("${info.faculty.uri}") 
	private String facultyUri;

	@Value("${info.course.uri}") 
	private String courseUri;

	@RequestMapping("/infoUni/{university}")
	public String getInfoUni(@PathVariable String university) {
		String infoUni =  
			university + " è stata fondata nel " + getUniversity(university) +" e ha "+ getFaculty(university) + " facoltà.";
		logger.info("getInfoUni(): " + infoUni);
		return infoUni; 
	}

	@RequestMapping("/infoUni/{university}/{faculty}")
	public String getInfoUni(@PathVariable String university,@PathVariable String faculty) {
		String infoUni =  
			university + " è stata fondata nel " + getUniversity(university) + " e " + 
			faculty + " ha un piano di studi di " + getFaculty(university+"/"+faculty) + " esami per un totale di " +
			getCourse(university+"/"+faculty) + " crediti.";
		logger.info("getInfoUni(): " + infoUni);
		return infoUni; 
	}

	@RequestMapping("/infoUni/{university}/{faculty}/{course}")
	public String getInfoUni(@PathVariable String university,@PathVariable String faculty,@PathVariable String course) {
		String infoUni =  
			university + " è stata fondata nel " + getUniversity(university) + ", " + 
			faculty + " ha un piano di studi di " + getFaculty(university+"/"+faculty) + " esami con " + 
			course + " avente " + getCourse(university+"/"+faculty+"/"+course) + " crediti.";
		logger.info("getInfoUni(): " + infoUni);
		return infoUni; 
	}
	
	private String getInfo(String uri) {
		return new RestTemplate().getForObject(uri,String.class);
	}	

	private String getUniversity(String names) {
		String unUri=universityUri+names;
		return getInfo(unUri);
	}	

	private String getFaculty(String names) {
		String faUri=facultyUri+names;
		return getInfo(faUri);
	}	

	private String getCourse(String names) {
		String coUri=courseUri+names;
		return getInfo(coUri);
	}
}
