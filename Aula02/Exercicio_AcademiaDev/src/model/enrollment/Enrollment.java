package model.enrollment;

import model.course.Course;
import model.user.Student;
import util.IdGenerator;

public class Enrollment {

    private final int id;
    private int progress;
    private final Student student;
    private final Course course;
    private boolean active;

    public Enrollment(Student student, Course course) {

        if (student == null)
            throw new IllegalArgumentException("Student cannot be null.");

        if (course == null)
            throw new IllegalArgumentException("Course cannot be null.");

        this.id = IdGenerator.generate(Enrollment.class);
        this.student = student;
        this.course = course;
        this.progress = 0;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public int getProgress() {
        return progress;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
    }

    public void updateProgress(int progress) {

        if (progress < 0 || progress > 100)
            throw new IllegalArgumentException("Progress must be between 0 and 100");

        this.progress = progress;
    }

}