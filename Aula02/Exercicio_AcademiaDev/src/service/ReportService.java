package service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.course.Course;
import model.enrollment.Enrollment;
import model.plan.SubscriptionPlan;
import model.user.Student;

public class ReportService {

    private final CourseService courseService;
    private final UserService userService;
    private final EnrollmentService enrollmentService;

    public ReportService(
            CourseService courseService,
            UserService userService,
            EnrollmentService enrollmentService
    ) {
        this.courseService = courseService;
        this.userService = userService;
        this.enrollmentService = enrollmentService;
    }
    
    public List<Course> getCoursesByDifficulty(String difficultyLevel) {

        return courseService.listAll()
                .stream()
                .filter(c -> c.getDifficultyLevel().equals(difficultyLevel))
                .sorted(Comparator.comparing(Course::getTitle))
                .toList();
    }
    
    public Set<String> getActiveCourseInstructors() {

        return courseService.listAll()
                .stream()
                .filter(c -> c.getStatus().equals("ACTIVE"))
                .map(Course::getInstructorName)
                .collect(Collectors.toSet());
    }
    
    public Map<SubscriptionPlan, List<Student>> groupStudentsByPlan() {

        return userService.getStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getSubscriptionPlan));
    }
    
    public double getAverageProgress() {

        return enrollmentService.listAll()
                .stream()
                .mapToInt(Enrollment::getProgress)
                .average()
                .orElse(0);
    }
    
    public Optional<Student> getTopStudentByActiveEnrollments() {

        return userService.getStudents()
                .stream()
                .max(Comparator.comparing(Student::getActiveEnrollmentsCount));
    }

}