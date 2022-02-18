import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Lesson {
	
	//ATTRIBUTES
	private String lessonName;
	private int credit;
	private final int totalCreditsCapacity;
	private int electiveCoursesQuota =3; // TEACHER CAN CHANGE THE CAPACITY
	private int capacity =0;
	private Lesson electiveLesson1;
	private Lesson electiveLesson2;
	private Lesson electiveLesson3;
	private Lesson electiveLesson4;
	private Lesson electiveLesson5;
	private String electiveLesson1_day;
	private String electiveLesson2_day;
	private Teacher electiveLesson_teacher;

	public Lesson() //CONSTRUCTOR1
	{
		this.totalCreditsCapacity=23;
	}
	public Lesson(String lessonName, int credit) //CONSTRUCTOR2
	{
		
		this.totalCreditsCapacity=23;
		this.lessonName = lessonName;
		this.credit = credit;
		this.electiveLesson_teacher=null;
	}

	//DEFAULT COMPULSORY LESSON QUEUE GETTER FUNCTION
	public Queue<Lesson> getCompulsoryLessons()
	{
		//DEFAULT COMPULSORY LESSON GENERATION
		Queue<Lesson> compulsoryLessons = new LinkedList<>();
		Lesson lesson1 = new Lesson("Mathematic",5);
		Lesson lesson2 = new Lesson("English",4);
		Lesson lesson3 = new Lesson("Turkish",4);
		Lesson lesson4 = new Lesson("History",3);
		Lesson lesson5 = new Lesson("Geography",3);
		
		compulsoryLessons.add(lesson1);
		compulsoryLessons.add(lesson2);
		compulsoryLessons.add(lesson3);
	    compulsoryLessons.add(lesson4);
		compulsoryLessons.add(lesson5);
		
		return compulsoryLessons;
	}
	
	//DEFAULT ELECTIVE LESSON QUEUE GETTER FUNCTION
	public Queue<Lesson> getElectiveLessons() 
	{		
		//DEFAULT ELECTIVE LESSON GENERATION
		Queue<Lesson> electiveLessons = new LinkedList<>();
		electiveLesson1 = new Lesson("Computer Science",3);
		Teacher teacher1 = new Teacher("Alfred","Peralta", electiveLesson1);
		teacher1.setDayOff("Sunday");
		electiveLesson1.setElectiveLesson_teacher(teacher1);
		
		electiveLesson2 = new Lesson("Music",2);
		Teacher teacher2 = new Teacher("Emily","Jones", electiveLesson2);
		teacher2.setDayOff("Saturday");
		electiveLesson2.setElectiveLesson_teacher(teacher2);
		
		electiveLesson3 = new Lesson("Physical Education",2);
		Teacher teacher3 = new Teacher("Jake","Santiago", electiveLesson3);
		teacher3.setDayOff("Saturday");
		electiveLesson3.setElectiveLesson_teacher(teacher3);
		
		electiveLesson4 = new Lesson("Moral Education",1);
		Teacher teacher4 = new Teacher("Sarah","Schwimmer", electiveLesson4);
		teacher4.setDayOff("Sunday");
		electiveLesson4.setElectiveLesson_teacher(teacher4);
		
		electiveLesson5 = new Lesson("German",3);
		Teacher teacher5 = new Teacher("Liam","Aniston", electiveLesson5);
		teacher5.setDayOff("Sunday");
		electiveLesson5.setElectiveLesson_teacher(teacher5);
		
		electiveLessons.add(electiveLesson1);
		electiveLessons.add(electiveLesson2);
		electiveLessons.add(electiveLesson3);
		electiveLessons.add(electiveLesson4);
		electiveLessons.add(electiveLesson5);

		return electiveLessons;
	}
	
	//ELECTIVE LESSON SEARCH FOR STUDENT LESSON SELECTION 
	public Lesson searchElectiveLesson(String name) 
	{
		Queue<Lesson> temp = getElectiveLessons();
		int size = temp.size();
		for (int i = 0; i < size; i++) {
			Lesson check = temp.peek();
			if(check.lessonName.equalsIgnoreCase(name)) 
			{
				return check;
			}
			temp.remove();
		}
		return null;
	}
	
	//GETTERS & SETTERS
	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public int getTotalCreditsCapacity() {
		return totalCreditsCapacity;
	}
	
	public int electiveCoursesQuota() {
		return electiveCoursesQuota;
	}
	public void electiveCoursesQuota(int quota) {
		this.electiveCoursesQuota = quota;
	}
	
	public int getElectiveLesson1() {
		return electiveLesson1.capacity;
	}
	public String getElectiveLesson1Name() {
		return electiveLesson1.lessonName;
	}
	public void setElectiveLesson1() {
		this.electiveLesson1.capacity++;;
	}
	public int getElectiveLesson2() {
		return electiveLesson2.capacity;
	}
	public String getElectiveLesson2Name() {
		return electiveLesson2.lessonName;
	}
	public void setElectiveLesson2() {
		this.electiveLesson2.capacity++;
	}
	public int getElectiveLesson3() {
		return electiveLesson3.capacity;
	}
	public String getElectiveLesson3Name() {
		return electiveLesson3.lessonName;
	}
	public void setElectiveLesson3() {
		this.electiveLesson3.capacity++;
	}
	public int getElectiveLesson4() {
		return electiveLesson4.capacity;
	}
	public String getElectiveLesson4Name() {
		return electiveLesson4.lessonName;
	}
	public void setElectiveLesson4() {
		this.electiveLesson4.capacity++;
	}
	public int getElectiveLesson5() {
		return electiveLesson5.capacity;
	}
	public String getElectiveLesson5Name() {
		return electiveLesson5.lessonName;
	}
	public void setElectiveLesson5() {
		this.electiveLesson5.capacity++;
	}
	
	public String getElectiveLesson1_day() {
		return electiveLesson1_day;
	}
	public void setElectiveLesson1_day(String electiveLesson1_day) {
		this.electiveLesson1_day = electiveLesson1_day;
	}
	public String getElectiveLesson2_day() {
		return electiveLesson2_day;
	}
	public void setElectiveLesson2_day(String electiveLesson2_day) {
		this.electiveLesson2_day = electiveLesson2_day;
	}
	
	public Teacher getElectiveLesson_teacher() {
		return electiveLesson_teacher;
	}
	public void setElectiveLesson_teacher(Teacher teacher) {
		this.electiveLesson_teacher= teacher;
	}
	public HashMap<Teacher,Lesson>getElectiveLesson_teacher_hm() {
		
		HashMap<Teacher,Lesson> electiveLesson_teacher_hm= new HashMap<Teacher,Lesson>();
		Queue<Lesson> electiveLessons= getElectiveLessons();
		Lesson lesson=electiveLessons.peek();
		while(lesson!=null) {
			electiveLesson_teacher_hm.put(lesson.getElectiveLesson_teacher(), lesson);			
			electiveLessons.remove();
			lesson=electiveLessons.peek();
		}	
		return electiveLesson_teacher_hm;
	}
	
}
