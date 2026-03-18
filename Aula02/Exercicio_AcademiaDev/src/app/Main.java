package app;

import java.util.List;
import java.util.Scanner;

import model.course.Course;
import model.user.Admin;
import model.user.Student;
import model.user.User;
import service.CourseService;
import service.EnrollmentService;
import service.ReportService;
import service.SupportService;
import service.UserService;
import util.GenericCsvExporter;
import util.InitialData;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CourseService courseService = new CourseService();
        UserService userService = new UserService();
        EnrollmentService enrollmentService = new EnrollmentService();
        SupportService supportService = new SupportService();
        ReportService reportService =
                new ReportService(courseService, userService, enrollmentService);

        InitialData data =
                new InitialData(courseService, userService, enrollmentService);

        data.populate();

        while (true) {

            System.out.println("\n=== LOGIN ===");
            System.out.print("Email: ");
            String email = sc.nextLine();

            User user = userService.authenticate(email);

            if (user == null) {
                System.out.println("User not found");
                continue;
            }

            if (user instanceof Admin admin) {
                adminMenu(sc, admin, courseService, supportService, reportService);
            } else if (user instanceof Student student) {
                studentMenu(sc, student, courseService, enrollmentService, supportService);
            }
        }
    }

    private static void adminMenu(
            Scanner sc,
            Admin admin,
            CourseService courseService,
            SupportService supportService,
            ReportService reportService
    ) {

        while (true) {

            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1 - List courses");
            System.out.println("2 - Create course");
            System.out.println("3 - Activate course");
            System.out.println("4 - Deactivate course");
            System.out.println("5 - Reports");
            System.out.println("6 - Process ticket");
            System.out.println("0 - Logout");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> {
                    courseService.listAll()
                            .forEach(c -> System.out.println(
                                    c.getId() + " - " + c.getTitle() + " (" + c.getStatus() + ")"
                            ));
                }

                case 2 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    System.out.print("Instructor: ");
                    String instructor = sc.nextLine();

                    System.out.print("Workload: ");
                    int workload = Integer.parseInt(sc.nextLine());

                    System.out.print("Level: ");
                    String level = sc.nextLine();

                    courseService.create(
                            new Course(title, desc, instructor, workload, level, "INACTIVE")
                    );
                }

                case 3 -> {
                    System.out.print("Course ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    courseService.activate(id);
                }

                case 4 -> {
                    System.out.print("Course ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    courseService.deactivate(id);
                }

                case 5 -> reportsMenu(sc, reportService);

                case 6 -> {
                    var ticket = supportService.processNextTicket(admin);
                    System.out.println(ticket == null ? "No tickets" : "Processed: " + ticket.getTitle());
                }

                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void studentMenu(
            Scanner sc,
            Student student,
            CourseService courseService,
            EnrollmentService enrollmentService,
            SupportService supportService
    ) {

        while (true) {

            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1 - List courses");
            System.out.println("2 - Enroll");
            System.out.println("3 - My enrollments");
            System.out.println("4 - Open ticket");
            System.out.println("5 - Update Progress");
            System.out.println("0 - Logout");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 -> {
                    courseService.listAll()
                            .forEach(c -> System.out.println(
                                    c.getId() + " - " + c.getTitle() + " (" + c.getStatus() + ")"
                            ));
                }

                case 2 -> {
                    System.out.print("Course ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    Course course = courseService.getById(id);

                    try {
                        enrollmentService.enroll(student, course);
                        System.out.println("Enrolled successfully");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    student.getEnrollments()
                            .forEach(e -> System.out.println(
                                    e.getId() + " - " + e.getCourse().getTitle() + " - " + e.getProgress() + "%"
                            ));
                }

                case 4 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Message: ");
                    String msg = sc.nextLine();

                    supportService.openTicket(student, title, msg);
                }
                
                case 5 -> {
                    System.out.print("Enrollment: ");
                    int enrollment = Integer.parseInt(sc.nextLine());
                    
                    System.out.print("New Progress: ");
                    int newProgress = Integer.parseInt(sc.nextLine());
                    
                    enrollmentService.updateProgress(enrollment, newProgress);
                }

                case 0 -> {
                    return;
                }
            }
        }
    }

    private static void reportsMenu(Scanner sc, ReportService reportService) {

        while (true) {

            System.out.println("\n=== REPORTS ===");
            System.out.println("1 - Courses by level");
            System.out.println("2 - Active instructors");
            System.out.println("3 - Students by plan");
            System.out.println("4 - Average progress");
            System.out.println("5 - Top student");
            System.out.println("0 - Back");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {

	            case 1 -> {
	                System.out.print("Level: ");
	                String level = sc.nextLine();
	
	                var courses = reportService.getCoursesByDifficulty(level);
	
	                String csv = GenericCsvExporter.export(
	                        courses,
	                        List.of("id", "title", "status")
	                );
	
	                System.out.println("\n=== CSV ===");
	                System.out.println(csv);
	            }

	            case 2 -> {

	                var instructors = reportService.getActiveCourseInstructors();

	                String csv = GenericCsvExporter.export(
	                        instructors.stream()
	                                .map(name -> new Object() { String instructor = name; })
	                                .toList(),
	                        List.of("instructor")
	                );

	                System.out.println("\n=== CSV ===");
	                System.out.println(csv);
	            }

	            case 3 -> {

	                var data = reportService.groupStudentsByPlan();

	                var rows = data.entrySet().stream()
	                        .flatMap(entry -> entry.getValue().stream()
	                                .map(student -> new Object() {
	                                    String plan = entry.getKey().getClass().getSimpleName();
	                                    String name = student.getName();
	                                }))
	                        .toList();

	                String csv = GenericCsvExporter.export(
	                        rows,
	                        List.of("plan", "name")
	                );

	                System.out.println("\n=== CSV ===");
	                System.out.println(csv);
	            }

	            case 4 -> {

	                double avg = reportService.getAverageProgress();

	                var rows = List.of(new Object() {
	                    double average = avg;
	                });

	                String csv = GenericCsvExporter.export(
	                        rows,
	                        List.of("average")
	                );

	                System.out.println("\n=== CSV ===");
	                System.out.println(csv);
	            }

	            case 5 -> {

	                var rows = reportService.getTopStudentByActiveEnrollments()
	                        .map(s -> List.of(new Object() {
	                            String name = s.getName();
	                        }))
	                        .orElse(List.of());

	                String csv = GenericCsvExporter.export(
	                        rows,
	                        List.of("name")
	                );

	                System.out.println("\n=== CSV ===");
	                System.out.println(csv);
	            }

                case 0 -> {
                    return;
                }
            }
        }
    }
}