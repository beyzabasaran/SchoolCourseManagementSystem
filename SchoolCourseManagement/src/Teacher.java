import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Teacher extends Person{
	
	//ATTRIBUTES
	private boolean isAvailable;
	private Lesson lesson;
	private String dayOff;
	private String path="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\teacher.txt";
	
	//CONSTRUCTOR1
	public Teacher(String firstName, String lastName, String phoneNum, String gender, String birthdate, String email,Lesson lesson) {
		super(firstName, lastName, phoneNum, gender,birthdate,email);
		this.lesson = lesson;
		this.isAvailable = true;
		this.dayOff=null;
	}
	
	//CONSTRUCTOR2
	public Teacher(String firstName, String lastName,Lesson lesson) {
		super(firstName, lastName, "", "","","");
		this.lesson = lesson;
		this.isAvailable = true;
	}
	
	//GETTERS & SETTERS
	public Teacher() {
		this.isAvailable = true;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean available) {
		this.isAvailable = available;
	}


	public Lesson getLesson() {
		return lesson;
	}


	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public String getDayOff() {
		return dayOff;
	}
	
	public void setDayOff(String dayOff) {
		setAvailable(false);
		this.dayOff = dayOff;
	}
	
	//GETTER HELPER FUNCTION FOR DEFAULT TEACHER'S HASHMAP 
	public HashMap<String, Teacher> getHm_teachers() 
	{
				Lesson lessons= new Lesson();
			 	HashMap<String, Teacher> hm_teachers= new HashMap<String, Teacher>();		 	
			 	Queue<Lesson> temp_compulsoryLessons= lessons.getCompulsoryLessons();
		 
			 	Lesson temp= temp_compulsoryLessons.peek();
				Teacher teacher1 = new Teacher("Sam".toLowerCase(),"Santiago".toLowerCase(),temp);
				teacher1.setDayOff("Sunday");
				temp_compulsoryLessons.remove();
				temp= temp_compulsoryLessons.peek();
				Teacher teacher2 = new Teacher("Clara".toLowerCase(),"Smith".toLowerCase(),temp);
				teacher2.setDayOff("Saturday");
				temp_compulsoryLessons.remove();
				temp= temp_compulsoryLessons.peek();
				Teacher teacher3 = new Teacher("Harry".toLowerCase(),"Ruth".toLowerCase(),temp);
				teacher3.setDayOff("Saturday");
				temp_compulsoryLessons.remove();
				temp= temp_compulsoryLessons.peek();
				Teacher teacher4 = new Teacher("Eva".toLowerCase(),"Black".toLowerCase(),temp);
				teacher4.setDayOff("Sunday");
				temp_compulsoryLessons.remove();
				temp= temp_compulsoryLessons.peek();
				Teacher teacher5 = new Teacher("Damian".toLowerCase(),"Bold".toLowerCase(),temp);
				//teacher5.setDayOff("Sunday"); //DO NOT ASSIGN AS DEFAULT SELECT AFTER LOG IN TO THE SYSTEM
				
				hm_teachers.put(teacher1.getFirstName().toLowerCase(),teacher1);
				hm_teachers.put(teacher2.getFirstName().toLowerCase(),teacher2);
				hm_teachers.put(teacher3.getFirstName().toLowerCase(),teacher3);
				hm_teachers.put(teacher4.getFirstName().toLowerCase(),teacher4);
				hm_teachers.put(teacher5.getFirstName().toLowerCase(),teacher5);
				FileOperation.writetoTeacherFile(path,teacher1,0); //TYPE=0 WRITE DEFAULTS TO THE FILE
				FileOperation.writetoTeacherFile(path,teacher2,0);
				FileOperation.writetoTeacherFile(path,teacher3,0);
			
		return hm_teachers;
	}

	@Override
	String toStringPerson() {
		
		System.out.println("Teacher name: "+super.getFirstName()+"   Surname:"+super.getLastName()+"   Lesson:"+getLesson().getLessonName());
		return null;

		
	}
    
	//DELETE STUDENT FROM THE STUDENT HASHMAP
	void deleteStudent(String student_id) throws FileNotFoundException, InterruptedException {
		Main main= new Main();
		HashMap<String,Student>hm_stu=main.get_hmStudents();
		if(hm_stu.containsKey(student_id)) {
			main.get_hmStudents().remove(student_id, hm_stu.get(student_id));
		}

		
	}
	//ADD STUDENT TO THE STUDENT HASHMAP
	void addStudent(Student student) throws FileNotFoundException, InterruptedException {
		Main main= new Main();
		HashMap<String,Student>hm_stu=main.get_hmStudents();
		if(!hm_stu.containsKey(student.getId())) {
			main.get_hmStudents().put(student.getId(), student);
			
		}
		
		
	}
	
	void acceptToCourse(Student student, Teacher teacher) {
		if(!isAvailable()) {
			//MESSAGE!
		}
		else {
			//Enroll student
		}
		
	}


}
