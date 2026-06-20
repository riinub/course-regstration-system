 //Course Registration System

import java.util.*;
import java.io.*;

class Course {
    private String courseId;
    private String courseName;
    private int maxSeats;
    ArrayList<String> enrolledStudents;

    public Course(String courseId, String courseName, int maxSeats) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxSeats = maxSeats;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxSeats;
    }

    public void addStudent(String studentId) throws Exception {
        if (isFull()) {
            throw new Exception("Course is full.");
        }
        enrolledStudents.add(studentId);
    }

    public void removeStudent(String studentId) {
        enrolledStudents.remove(studentId);
    }

    public void displayCourse() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Enrolled Students: " + enrolledStudents.size() + "/" + maxSeats);
    }

    public void saveToFile(PrintWriter writer) {
        writer.println(courseId);
        writer.println(courseName);
        writer.println(maxSeats);
        writer.println(enrolledStudents.size());
        for (String sid : enrolledStudents) {
            writer.println(sid);
        }
    }

    public static Course loadFromFile(Scanner sc) {
        String id = sc.nextLine();
        String name = sc.nextLine();
        int max = Integer.parseInt(sc.nextLine());
        int count = Integer.parseInt(sc.nextLine());
        Course course = new Course(id, name, max);
        for (int i = 0; i < count; i++) {
            course.enrolledStudents.add(sc.nextLine());
        }
        return course;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private ArrayList<String> registeredCourses;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void registerCourse(String courseId) {
        registeredCourses.add(courseId);
    }

    public void deregisterCourse(String courseId) {
        registeredCourses.remove(courseId);
    }

    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Registered Courses: " + registeredCourses);
    }

    public void saveToFile(PrintWriter writer) {
        writer.println(studentId);
        writer.println(studentName);
        writer.println(registeredCourses.size());
        for (String cid : registeredCourses) {
            writer.println(cid);
        }
    }

    public static Student loadFromFile(Scanner sc) {
        String id = sc.nextLine();
        String name = sc.nextLine();
        int courseCount = Integer.parseInt(sc.nextLine());
        Student student = new Student(id, name);
        for (int i = 0; i < courseCount; i++) {
            student.registerCourse(sc.nextLine());
        }
        return student;
    }
}

class Registrar {
    public void register(Student student, Course course) throws Exception {
        if (course.isFull()) {
            throw new Exception("Cannot register. Course is full.");
        }
        course.addStudent(student.getStudentId());
        student.registerCourse(course.getCourseId());
    }

    public void deregister(Student student, Course course) {
        course.removeStudent(student.getStudentId());
        student.deregisterCourse(course.getCourseId());
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Registrar registrar = new Registrar();

        HashMap<String, Student> students = new HashMap<>();
        HashMap<String, Course> courses = new HashMap<>();

        // Load students from file
        try {
            File studentFile = new File("students.txt");
            if (studentFile.exists()) {
                Scanner sfile = new Scanner(studentFile);
                while (sfile.hasNextLine()) {
                    Student s = Student.loadFromFile(sfile);
                    students.put(s.getStudentId(), s);
                }
                sfile.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to load students: " + e.getMessage());
        }

        // Load courses from file
        try {
            File courseFile = new File("courses.txt");
            if (courseFile.exists()) {
                Scanner cfile = new Scanner(courseFile);
                while (cfile.hasNextLine()) {
                    Course c = Course.loadFromFile(cfile);
                    courses.put(c.getCourseId(), c);
                }
                cfile.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to load courses: " + e.getMessage());
        }

        // Add predefined courses if not already in file
        if (!courses.containsKey("CS01")) {
            courses.put("CS01", new Course("CS01", "Object Oriented Programming", 30));
        }
        if (!courses.containsKey("CS02")) {
            courses.put("CS02", new Course("CS02", "Data Structures", 25));
        }
        if (!courses.containsKey("CS03")) {
            courses.put("CS03", new Course("CS03", "Database Systems", 20));
        }
          if (!courses.containsKey("CS04")) {
            courses.put("CS04", new Course("CS04", "Programming Fundamentals",20));
        }

        int choice;
        do {
            System.out.println("\n----- Course Registration Menu -----");
            System.out.println("1. Register a student in a course");
            System.out.println("2. Deregister a student from a course");
            System.out.println("3. Display student info");
            System.out.println("4. Display course info");
            System.out.println("5. Show all available courses");
            System.out.println("6. Exit"); // New option
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String sid = sc.nextLine();
                        System.out.print("Enter Student Name : ");
                        String sname = sc.nextLine();
                        System.out.print("Enter Course ID: ");
                        String cid = sc.nextLine();

                        students.putIfAbsent(sid, new Student(sid, sname));

                        if (!courses.containsKey(cid)) {
                            System.out.println("Invalid Course ID. Choose from available courses.");
                            break;
                        }

                        registrar.register(students.get(sid), courses.get(cid));
                        System.out.println("Registration successful!");
                        break;

                    case 2:
                        System.out.print("Enter Student ID: ");
                        sid = sc.nextLine();
                        System.out.print("Enter Course ID: ");
                        cid = sc.nextLine();
                        if (students.containsKey(sid) && courses.containsKey(cid)) {
                            registrar.deregister(students.get(sid), courses.get(cid));
                            System.out.println("Deregistration successful!");
                        } else {
                            System.out.println("Invalid student or course ID.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        sid = sc.nextLine();
                        if (students.containsKey(sid)) {
                            students.get(sid).displayStudent();
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Course ID: ");
                        cid = sc.nextLine();
                        if (courses.containsKey(cid)) {
                            courses.get(cid).displayCourse();
                        } else {
                            System.out.println("Course not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Available Courses:");
                        for (Course c : courses.values()) {
                            c.displayCourse();
                            System.out.println("------------------");
                        }
                        break;
                        

                    case 6:
                        // Save students
                        try {
                            PrintWriter writer = new PrintWriter("students.txt");
                            for (Student s : students.values()) {
                                s.saveToFile(writer);
                            }
                            writer.close();
                        } catch (Exception e) {
                            System.out.println("Failed to save students: " + e.getMessage());
                        }

                        // Save courses
                        try {
                            PrintWriter writer = new PrintWriter("courses.txt");
                            for (Course c : courses.values()) {
                                c.saveToFile(writer);
                            }
                            writer.close();
                        } catch (Exception e) {
                            System.out.println("Failed to save courses: " + e.getMessage());
                        }

                        System.out.println("Exiting system. Goodbye!");
                        break;


                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }
}
