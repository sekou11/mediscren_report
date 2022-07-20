package mediscreen_report.Proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mediscreen_report.Models.Note;

@FeignClient(name = "msnote", url = "localhost:8702")
public interface NoteProxy {
	
	 @GetMapping("/note/patient/{patientId}")
	 public List<Note> getNotesForPatientId(@PathVariable int patientId);

}
