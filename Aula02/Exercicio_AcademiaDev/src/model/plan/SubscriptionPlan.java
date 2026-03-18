package model.plan;

import model.user.Student;

public interface SubscriptionPlan {

    boolean canEnroll(Student student);

}