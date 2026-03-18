package service;

import java.util.ArrayList;
import java.util.List;

import model.course.Course;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public void create(Course course) {

        if (course == null)
            throw new IllegalArgumentException("Course cannot be null.");

        boolean exists = courses.stream()
                .anyMatch(c -> c.getTitle().equalsIgnoreCase(course.getTitle()));

        if (exists)
            throw new IllegalStateException("Course title must be unique.");

        courses.add(course);
    }

    public List<Course> listAll() {
        return List.copyOf(courses);
    }

    public Course getById(int id) {
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean delete(int id) {

        Course course = getById(id);

        if (course == null)
            return false;

        return courses.remove(course);
    }

    public boolean update(int id, String title, String description, String instructorName) {

        Course course = getById(id);

        if (course == null)
            return false;

        course.setTitle(title);
        course.setDescription(description);
        course.setInstructorName(instructorName);

        return true;
    }

    public boolean activate(int id) {

        Course course = getById(id);

        if (course == null)
            return false;

        course.setStatus("ACTIVE");

        return true;
    }

    public boolean deactivate(int id) {

        Course course = getById(id);

        if (course == null)
            return false;

        course.setStatus("INACTIVE");

        return true;
    }
}