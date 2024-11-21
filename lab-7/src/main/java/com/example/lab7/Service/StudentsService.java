package com.example.lab7.Service;

import com.example.lab7.Model.Students;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentsService {
    ArrayList<Students> studentsList = new ArrayList<>();

    public ArrayList<Students> getStudentsList() {
        return studentsList;
    }

    public void addStudents(Students student) {
       studentsList.add(student);
    }
    public boolean updateStudents(String id,Students student) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId().equals(id)) {
                studentsList.set(i, student);
                return true;
            }
        }
        return false;
    }


    public boolean deleteStudents(String id) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId().equals(id)) {
                studentsList.remove(i);
                return true;
            }
        }
        return false;
    }

    public double viewGPA(String id) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId().equals(id)) {
                return studentsList.get(i).getGPA();
            }
        }
        return 0.0;
    }

    public String viewCourses(String id) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId().equals(id)) {
                return studentsList.get(i).getCourses();
            }
        }
        return null;
    }

    public boolean changeCourses(String id,String courses) {
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId().equals(id)) {
                studentsList.get(i).setCourses(courses);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Students> getStudentsGreaterThanGPA(double gpa) {
        ArrayList<Students> studentsByGPA= new ArrayList<>();
        for (int i = 0; i < studentsList.size(); i++) {
            if(studentsList.get(i).getGPA()>=gpa){
                studentsByGPA.add(studentsList.get(i));
                return studentsByGPA;
            }
        }
        return null;
    }
}
