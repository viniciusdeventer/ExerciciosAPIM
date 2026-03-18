package model.plan;

import model.user.Student;

public class BasicPlan implements SubscriptionPlan {

    @Override
    public boolean canEnroll(Student student) {
        return student.getActiveEnrollmentsCount() < 3;
    }

}