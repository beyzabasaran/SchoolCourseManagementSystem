import java.io.FileNotFoundException;
import java.util.HashMap;

public class Manager extends Person {
	private String userName;
	private String password;
	
	public Manager(String firstName, String lastName, String phoneNum, String gender, String birthdate,String email,String userName, String password) 
	{		super(firstName, lastName, phoneNum, gender, birthdate, email);
		this.userName = userName;
		this.password = password;
	}
	
	public Manager() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	String toStringPerson() {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	static void enrollStudent(Student student) throws FileNotFoundException, InterruptedException {
		Teacher t= new Teacher();
		t.addStudent(student);
    }
	
	public static void deleteStudent(String student_id) throws FileNotFoundException, InterruptedException {
		Teacher t= new Teacher();
		t.deleteStudent(student_id);
	}

	public static boolean addTeacher(Teacher teacher) {
		boolean flag=false;
		Teacher t= new Teacher();
		HashMap<String, Teacher> hm_teachers=t.getHm_teachers() ;
		if(!hm_teachers.containsKey(teacher.getFirstName())) {
			hm_teachers.put(teacher.getFirstName(),teacher);
			flag=true;
		}
		return flag;
	}
	
	public void deleteTeacher(Teacher teacher) {
		Teacher t= new Teacher();
		HashMap<String, Teacher> hm_teachers=t.getHm_teachers() ;
		if(hm_teachers.containsKey(teacher.getFirstName())) {
			hm_teachers.remove(teacher.getFirstName(),teacher);
		}
	}
	
	


	

	

}
