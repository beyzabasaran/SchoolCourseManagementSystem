
public abstract class Person { //ABSTRACT CLASS
	
	//ATTRIBUTES
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String gender;
    private String birthdate;
    private String email;
    
	public Person(String firstName, String lastName, String phoneNum, String gender,String birthdate, String email) {//CONSTRUCTOR
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.birthdate = birthdate;
		this.email = email;
	}
	public Person() {//CONSTRUCTOR2
		
	}
	
   //GETTERS & SETTERS
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
       
    abstract String toStringPerson();
    

}
