package mediscreen_report.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mediscreen_report.Models.Note;
import mediscreen_report.Models.Patient;
import mediscreen_report.Services.ReportsService;

@RestController
public class ReportController {
	
	@Autowired
    ReportsService reportService;

    @GetMapping("/")
    public String index() {
        return "Greeting to report Microservice";
    }

    @GetMapping("/patient/{patientId}")
    public Patient GetPatient(@PathVariable int patientId) {
        return reportService.getPatient(patientId);
    }

    @GetMapping("/note/patient/{patientId}")
    public List<Note> GetNotes(@PathVariable int patientId) {
        return reportService.getNotes(patientId);
    }

    @GetMapping("/report/patient/{patientId}")
    public String getReportForPatientId(@PathVariable int patientId){
        return reportService.getReportForPatientId(patientId);
    }

}
