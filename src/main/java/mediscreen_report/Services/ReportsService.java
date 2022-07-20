package mediscreen_report.Services;

import java.util.List;

import mediscreen_report.Models.Note;
import mediscreen_report.Models.Patient;

public interface ReportsService {
	
	public Patient getPatient(int patientId);

	public  List<Note> getNotes(int patientId);

	public  String getReportForPatientId(int patientId);

}
