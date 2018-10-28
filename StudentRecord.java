import java.util.*;
import java.text.DecimalFormat;

/**
 * A database to record students' name, id, course and grade information 
 * 
 * @author HIENDANG
 * 
 * @version February 28th 2017
 *
 */
public class StudentRecord 
{
	/**
	 * Inner class to store a student's name and id
	 */
	private class Student
	{
		private int id;
		private String name;
		
		// Constructor
		Student (int id, String name)
		{
			this.id = id;
			this.name = name;
		}
	}
	/**
	 * Inner class to store courses' record of a student,
	 * which includes name, number of credits, grade, 
	 * and cumulative GPA for a particular course the student has taken
	 */
	private class CourseRecord
	{
		private int studentId;
		private String courseName;
		private double courseCredit;
		private String grade;
		
		// Constructor
		CourseRecord (int studentId, String courseName, double courseCredit, String grade)
		{
			this.studentId = studentId;
			this.courseName = courseName;
			this.courseCredit = courseCredit;
			this.grade = grade;
		}
	}
	// Instance variables for Student LinkedList and CourseRecord LinkedList
	private LinkedList StudentDatabase;
	private LinkedList CourseRecordDatabase;
	
	// Constructor
	public StudentRecord()
	{
		// A linked list to hold Student objects
		StudentDatabase = new LinkedList();
		
		// A linked list to hold CourseRecord objects
		CourseRecordDatabase = new LinkedList();
	}
	/**
	 * This method adds the id and name of a student
	 * @param id
	 * @param name
	 */
	public void addStudent(int id, String name)
	{
		// Create a new Student object
		Student studentObject = new Student (id, name);
		
		// Add the studentObject to the StudentDatabase LinkedList at the first position
		StudentDatabase.addItem(studentObject, 0);
	}
	
	/**
	 * This method adds all necessary information for the course the student has taken
	 * @param studentId
	 * @param courseName
	 * @param couresCredit
	 * @param grade
	 */
	public void addCourseRecord(int studentId, String courseName, double courseCredit, String grade)
	{
		// Create a new CourseRecord object
		CourseRecord courseRecordObject = new CourseRecord(studentId, courseName, courseCredit, grade);
		
		// Add the courseRecordObject to the CourseRecordDatabase LinkedList at the first position
		CourseRecordDatabase.addItem(courseRecordObject, 0);
	}
	
	public void deleteCourseRecord(int studentId, String courseName)
	{
		CourseRecordDatabase.deleteItem(0);
	}
	
	/**
	 * This method calculates students' GPA
	 */
	public String gpaCalculator(double courseCredit, String grade)
	{
		double calcTimes = 0;
		double totalCredits = 0;
		double totalCalcs = 0;
		double finalGPA = 0;
		String printFinalGPA;
		final double A = 4.0;
		final double B = 3.0;
		final double C = 2.0;
		final double D = 1.0;
		final double F = 0.0;
		int courseSize = CourseRecordDatabase.size();
		
		DecimalFormat decimalFormat = new DecimalFormat("0.##");
		
		for (int i = 0; i < courseSize; i++)
		{
			switch(grade){
			case "A":
				calcTimes = courseCredit * A;
			case "a":
				calcTimes = courseCredit * A;
				break;
			case "B":
				calcTimes = courseCredit * B;
			case "b":
				calcTimes = courseCredit * B;
				break;
			case "C":
				calcTimes = courseCredit * C;
			case "c":
				calcTimes = courseCredit * C;
				break;
			case "D":
				calcTimes = courseCredit * D;
			case "d":
				calcTimes = courseCredit * D;
				break;
			case "F":
				calcTimes = courseCredit * F;
			case "f":
				calcTimes = courseCredit * F;
				break;
			default:
				System.out.println("Invalid grade...");
				break;
			}
			totalCredits = totalCredits + courseCredit;
			totalCalcs = totalCalcs + calcTimes;
			finalGPA = totalCalcs/totalCredits;
		}
		printFinalGPA = decimalFormat.format(finalGPA);
		return printFinalGPA;
	}
	
	/**
	 * This method prints out the current records of the students and the courses they have taken
	 */
	public void printStudentRecord()
	{
		System.out.println();
		ListIterator iterateStudentDatabase = StudentDatabase.iterator();
		int courseSize = CourseRecordDatabase.size();
		
		while (iterateStudentDatabase.hasNext())
		{
			Student student = (Student) iterateStudentDatabase.next();
			
			for (int i = 0; i < courseSize; i++)
			{
				CourseRecord courseRecord = (CourseRecord) CourseRecordDatabase.getItem(i);
							
				if (student.id == courseRecord.studentId)
				{
					System.out.println("Student ID: " + student.id);
					System.out.println("Student Name: " + student.name);
					System.out.println("Course Name: " + courseRecord.courseName);
					System.out.println("Course Credit: " + courseRecord.courseCredit);
					System.out.println("Grade: " + courseRecord.grade);
					System.out.println("GPA: " + gpaCalculator(courseRecord.courseCredit, courseRecord.grade));
					System.out.println();
				}
			}
		}
	}

	/**
	 * Main method to run the StudentRecord program and test those methods above
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner (System.in);
		String name, courseName, grade;
		int id, operation;
		double courseCredit;
	
		StudentRecord studentRecord = new StudentRecord();
	
		while (true)
		{
			System.out.println("Please enter 1 to add a student.");
			System.out.println("Please enter 2 to add the courses the student has taken.");
			System.out.println("Please enter 3 to delete a single course the student has taken.");
			System.out.println("Please enter 4 to print the entire student records in this database.");
			System.out.println("Please enter 5 to exit the program.");
			System.out.println("Which operation would you like to execute from 1-5?");
			operation = keyboard.nextInt();
			keyboard.nextLine();
		
			switch (operation){
			case 1:
				System.out.println("Student Id: ");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Student Id must contain integers only.");
					keyboard.next();
				}
				id = keyboard.nextInt();
				System.out.println("Student Name: ");
				while(!keyboard.hasNext("[A-Za-z]+"))
				{
					System.out.println("Name should contain alphabetical characters only.");
					keyboard.next();
				}
				name = keyboard.next();
				
				studentRecord.addStudent(id, name);
				break;
			case 2:
				System.out.println("Student Id: ");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Student Id must contain integers only.");
					keyboard.next();
				}
				id = keyboard.nextInt();
				System.out.println("Course Name: ");
				courseName = keyboard.next();
				System.out.println("Course Credit: ");
				while (!keyboard.hasNextDouble())
				{
					System.out.println("Course credit must be a whole number from 1-4."); 
					keyboard.next();
				}
				courseCredit = keyboard.nextDouble();
				System.out.println("Grade: "); 
				grade = keyboard.next();
				
				studentRecord.addCourseRecord(id, courseName, courseCredit, grade);
			    studentRecord.gpaCalculator(courseCredit, grade);
				break;
			case 3:
				System.out.println("Student Id: ");
				while (!keyboard.hasNextInt())
				{
					System.out.println("Student Id must contain integers only.");
					keyboard.next();
				}
				id = keyboard.nextInt();
				System.out.println("Course Name: ");
				courseName = keyboard.next();
				studentRecord.deleteCourseRecord(id, courseName);
				break;
			case 4:
				studentRecord.printStudentRecord();
				break;
			case 5:
				System.out.println("Thank you for your time. Goodbye!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input. Please enter from 1-5.");
				break;
			}	
		}
	}
}	

