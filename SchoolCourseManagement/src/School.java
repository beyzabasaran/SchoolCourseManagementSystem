import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class School {
	
	//ATTRIBUTES
	 private String schoolName;
	 private int capacity;
	 private HashMap<String,Student> students_hm;
	 private int index=0;


	public School(String schoolName, int capacity) {//CONSTRUCTOR
		
	    this.students_hm= setDefaultStudents();
		this.schoolName = schoolName;
		this.capacity = capacity;
	}
	
	//ADD NEW STUDENT TO THE STUDENT HASHMAP
	public void addStudent_hm(Student student) {
		if(students_hm.size()<capacity) 
		{
			students_hm.put(student.getId(), student);
		}
		else 
		{
				JOptionPane.showMessageDialog(null, "School capacity is full!" ,"Error!",JOptionPane.ERROR_MESSAGE);
		}
	}

	//GETTER FUNCTION FOR DEFAULT SETTED STUDENT'S HASHMAP
	public HashMap<String,Student> setDefaultStudents() 
	{
		String path="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\studentsList.txt";
		HashMap<String,Student> default_student_hm = new HashMap<String, Student>();
		Student stu1 = new Student(1, "2012510012" ,"1", "Beyza", "Basaran", "Female","05324561212", "10/12/2008", "beyza.basaran@gmail.com");
		Student stu2 = new Student(2, "2010510042" ,"1", "Seyma Nur", "Erkul",  "Female","05321425212","14/10/2015", "bseyma.erkul@gmail.com");
		Student stu3 = new Student(3,"2142510055"  ,"1","Bilbo", "Baggins",  "Male", "05444561212","10/12/2011", "bilbo.baggins@gmail.com");
		Lesson electiveLesson1 = new Lesson("Computer Science",3);
		Lesson electiveLesson2 = new Lesson("Moral Education",1);
		stu1.setElectiveLesson1(electiveLesson1);  //"Not Chosen yet!"
		stu1.setElectiveLesson2(electiveLesson2);
		
		default_student_hm.put(stu1.getId(), stu1);
		default_student_hm.put(stu2.getId(), stu2);
		default_student_hm.put(stu3.getId(), stu3);
		FileOperation.writetoStudentFile(path,stu1,0); //type 0 writes defaults 
		FileOperation.writetoStudentFile(path,stu2,0);
		FileOperation.writetoStudentFile(path,stu3,0);
		return default_student_hm;

	}
	
	// GETTERS & SETTERS
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public HashMap<String,Student> get_student_hm() {
		return students_hm;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	
	 

}
