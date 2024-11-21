package com.example.lab7.Service;

import com.example.lab7.Model.Instructors;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstructorsService {
    ArrayList<Instructors> instructorsList = new ArrayList<>();

    public ArrayList<Instructors> getInstructorsList() {
        return instructorsList;
    }

    public void addInstructor(Instructors instructor) {
        instructorsList.add(instructor);
    }

    public boolean updateInstructor(String id, Instructors instructor) {
        for (int i = 0; i < instructorsList.size(); i++) {
            if (instructorsList.get(i).getId().equals(id)) {
                instructorsList.set(i, instructor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteInstructor(String id) {
        for (int i = 0; i < instructorsList.size(); i++) {
            if (instructorsList.get(i).getId().equals(id)) {
                instructorsList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Instructors getInstructorById(String id) {
        for (int i = 0; i < instructorsList.size(); i++) {
            if (instructorsList.get(i).getId().equals(id)) {
                return instructorsList.get(i);
            }
        }
        return null;
    }

    public String viewCoursesInstructor(String id) {
        for (int i = 0; i < instructorsList.size(); i++) {
            if (instructorsList.get(i).getId().equals(id)) {
                return instructorsList.get(i).getTeachCourse();
            }
        }
        return null;
    }

    public ArrayList<Instructors> viewInstructorsByDepartment(String department) {
        ArrayList<Instructors> instructorsByDepartment = new ArrayList<>();
        for (int i = 0; i < instructorsList.size(); i++) {
            if (instructorsList.get(i).getDepartment().equals(department)) {
                instructorsByDepartment.add(instructorsList.get(i));
                return instructorsByDepartment;
            }
        }
        return null;
    }

    public boolean  changeInstructorDepartment(String id,String department) {
        for (int i = 0; i < instructorsList.size(); i++) {
            if (instructorsList.get(i).getId().equals(id)) {
                instructorsList.get(i).setDepartment(department);
                return true;
            }
        }
        return false;
    }

}
