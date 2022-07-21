package mediscreen_report.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mediscreen_report.Models.Note;
import mediscreen_report.Models.Patient;
import mediscreen_report.Proxies.NoteProxy;
import mediscreen_report.Proxies.PatientProxy;
import static java.time.temporal.ChronoUnit.YEARS;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	PatientProxy patientProxy;

	@Autowired
	NoteProxy noteProxy;

	@Override
	public Patient getPatient(int patientId) {
		return patientProxy.getPatient(patientId);
	}

	@Override
	public List<Note> getNotes(int patientId) {
		return noteProxy.getNotesForPatientId(patientId);
	}

	@Override
	public String getReportForPatientId(int patientId) {
		Patient patient = patientProxy.getPatient(patientId);
		List<Note> notes = noteProxy.getNotesForPatientId(patientId);

		List<String> triggers = new ArrayList<>();
		triggers.add("Hémoglobine A1C");
		triggers.add("Microalbumine");
		triggers.add("Taille");
		triggers.add("Poids");
		triggers.add("Fumeur");
		triggers.add("Anormal");
		triggers.add("Cholestérol");
		triggers.add("Vertige");
		triggers.add("Rechute");
		triggers.add("Réaction");
		triggers.add("Anticorps");

		int nbTrigger = triggers.stream().mapToInt(term -> (int) notes.stream()
				.filter(note -> note.getNote().toLowerCase().contains(term.toLowerCase())).count()).sum();

		if (nbTrigger == 0) {
			return "none";
		}

		if ((patient.getBirthdate().until(LocalDate.now(), YEARS) > 30 && nbTrigger >= 8)
				|| (patient.getBirthdate().until(LocalDate.now(), YEARS) <= 30 && patient.getSex().equals("F")
						&& nbTrigger >= 7)
				|| (patient.getBirthdate().until(LocalDate.now(), YEARS) <= 30 && patient.getSex().equals("M")
						&& nbTrigger >= 5)) {
			return "Early onset";
		} else if ((patient.getBirthdate().until(LocalDate.now(), YEARS) > 30 && nbTrigger >= 6)
				|| (patient.getBirthdate().until(LocalDate.now(), YEARS) <= 30 && patient.getSex().equals("F")
						&& nbTrigger >= 4)
				|| (patient.getBirthdate().until(LocalDate.now(), YEARS) <= 30 && patient.getSex().equals("M")
						&& nbTrigger >= 3)) {
			return "Danger";
		} else if (patient.getBirthdate().until(LocalDate.now(), YEARS) > 30 && nbTrigger >= 2) {
			return "Borderline";
		}

		return "not defined";
	}

}
