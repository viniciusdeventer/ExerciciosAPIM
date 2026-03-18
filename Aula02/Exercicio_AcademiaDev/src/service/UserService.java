package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.user.Student;
import model.plan.SubscriptionPlan;
import model.user.User;

public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public void create(User user) {

        if (user == null)
            throw new IllegalArgumentException("User cannot be null.");

        if (users.containsKey(user.getEmail()))
            throw new IllegalStateException("Email already in use by another user.");

        users.put(user.getEmail(), user);
    }

    public Collection<User> listAll() {
        return users.values();
    }

    public User getByEmail(String email) {
        return users.get(email);
    }

    public User getById(int id) {

        return users.values().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User authenticate(String email) {

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be blank.");

        return users.get(email);
    }

    public boolean changeStudentPlan(int studentId, SubscriptionPlan plan) {

        User user = getById(studentId);

        if (!(user instanceof Student student))
            return false;

        student.setSubscriptionPlan(plan);
        return true;
    }
    
    public List<Student> getStudents() {

        return users.values().stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .toList();
    }

}