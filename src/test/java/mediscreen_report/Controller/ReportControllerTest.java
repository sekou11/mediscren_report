package mediscreen_report.Controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import mediscreen_report.Models.Note;
import mediscreen_report.Models.Patient;
import mediscreen_report.Proxies.NoteProxy;
import mediscreen_report.Proxies.PatientProxy;
import mediscreen_report.Services.ReportsService;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ReportControllerTest {

    @MockBean
    ReportsService reportService;

    @MockBean
    PatientProxy patientProxy;

    @MockBean
    NoteProxy noteProxy;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/note/patient/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPatient() throws Exception {
        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReport() throws Exception {
        Patient patient;
        patient = new Patient();
        patient.setId(1);
        patient.setFirstName("test");
        patient.setLastName("test");
        patient.setBirthdate(LocalDate.of(1972, 01, 01));
        patient.setSex("M");
        patient.setAddress("address");
        patient.setPhone("phone");

        Note note;
        note = new Note();
        note.setId("noteId");
        note.setPatientId(1);
        note.setNote("Hémoglobine A1C Microalbumine");

        Mockito.when(reportService.getPatient(1)).thenReturn(patient);
        Mockito.when(reportService.getReportForPatientId(1)).thenReturn(String.valueOf(Collections.singletonList(note)));

        mockMvc.perform(get("/report/patient/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(reportService).getReportForPatientId(1);

    }
}
