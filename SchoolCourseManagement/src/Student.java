import java.util.LinkedList;
import java.util.Queue;

public class Student extends Person{
	
	//ATTRIBUTES	
	private String id;
	private int rollNum;
	private String section;
    String birthdate;
    String email;	
    Lesson electiveLesson1;
    Lesson electiveLesson2;
    Queue<Lesson> compulsoryLessons;
    
   //CONSTRUCTOR1
	public Student( int rollNum, String id ,String section , String firstName, String lastName, String gender, String phoneNum,String birthdate, String email) 
	{
		super(firstName, lastName, phoneNum, gender,birthdate,email);
		this.id=id;
		this.rollNum= rollNum;
		this.section=section;
		electiveLesson1 = new Lesson();
		electiveLesson2 = new Lesson();
		electiveLesson1 = null;
		electiveLesson2 = null;
		compulsoryLessons = new LinkedList();
	}

	//CONSTRUCTOR2
	public Student() {
		
		super();
		electiveLesson1 = new Lesson();
		electiveLesson2 = new Lesson();
		compulsoryLessons = new LinkedList();

	}
	
	@Override
	String toStringPerson() {
		return("Student Roll Number: " + rollNum + " \nName: "+super.getFirstName()+"\nSurname:"+super.getLastName()+
				"\nPhone Number:"+super.getPhoneNum()+"\nGender:"+super.getGender()+"\nBirthdate:"+super.getBirthdate()
				+"\nEmail: "+super.getEmail() + "\nSection: " +getSection()+"\nID: "+ getId());
		
	}
    
	//GETTERS & SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRollNum() {
		return rollNum;
	}

	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Lesson getElectiveLesson1() {
		return electiveLesson1;
	}

	public void setElectiveLesson1(Lesson electiveLesson1) {
		this.electiveLesson1 = electiveLesson1;
	}

	public Lesson getElectiveLesson2() {
		return electiveLesson2;
	}

	public void setElectiveLesson2(Lesson electiveLesson2) {
		this.electiveLesson2 = electiveLesson2;
	}

	public Queue<Lesson> getCompulsoryLessons() {
		return compulsoryLessons;
	}

	public void setCompulsoryLessons(Queue<Lesson> compulsoryLessons) {
		this.compulsoryLessons = compulsoryLessons;
	}
	
}
