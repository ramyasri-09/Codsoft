import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> enrolledStudents;
    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }
    public String getCode() {
        return code;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getSchedule() {
        return schedule;
    }
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }
    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
    }
    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }
    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description +
               "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
    }
}
class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
    public void registerCourse(Course course) {
        if (course.enrollStudent(this)) {
            registeredCourses.add(course);
        } else {
            System.out.println("Course is full.");
        }
    }
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.dropStudent(this);
            registeredCourses.remove(course);
        } else {
            System.out.println("You are not enrolled in this course.");
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(id).append("\nName: ").append(name).append("\nRegistered Courses:\n");
        for (Course course : registeredCourses) {
            sb.append(course.getCode()).append(" - ").append(course.getTitle()).append("\n");
        }
        return sb.toString();
    }
}
public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();
        showMenu();
    }
    private static void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 3, "MWF 9:00-10:00"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus", 2, "TTh 10:00-11:30"));
        courses.add(new Course("ENG101", "English Literature", "Study of English literature", 2, "MWF 11:00-12:00"));
    }
    private static void initializeStudents() {
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
    }
    private static void showMenu() {
        while (true) {
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. List Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void listCourses() {
        for (Course course : courses) {
            System.out.println(course);
            System.out.println();
        }
    }
    private static void registerForCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.registerCourse(course);
    }
    private static void dropCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        student.dropCourse(course);
    }
    private static void listStudents() {
        for (Student student : students) {
            System.out.println(student);
            System.out.println();
        }
    }
    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    private static Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}
