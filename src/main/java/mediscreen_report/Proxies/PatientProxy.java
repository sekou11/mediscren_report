package mediscreen_report.Proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mediscreen_report.Models.Patient;

@FeignClient(name = "mspatient",url="http://localhost:8701")
public interface PatientProxy {
	@GetMapping("/patient/{id}")
	public Patient getPatient(@PathVariable int id);

}
