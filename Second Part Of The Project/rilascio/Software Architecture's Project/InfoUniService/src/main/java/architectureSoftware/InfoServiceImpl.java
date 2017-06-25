package architectureSoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import architectureSoftware.ServiceClient.CourseClient;
import architectureSoftware.ServiceClient.FacultyClient;
import architectureSoftware.ServiceClient.UniversityClient;

@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private CourseClient courseClient;
	@Autowired
	private FacultyClient facultyClient;
	@Autowired
	private UniversityClient universityClient;

	@HystrixCommand(fallbackMethod="getFallbackUniversityInfoByUniversityService")
	public String getUniversityInfoByUniversityService() {return universityClient.getUniversityInfo();}

	@HystrixCommand(fallbackMethod="getFallbackUniversityInfoByFacultyService")
	public String getUniversityInfoByFacultyService(){return facultyClient.getUniversityInfo();}

	@HystrixCommand(fallbackMethod="getFallbackFacultyInfoByFacultyService")
	public String getFacultyInfoByFacultyService() {return facultyClient.getFacultyInfo();}

	@HystrixCommand(fallbackMethod="getFallbackFacultyInfoByCourseService")
	public String getFacultyInfoByCourseService() {return courseClient.getFacultyInfo();}

	@HystrixCommand(fallbackMethod="getFallbackCourseInfoByCourseService")
	public String getCourseInfoByCourseService() {return courseClient.getCourseInfo();}
	
	
	public String getFallbackUniversityInfoByUniversityService() { return "Error on subService UniversityService"; }
	public String getFallbackUniversityInfoByFacultyService() { return "Error on subService FacultyService"; }
	public String getFallbackFacultyInfoByFacultyService() { return "Error on subService FacultyServiceEEE"; }
	public String getFallbackFacultyInfoByCourseService() { return "Error on subService CourseService"; }
	public String getFallbackCourseInfoByCourseService() { return "Error on subService CourseService"; }
}
