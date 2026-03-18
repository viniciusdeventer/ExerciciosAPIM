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

        userService.create(
            new Student(
                "Joao",
                "joao@email.com",
                new BasicPlan()
            )
        );

        userService.create(
            new Student(
                "Mariana",
                "mariana@email.com",
                new PremiumPlan()
            )
        );

        userService.create(
            new Student(
                "Lucas",
                "lucas@email.com",
                new BasicPlan()
            )
        );

        userService.create(
            new Student(
                "Fernanda",
                "fernanda@email.com",
                new PremiumPlan()
            )
        );
    }

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

        courseService.create(
            new Course(
                "Docker Essentials",
                "Containers from zero to hero",
                "Rafael Lima",
                20,
                "BEGINNER",
                "ACTIVE"
            )
        );

        courseService.create(
            new Course(
                "Kubernetes in Practice",
                "Orchestration and scaling",
                "Rafael Lima",
                35,
                "ADVANCED",
                "ACTIVE"
            )
        );

        courseService.create(
            new Course(
                "Database Design",
                "Modeling and optimization",
                "Juliana Rocha",
                25,
                "INTERMEDIATE",
                "ACTIVE"
            )
        );
    }

    private void createEnrollments() {

        Student vinicius =
                (Student) userService.getByEmail("vinicius@email.com");

        Student ana =
                (Student) userService.getByEmail("ana@email.com");

        Student joao =
                (Student) userService.getByEmail("joao@email.com");

        Student mariana =
                (Student) userService.getByEmail("mariana@email.com");

        Student lucas =
                (Student) userService.getByEmail("lucas@email.com");

        Student fernanda =
                (Student) userService.getByEmail("fernanda@email.com");

        Course java =
                courseService.getById(1);

        Course spring =
                courseService.getById(2);

        Course docker =
                courseService.getById(4);

        Course kubernetes =
                courseService.getById(5);

        Course database =
                courseService.getById(6);

        enrollmentService.enroll(vinicius, java);
        enrollmentService.enroll(vinicius, spring);

        enrollmentService.enroll(ana, java);
        enrollmentService.enroll(ana, spring);
        enrollmentService.enroll(ana, database);

        enrollmentService.enroll(joao, java);
        enrollmentService.enroll(joao, docker);

        enrollmentService.enroll(mariana, spring);
        enrollmentService.enroll(mariana, kubernetes);

        enrollmentService.enroll(lucas, docker);
        enrollmentService.enroll(lucas, database);

        enrollmentService.enroll(fernanda, kubernetes);
    }
}