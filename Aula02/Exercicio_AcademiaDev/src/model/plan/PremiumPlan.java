package model.plan;

import model.user.Student;

public class PremiumPlan implements SubscriptionPlan {

    @Override
    public boolean canEnroll(Student student) {
        return true;
    }

}