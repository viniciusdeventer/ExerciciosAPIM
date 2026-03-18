package util;

import model.course.Course;
import model.user.Admin;
import model.plan.BasicPlan;
import model.plan.PremiumPlan;

import model.user.Student;
import service.CourseService;
import service.EnrollmentService;
import service.UserService;

public class InitialData {

    private final CourseService courseService;
    private final UserService userService;
    private final EnrollmentService enrollmentService;

    public InitialData(
            CourseService courseService,
            UserService userService,
            EnrollmentService enrollmentService
    ) {
        this.courseService = courseService;
        this.userService = userService;
        this.enrollmentService = enrollmentService;
    }

    public void populate() {

        createUsers();
        createCourses();
        createEnrollments();
    }
    
    private void createUsers() {

        userService.create(
            new Admin(
                "Admin Master",
                "admin@academiadev.com"
            )
        );

        userService.create(
            new Student(
                "Vinicius",
                "vinicius@email.com",
                new BasicPlan()
            )
        );
        
        userService.create(
        	    new Student(
        	        "Ana",
        	        "ana@email.com",
        	        new PremiumPlan()
        	    )
    	);

    };
    
    private void createCourses() {

        courseService.create(
            new Course(
                "Java Fundamentals",
                "Learn Java from scratch",
                "Carlos Silva",
                40,
                "BEGINNER",
                "ACTIVE"
            )
        );

        courseService.create(
            new Course(
                "Spring Boot API",
                "Build REST APIs with Spring",
                "Maria Souza",
                30,
                "INTERMEDIATE",
                "ACTIVE"
            )
        );

        courseService.create(
            new Course(
                "Advanced Microservices",
                "Architecture and scalability",
                "Carlos Silva",
                50,
                "ADVANCED",
                "INACTIVE"
            )
        );

    }
    
    private void createEnrollments() {

        Student vinicius =
                (Student) userService.getByEmail("vinicius@email.com");

        Student ana =
                (Student) userService.getByEmail("ana@email.com");

        Course java =
                courseService.getById(1);

        Course spring =
                courseService.getById(2);

        enrollmentService.enroll(vinicius, java);

        enrollmentService.enroll(ana, java);
        enrollmentService.enroll(ana, spring);

    }

}

