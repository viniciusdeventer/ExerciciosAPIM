package service;

import java.util.ArrayList;
import java.util.List;

import model.course.Course;
import model.enrollment.Enrollment;
import model.user.Student;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public void enroll(Student student, Course course) {

        if (!course.isActive())
            throw new IllegalStateException("Course is inactive.");

        if (student.isEnrolledInCourse(course.getId()))
            throw new IllegalStateException("Student already enrolled.");

        if (!student.getSubscriptionPlan().canEnroll(student))
            throw new IllegalStateException("Subscription plan limit reached.");

        Enrollment enrollment = new Enrollment(student, course);

        student.addEnrollment(enrollment);
        enrollments.add(enrollment);
    }

    public List<Enrollment> listAll() {
        return List.copyOf(enrollments);
    }
    
    public void updateProgress(int enrollmentId, int progress) {

        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getId() == enrollmentId)
                .findFirst()
                .orElseThrow();

        enrollment.updateProgress(progress);
    }
    
    public void cancelEnrollment(int enrollmentId) {

        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getId() == enrollmentId)
                .findFirst()
                .orElseThrow();

        enrollment.cancel();
    }

}