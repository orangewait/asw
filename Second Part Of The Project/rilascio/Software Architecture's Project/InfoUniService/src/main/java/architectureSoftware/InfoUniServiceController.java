package architectureSoftware;

import java.net.URI;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.util.logging.Logger; 

@RestController
public class InfoUniServiceController {

	private final Logger logger = Logger.getLogger("architectureSoftware"); 

	@Autowired
	private InfoService infoService;
	
	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping("/infoUni/{university}")
	public String getInfoUni(@PathVariable String university) {
		
		String infoUni =  
			university + 
			" è stata fondata nel " + 
			infoService.getUniversityInfoByUniversityService() +
			" e ha "+
			infoService.getUniversityInfoByFacultyService() +
			" facoltà.";
		
		logger.info("getInfoUni(): " + infoUni);
		return infoUni; 
	}

	@RequestMapping("/infoUni/{university}/{faculty}")
	public String getInfoUni(@PathVariable String university,@PathVariable String faculty) {
		String infoUni =  
			university + 
			" è stata fondata nel " + 
			infoService.getUniversityInfoByUniversityService() + 
			" e " + 
			faculty +
			" ha un piano di studi di "
			+ infoService.getFacultyInfoByFacultyService() + 
			" esami per un totale di " 
			+ infoService.getFacultyInfoByCourseService() + 
			" crediti.";
		
		logger.info("getInfoUni(): " + infoUni);
		return infoUni; 
	}

	@RequestMapping("/infoUni/{university}/{faculty}/{course}")
	public String getInfoUni(@PathVariable String university,@PathVariable String faculty,@PathVariable String course) {
		String infoUni =  
			university + " è stata fondata nel " + infoService.getUniversityInfoByUniversityService() + ", " + 
			faculty + " ha un piano di studi di " + infoService.getFacultyInfoByFacultyService() + " esami con " + 
			course + " avente " + infoService.getCourseInfoByCourseService() + " crediti.";
		logger.info("getInfoUni(): " + infoUni);
		return infoUni; 
	}
	
	/*private String getInfo(String uri) {
		return new RestTemplate().getForObject(uri,String.class);
	}	
	
	private String getService(String service){
		ServiceInstance instance = loadBalancer.choose(service);
		   if (instance!=null) {
		      URI uri = instance.getUri();
		      if (uri!=null) {
		         return uri.toString();
		      }
		}
		   return null;
		}

	private String getUniversity(String names) {
		String universityUri=getService("university")+"university/"+names;
		return getInfo(universityUri);
	}	

	private String getFaculty(String names) {
		String facultyUri=getService("faculty")+"faculty/"+names;
		return getInfo(facultyUri);
	}	

	private String getCourse(String names) {
		String courseUri=getService("course")+"course/"+names;
		return getInfo(courseUri);
	}*/
}
