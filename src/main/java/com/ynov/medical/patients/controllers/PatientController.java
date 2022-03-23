package com.ynov.medical.patients.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.medical.patients.daos.IPatientDao;
import com.ynov.medical.patients.models.DoctorBody;
import com.ynov.medical.patients.models.Patient;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class PatientController {
    public static List<Patient> patients = new ArrayList<Patient>();

    static {
        Patient patient1 = new Patient("1", "patient1", 23);
        Patient patient2 = new Patient("2", "patient2", 24);
        Patient patient3 = new Patient("3", "patient3", 25);
        Patient patient4 = new Patient("4", "patient4", 26);

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);
    }

    static OkHttpClient client = new OkHttpClient();

    public static Map<String, Object> run(String url) throws IOException {
        Map<String, Object> responseData = new HashMap<String, Object>();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            responseData.put("result", response.body().string());
            responseData.put("code", response.code());
            return responseData;
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    Map<String, Object> post(String url, String json) throws IOException {
        Map<String, Object> responseData = new HashMap<String, Object>();
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            responseData.put("result", response.body().string());
            responseData.put("code", response.code());
            return responseData;
        }
    }

    @Autowired
    IPatientDao patientDao;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {

        return patientDao.findAll();
    }

    @GetMapping("/patients/{idPatient}")
    public Optional<Patient> getOnePatient(@PathVariable String idPatient) {

        // for (Patient patient : PatientController.patients) {
        // if (patient.getId().equals(idPatient)) {
        // return patient;
        // }
        // }
        return patientDao.findById(idPatient);
    }

    @GetMapping("/patientsByName/{namePatient}")
    public Patient getOnePatientByName(@PathVariable String namePatient) {

        // for (Patient patient : PatientController.patients) {
        // if (patient.getId().equals(idPatient)) {
        // return patient;
        // }
        // }
        return patientDao.findByName(namePatient);
    }

    @GetMapping("/doctors")
    public List<Map<String, String>> getDoctors(@RequestParam double latitude, @RequestParam double longitude, @RequestParam int distance,
            @RequestParam String speciality) throws IOException {
        List<Map<String, String>> doctors = new ArrayList<Map<String, String>>();

        //Add position params and speciality
        Map<String, Object> response = run("http://localhost:7070/doctors");
        final JSONArray data = new JSONArray(response.get("result").toString());
        final int n = data.length();

        for (int i = 0; i < n; ++i) {
            JSONObject doctor = data.getJSONObject(i);
            Map<String, String> doctorInfo = new HashMap<String, String>();
            doctorInfo.put("name", doctor.getString("name"));
            doctorInfo.put("age", String.valueOf(doctor.getLong("age")));
            doctorInfo.put("speciality", doctor.getString("speciality"));
            doctors.add(doctorInfo);
        }

        return doctors;
    }

    @PostMapping("patients")
    public boolean addPatient(@RequestBody Patient patient) {
        patientDao.save(patient);
        return true;
    }



    @PostMapping("addPatientToDoctor")
    public boolean addDoctor(@RequestBody DoctorBody body) throws IOException {
        // JSONObject parsedBody = new JSONObject(body);
        // String idPatient = parsedBody.getString("patient");
        // String idDoctor = parsedBody.getString("doctor");
        String idDoctor = body.getDoctor();
        String idPatient = body.getPatient();
        Optional<Patient> patient = patientDao.findById(idPatient);
        if (patient.isPresent()) {
            String json = "{\r\n" +
                    " \"doctor\" : \"" + idDoctor + "\",\r\n" +
                    " \"patient\" : \"" + idPatient + "\"\r\n" +
                    "}";
            Map<String, Object> response = post("http://localhost:7070/addPatientToDoctor", json);
            if (response.get("code").equals(200)) {
                Patient updatePatient = patient.get();
                updatePatient.setDoctor(idDoctor);
                patientDao.save(updatePatient);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
