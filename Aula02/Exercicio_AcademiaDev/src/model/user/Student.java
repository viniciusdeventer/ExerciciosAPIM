package model.user;

import java.util.ArrayList;
import java.util.List;

import model.enrollment.Enrollment;
import model.plan.SubscriptionPlan;

public class Student extends User {

    private SubscriptionPlan subscriptionPlan;
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String name, String email, SubscriptionPlan subscriptionPlan) {
        super(name, email);
        this.subscriptionPlan = subscriptionPlan;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public long getActiveEnrollmentsCount() {
        return enrollments.stream()
                .filter(Enrollment::isActive)
                .count();
    }

    public boolean isEnrolledInCourse(int courseId) {
        return enrollments.stream()
                .anyMatch(e -> e.getCourse().getId() == courseId && e.isActive());
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public String getUserRole() {
        return "Student";
    }

}