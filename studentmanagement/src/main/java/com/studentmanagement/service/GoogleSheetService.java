package com.studentmanagement.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import com.studentmanagement.model.Student;

@Service
public class GoogleSheetService {

    private static final String SPREADSHEET_ID =
            "1aB8hqmaNRzJXOANLubfpBLWoFPFHRF5qwJbbacjkA-s";

    private static final String RANGE = "Sheet1!A2:F";

    private Sheets getSheetsService() throws Exception {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("google/service-account.json");

        if (inputStream == null) {
            throw new RuntimeException("service-account.json not found");
        }

        GoogleCredentials credentials = GoogleCredentials
                .fromStream(inputStream)
                .createScoped(
                        Collections.singleton(
                                SheetsScopes.SPREADSHEETS_READONLY));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("Student Management")
                .build();
    }

    public List<Student> getStudents() throws Exception {

        Sheets sheets = getSheetsService();

        ValueRange response = sheets.spreadsheets()
                .values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();

        List<List<Object>> rows = response.getValues();

        List<Student> students = new ArrayList<>();

        if (rows == null) {
            return students;
        }

        for (List<Object> row : rows) {

            Student student = new Student();

            student.setId(Integer.parseInt(row.get(0).toString()));
            student.setName(row.get(1).toString());
            student.setMarks(Double.parseDouble(row.get(2).toString()));
            student.setMobno(row.get(3).toString());
            student.setAddress(row.get(4).toString());
            student.setDob(row.get(5).toString());

            students.add(student);
        }

        return students;
    }
}