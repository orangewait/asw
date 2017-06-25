package architectureSoftware.ServiceClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("course")
public interface CourseClient {
	
	@RequestMapping(value="/university/faculty",method=RequestMethod.GET)
	public String getFacultyInfo();

	@RequestMapping(value="/university/faculty/course",method=RequestMethod.GET)
	public String getCourseInfo();
}
