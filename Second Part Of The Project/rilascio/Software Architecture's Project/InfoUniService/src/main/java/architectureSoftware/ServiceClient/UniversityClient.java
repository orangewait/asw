package architectureSoftware.ServiceClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("university")
public interface UniversityClient {

	@RequestMapping(value="/university",method=RequestMethod.GET)
	public String getUniversityInfo();
}
