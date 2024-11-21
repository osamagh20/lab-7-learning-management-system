package com.example.lab7.Service;

import com.example.lab7.Model.Courses;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CoursesService {
    ArrayList<Courses> coursesList = new ArrayList<>();

    public ArrayList<Courses> getCoursesList() {
        return coursesList;
    }
    public void addCoursesList(Courses course) {
        coursesList.add(course);

    }

    public boolean updateCourse(String id,Courses course) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getId().equals(id)) {
                coursesList.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getId().equals(id)) {
                coursesList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Courses getCourseById(String id) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getId().equals(id)) {
                return coursesList.get(i);
            }
        }
        return null;
    }


    public ArrayList<Courses> getCoursesActivated() {
        ArrayList<Courses> coursesActiveted = new ArrayList<>();
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).isActive()==true) {
                coursesActiveted.add(coursesList.get(i));
            }
        }
        if (coursesActiveted == null) {
            return null;
        }else {
            return coursesActiveted;
        }
    }



    public ArrayList<Courses> getCoursesNotActivated() {
        ArrayList<Courses> coursesNotActiveted = new ArrayList<>();
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).isActive()==false) {
                coursesNotActiveted.add(coursesList.get(i));
            }
        }
        if (coursesNotActiveted == null) {
            return null;
        }
        return coursesNotActiveted;
    }

    public ArrayList<Courses> getCoursesByDepartment(String department) {
        ArrayList<Courses> coursesByDepartment = new ArrayList<>();
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getDepartment().equals(department)) {
                coursesByDepartment.add(coursesList.get(i));
                return coursesByDepartment;
            }

        }

        return null;
    }

    public boolean changeLimitOfCourse(String id, int limit) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getId().equals(id)) {
                coursesList.get(i).setLimit(limit);
                return true;
            }

        }
        return false;
    }

    public boolean isActiveCourse(String id) {
        for (int i = 0; i < coursesList.size(); i++) {
            if (coursesList.get(i).getId().equals(id)) {
                coursesList.get(i).setActive(true);
                return true;
            }
        }
        return false;
    }
}
