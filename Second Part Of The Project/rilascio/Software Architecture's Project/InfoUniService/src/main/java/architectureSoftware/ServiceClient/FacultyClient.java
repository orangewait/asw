package architectureSoftware.ServiceClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("faculty")
public interface FacultyClient {

	@RequestMapping(value="/university",method=RequestMethod.GET)
	public String getUniversityInfo();
	
	@RequestMapping(value="/university/faculty",method=RequestMethod.GET)
	public String getFacultyInfo();
		
}
