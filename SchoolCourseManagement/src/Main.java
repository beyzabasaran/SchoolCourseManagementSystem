import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Main {
    //JFRAMES
	private static JFrame frmLogIn;
	private static JFrame frmEnroll;
	private static JFrame frmLogin;
	private static JFrame frmTeacherDayOff;
	private static JFrame frmTeacherMenu;
	private static JFrame frmTeacherAddStu;
	private static JFrame frmTeacherDeleteStu;
	private static JFrame frmEnroll2 ;
	private static JFrame frmManagerMenu;
	private static JFrame frmManagerAddStu;
	private static JFrame frmManagerDeleteStu;
	private static JFrame frmManagerAddTeacher;
	private static JFrame frmManagerDeleteTeacher;
	private static JFrame frmEnroll3;
	private static JFrame frmEntrance;
	
	//JTEXTFIELDS
	private static JTextField txtUserName;
	private static JPasswordField passwordField;
	private static JTextField studentIDField;
	private static JTextField studentName;
	private static JTextField studentSurname;
	private static JTextField studentPhone;
	private static JTextField studentGender;
	private static JTextField studentID ;
	private static JTextField field_id;
	private static JTextField studentEmail;
	private static JTextField studentBirthdate;
	private static JTextField studentSection;	
	private static JTextField teacherName;
	private static JTextField teacherSurname;
	private static JTextField teacherPhone;
	private static JTextField teacherGender;
	private static JTextField teacherEmail;
	private static JTextField teacherBirthday;
	private static JTextField teacherLessonName;
	private static JTextField teacherLessonCredit;
	
	private JButton btnCancel;
	private JButton btnCancel2;
    private static JButton btnLogIn;
    
    //FILE ATTRIBUTES
	public static FileOperation file_student;
	public static FileOperation file_teacher;
	
	public static String student_txt_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\studentsList.txt"; 
	public static String teacher_txt_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\teacher.txt";
	private static String child_image_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\PngItem_1746580.png"; // child image
	private static String teacher_image_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\unnamed.png"; // teacher image
	private static String manager_image_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\manager3.png";// manager image
	private static String welcome_png_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\entranceWelcome.jpg"; // entrance image
   
   //HASHMAPS to store enrolled and default added students and teachers
    private static HashMap<String,Student> hmStudents; //KEY=STUDENT ID, VALUE=STUDENT
    private static HashMap<String, Teacher> hm_teachers; //KEY=TEACHER NAME, VALUE=TEACHER
    
    //CLASS OBJECTS
	private static JLabel lblMenu;
	public static Student student;
	public static Teacher teacher;
    private static MenuPage mp;
    public static  School school = new School("School",50);
    private static Manager manager = new Manager("Manager","Unknown", "05337943498", "Unknown", "11/11/1956","schoolcourse@hotmail.com","manager", "manganer");
    private String managerPassword="manager"; //MANAGER PASSWORD
   	private static int rollNumber = 3;
    private boolean flag_teacher_dayOff;
    private static int count=1;
    
    //MANAGER PASSWORD=manager
    //Teacher PASSWORD= teacher name
    
//CONSTRUCTOR FOR ENTRANCE FRAME    
public Main(JFrame entrancefrm) throws InterruptedException {
	
	entrancefrm.setTitle("SCHOOL-COURSE MANAGEMENT SYSTEM");
	entrancefrm.setBounds(100, 100, 1100, 570);
	entrancefrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	entrancefrm.getContentPane().setLayout(null);
	
	//IMAGE LABEL
	JLabel lblNewLabel = new JLabel(""); 
	lblNewLabel.setIcon(new ImageIcon(welcome_png_path));
	lblNewLabel.setBounds(-150, -10, 1300, 550);
	entrancefrm.getContentPane().add(lblNewLabel);
	
	if(count==1) //DO IT ONY ONCE
	{	      	
		frmEntrance.setVisible(true);
		Thread.sleep(5000);	//WAIT FOR 5 SECONDS
		frmEntrance.setVisible(false); 
		count++;
	}	
}  

public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	
	frmEntrance = new JFrame();
	Main m = new Main(frmEntrance);
	
	hmStudents=school.get_student_hm(); //default added students to the Student HashMap 
	file_student = new FileOperation(student_txt_path);
	readfromFileToHm(student_txt_path,hmStudents);//Reading recorded students to Student HashMap from the student file
	
	teacher= new Teacher();
	hm_teachers= teacher.getHm_teachers();//default added teachers to the Teacher HashMap
	file_teacher = new FileOperation(student_txt_path);
	readfromFileToTeacherHm(teacher_txt_path,hm_teachers);//Reading recorded teachers to Teacher HashMap from the teacher file
	
	//FRAME & TEXTFIELD DECLARATIONS
	studentName = new JTextField("");
	studentSurname = new JTextField("");
	studentPhone = new JTextField("");
	studentGender = new JTextField("");
	studentID = new JTextField("");
	studentEmail = new JTextField("");
	studentBirthdate = new JTextField("");
	studentSection = new JTextField("");
	field_id= new JTextField("");
	
	teacherName = new JTextField("");
	teacherSurname = new JTextField("");
	teacherPhone = new JTextField("");
	teacherGender = new JTextField("");
	teacherEmail = new JTextField("");
	teacherBirthday = new JTextField("");
	teacherLessonName = new JTextField("");
	teacherLessonCredit = new JTextField("");
	
	frmLogIn = new JFrame();
	frmLogIn.setBounds(200, 100, 500, 500);
	frmLogIn.getContentPane().setBackground(new Color(173, 216, 230));
	frmLogIn.setBounds(200, 100, 500, 500);
	frmLogIn.getContentPane().setBackground(new Color(173, 216, 230));
	frmLogIn.setTitle("Log in");
	frmLogIn.setBounds(200, 100, 500, 500);
	frmLogIn.getContentPane().setBackground(new Color(173, 216, 230));
	frmLogIn.setBackground(new Color(178, 238, 238));
	frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmLogIn.getContentPane().setLayout(null);
	
	
	frmEnroll = new JFrame();
	frmEnroll.setBounds(200, 100, 500, 500);
	frmEnroll.getContentPane().setBackground(new Color(173, 216, 230));
	frmEnroll.setTitle("Enroll");
	frmEnroll.setBounds(200, 100, 500, 500);
	frmLogIn.getContentPane().setBackground(new Color(173, 216, 230));
	frmLogIn.setBackground(new Color(178, 238, 238));
	frmEnroll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmEnroll.getContentPane().setLayout(null);
	
	frmLogin = new JFrame();
	frmLogin.setBounds(200, 100, 500, 500);
	frmLogin.getContentPane().setBackground(new Color(173, 216, 230));
	frmLogin.setTitle("Log in");
	frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmLogin.getContentPane().setLayout(null);
	
	frmTeacherDayOff = new JFrame();
	frmTeacherDayOff.setBounds(200, 100, 700, 500);
	frmTeacherDayOff.getContentPane().setBackground(new Color(173, 216, 230));
	frmTeacherDayOff.setTitle("Teacher");
	frmTeacherDayOff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmTeacherDayOff.getContentPane().setLayout(null);	
	
	frmTeacherMenu= new JFrame();
	lblMenu = new JLabel(" ");
	lblMenu.setBounds(20, 5, 46, 14);
	frmTeacherMenu.getContentPane().add(lblMenu);
	frmTeacherMenu.setBounds(200, 100, 700, 600);
	frmTeacherMenu.getContentPane().setBackground(new Color(173, 216, 230));
	frmTeacherMenu.setTitle("Menu");
	frmTeacherMenu.add(lblMenu);

	JLabel lblNewLabel2 = new JLabel(""); 
	lblNewLabel2.setIcon(new ImageIcon(teacher_image_path));
 	lblNewLabel2.setBounds(300, 0, 500, 600);
 	frmTeacherMenu.getContentPane().add(lblNewLabel2);
	
	frmTeacherAddStu= new JFrame();
	frmTeacherAddStu.getContentPane().setBackground(new Color(173, 216, 230));
	frmTeacherAddStu.setBackground(new Color(178, 238, 238));
	frmTeacherAddStu.setTitle("Add Student");
	frmTeacherAddStu.setBounds(200, 100, 500, 300);	
	frmTeacherAddStu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmTeacherAddStu.getContentPane().setLayout(null);
	
	frmTeacherDeleteStu= new JFrame();
	frmTeacherDeleteStu.getContentPane().setBackground(new Color(173, 216, 230));
	frmTeacherDeleteStu.setBackground(new Color(178, 238, 238));
	frmTeacherDeleteStu.setTitle("Delete Student");
	frmTeacherDeleteStu.setBounds(200, 100, 600, 450);
	frmTeacherDeleteStu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmTeacherDeleteStu.getContentPane().setLayout(null);
	
	frmEnroll2 = new JFrame();
	frmEnroll2.getContentPane().setBackground(new Color(173, 216, 230));
	frmEnroll2.setBackground(new Color(178, 238, 238));
	frmEnroll2.setTitle("Teacher Menu");
	frmEnroll2.setBounds(200, 100, 400, 400);
	frmEnroll2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmEnroll2.getContentPane().setLayout(null);
	
	frmManagerMenu= new JFrame();
	frmManagerMenu.getContentPane().setBackground(new Color(173, 216, 230));
	frmManagerMenu.setBackground(new Color(178, 238, 238));
	frmManagerMenu.setTitle("Menu");
	frmManagerMenu.setBounds(200, 100, 700, 500);
	frmManagerMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmManagerMenu.getContentPane().setLayout(null);
	frmManagerMenu.getContentPane().add(lblMenu);
	
	frmManagerAddStu= new JFrame();
	frmManagerAddStu.getContentPane().setBackground(new Color(173, 216, 230));
	frmManagerAddStu.setBackground(new Color(178, 238, 238));
	frmManagerAddStu.setTitle("Add Student");
	frmManagerAddStu.setBounds(200, 100, 500, 300);
	frmManagerAddStu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmManagerAddStu.getContentPane().setLayout(null);
	
	frmManagerDeleteStu= new JFrame();
	frmManagerDeleteStu.getContentPane().setBackground(new Color(173, 216, 230));
	frmManagerDeleteStu.setBackground(new Color(178, 238, 238));
	frmManagerDeleteStu.setTitle("Delete Student");
	frmManagerDeleteStu.setBounds(200, 100, 550, 350);
	frmManagerDeleteStu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmManagerDeleteStu.getContentPane().setLayout(null);
	
	frmManagerAddTeacher= new JFrame();
	frmManagerAddTeacher.getContentPane().setBackground(new Color(173, 216, 230));
	frmManagerAddTeacher.setBackground(new Color(178, 238, 238));
	frmManagerAddTeacher.setTitle("Add Teacher");
	frmManagerAddTeacher.setBounds(200, 100, 500, 300);
	frmManagerAddTeacher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmManagerAddTeacher.getContentPane().setLayout(null);
	
	frmManagerDeleteTeacher= new JFrame();
	frmManagerDeleteTeacher.getContentPane().setBackground(new Color(173, 216, 230));
	frmManagerDeleteTeacher.setBackground(new Color(178, 238, 238));
	frmManagerDeleteTeacher.setTitle("Delete Teacher");
	frmManagerDeleteTeacher.setBounds(200, 100, 550, 350);
	frmManagerDeleteTeacher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmManagerDeleteTeacher.getContentPane().setLayout(null);
	
	frmEnroll3 = new JFrame();
	frmEnroll3.getContentPane().setBackground(new Color(173, 216, 230));
	frmEnroll3.setBackground(new Color(178, 238, 238));
	frmEnroll3.setTitle("Enroll");
	frmEnroll3.setBounds(200, 100, 600, 650);
	frmEnroll3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmEnroll3.getContentPane().setLayout(null);
	
	final JLabel label = new JLabel();          
    label.setHorizontalAlignment(JLabel.CENTER);  
    label.setSize(120,120);  
    
   //LOG IN FRAME
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Main window = new Main();
				window.frmLogIn.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

public Main() throws FileNotFoundException, InterruptedException 
{
	//FRAME & BUTTON DECLARATIONS		
	readfromFileToHm(student_txt_path, hmStudents); //Reading recorded students to Student HashMap from the file
	frmLogIn = new JFrame();
	frmLogIn.getContentPane().setBackground(new Color(173, 216, 230));
	frmLogIn.setBackground(new Color(178, 238, 238));
	frmLogIn.setTitle("Log in");
	frmLogIn.setBounds(200, 100, 700, 600);
	frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmLogIn.getContentPane().setLayout(null);
	JLabel lblNewLabel = new JLabel(""); 
	lblNewLabel.setIcon(new ImageIcon(child_image_path));
 	lblNewLabel.setBounds(390, 0, 600, 600);
 	frmLogIn.getContentPane().add(lblNewLabel);
 	
 	btnCancel = new JButton("Cancel");
 	btnCancel2 = new JButton("Cancel");
	
	frmEnroll = new JFrame();
	frmEnroll.setTitle("Enroll");
	frmEnroll.getContentPane().setBackground(new Color(173, 216, 230));
	frmEnroll.setBackground(new Color(178, 238, 238));
	frmEnroll.setBounds(200, 100, 500, 600);
	frmEnroll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmEnroll.getContentPane().setLayout(null);
	
	//LABEL & COMBOBOX DECLARATIONS
	final JLabel label = new JLabel();          
    label.setHorizontalAlignment(JLabel.CENTER);  
    label.setSize(120,120);  

	String types[] = {"TYPE","MANAGER" , "TEACHER" , "STUDENT"}; 
	JComboBox<Object> cbUser = new JComboBox<Object>(types);
	cbUser.setFont(new Font("Font.PLAIN", Font.BOLD, 15));
	cbUser.setBounds(80, 20, 111, 28);
	frmLogIn.getContentPane().add(cbUser);
	
	
	JButton button=new JButton("OK");  
    button.setBounds(250,20,100,30);  
    button.setFont(new Font("Font.PLAIN", Font.BOLD, 15));
    button.setBackground(new Color(240, 230, 140));
    
    frmLogIn.getContentPane().add(button);
    frmLogIn.getContentPane().add(label);
    btnLogIn = new JButton("Log in");
    btnLogIn.setVisible(false);
    		//USER ENTRANCE OK BUTTON ACTION LISTENER
    		 button.addActionListener(new ActionListener() { 
    			 public void actionPerformed(ActionEvent e) 
    			 {   				 
    				 if(!(cbUser.getSelectedItem().equals("TYPE")) && cbUser.getSelectedItem() !=null) {	    				    
    					//USER TYPE=STUDENT	 
    					if(cbUser.getItemAt(cbUser.getSelectedIndex()).equals("STUDENT")){	    			  				 
	    				cbUser.setVisible(false);
	    				button.setVisible(false);
	    				label.setVisible(false);		    				 
	    				JRadioButton enroll = new JRadioButton("Enroll");
    					enroll.setBounds(80, 37, 220, 23);
    					enroll.setSelected(true);
    					
    					frmLogIn.getContentPane().add(enroll);
    					
    					JRadioButton login = new JRadioButton("Login");
    					login.setBounds(80, 68, 220, 23);
    					frmLogIn.getContentPane().add(login);
    					
    					ButtonGroup btnG = new ButtonGroup();
    					btnG.add(enroll);
    					btnG.add(login);
    					
    					JButton btnOk = new JButton("OK");
    					btnCancel.setVisible(true);
    					//STUDENT ENTRANCE OK BUTTON ACTION LISTENER
    					btnOk.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent arg0) 
    						{
    							//STUDENT RADIO BUTTON SELECTION
    							if(enroll.isSelected())//STUDENT ENROLL FRAME
    							{
    								enroll.setVisible(false);
    								login.setVisible(false);    					
    								frmLogIn.setVisible(false);
    				    			btnOk.setVisible(false);
    				    			JLabel title = new JLabel("Student Enroll Information ");
    			    				title.setBounds(150, 16, 200, 50);    				
    			    				frmEnroll.getContentPane().add(title);
    			    				 
    				    			 createTextField("Name: ",50,studentName,frmEnroll);	
    				    			 createTextField("Surname: ",100,studentSurname,frmEnroll);
    				    			 createTextField("Phone Number: ",150,studentPhone,frmEnroll);
    				    			 createTextField("Gender: ",200,studentGender,frmEnroll);
    				    			 createTextField("ID: ",250,studentID,frmEnroll);//check id control
    				    			 createTextField("Email: ",300,studentEmail,frmEnroll);
    				    			 createTextField("Birthdate: ",350,studentBirthdate,frmEnroll);
    				    			 createTextField("Section: ",400,studentSection,frmEnroll);
    				    			
    				    			 JButton buttonEnroll=new JButton("Enroll");  
    				    			 buttonEnroll.setBounds(220,480,100,30);
    				    			 frmEnroll.getContentPane().add(buttonEnroll);
    				    			 frmEnroll.setVisible(true);
    				    			 
    				    			 buttonEnroll.addActionListener(new ActionListener() {
    				    				 public void actionPerformed(ActionEvent arg0) {
    				    					 setRollNumber(rollNumber+1);
    				    					//STUDENT ENROLL INFORMATION CONTROLS
    				    					 Student student = new Student(getRollNumber(), studentID.getText(),studentSection.getText(),studentName.getText(),studentSurname.getText(),studentGender.getText(),studentPhone.getText(),studentBirthdate.getText(),
		    				    					 studentEmail.getText());
    				 							if(studentName.getText().isEmpty() || studentGender.getText().isEmpty() || studentSurname.getText().isEmpty() || studentPhone.getText().isEmpty()
    				 									|| studentID.getText().isEmpty() || studentEmail.getText().isEmpty() || studentBirthdate.getText().isEmpty() || studentSection.getText().isEmpty()) 
    				 							{
    				 								JOptionPane.showMessageDialog(frmEnroll, "Please fill the data" ,"Error!",JOptionPane.ERROR_MESSAGE);
    				 								frmEnroll.setVisible(true);
    				 							}
    				 							else if(!checkStudentInfo(student,hmStudents)) 
    				 							{
    				 								rollNumber--;
    				 								JOptionPane.showMessageDialog(frmEnroll, "Please fill the id right" ,"Error!",JOptionPane.ERROR_MESSAGE);
    				 								
    				 							}
    				 							else 
    				 							{
    				 								if(school.getCapacity()!=school.getIndex()) 
    				 								{
    				 									JOptionPane.showMessageDialog(frmEnroll, "Student Has Been Enrolled!\nNow you can log in to enroll lesson" ,null,JOptionPane.INFORMATION_MESSAGE);	    				 									
    				 								}			 								 
    			    				    			school.addStudent_hm(student);//STUDENT ADDED TO SCHOOL'S STUDENT HASHMAP
 	    			    				    		file_student.writetoStudentFile(student_txt_path,student,1); //NEW STUDENT RECORDED TO STUDENT FILE
 	    			    				    		frmEnroll.setVisible(false);
 	    				 							// checking null text fields
 	    				 							frmLogIn.setVisible(false);
 	    	 	    								JLabel lblUserName = new JLabel("Student ID: ");//STUDENT DIRECTED TO THE LOG IN PAGE
 	    	 	    			    				lblUserName.setBounds(55, 31, 78, 14);
 	    	 	    			    				frmLogin.getContentPane().add(lblUserName);
 	    	 	    				    			studentIDField = new JTextField();
 	    	 	    				    			studentIDField.setBounds(143, 31, 86, 20);
 	    	 	    				    			frmLogin.getContentPane().add(studentIDField);
 	    	 	    				    			btnOk.setVisible(false);
 	    	 	    				    			frmLogin.getContentPane().add(btnLogIn);
 	    	 	    				    			btnLogIn.setVisible(true);
 	    	 	    				    			frmLogin.setVisible(true);
    				 							}
    				 							
    				 						}	    				 					
    				 				});
    							}
    							else if(login.isSelected())//STUDENT LOG IN FRAME
    							{
    								enroll.setVisible(false);
    								login.setVisible(false);	    									    							
    								frmLogIn.setVisible(false);
    								frmEnroll.setVisible(false);
    								JLabel lblUserName = new JLabel("Student ID: ");
    			    				lblUserName.setBounds(55, 31, 78, 14);
    			    				frmLogin.getContentPane().add(lblUserName);
    				    			studentIDField = new JTextField();
    				    			studentIDField.setBounds(143, 31, 86, 20);
    				    			frmLogin.getContentPane().add(studentIDField);
    				    			btnOk.setVisible(false);
    				    			frmLogin.getContentPane().add(btnLogIn);
    				    			btnLogIn.setVisible(true);
    				    			frmLogin.setVisible(true);
    				    			student = hmStudents.get(studentIDField.getText());
    							}    							
 	    						}
    					});
    					//OK BUTTON
    					btnOk.setBounds(80, 100, 100, 20);	 
    					btnOk.setFont(new Font("Font.PLAIN", Font.BOLD, 15));
    					btnOk.setBackground(new Color(240, 230, 140));
    					    
    					frmLogIn.getContentPane().add(btnOk);
    					btnCancel.setBounds(190, 100, 110, 20);
    					btnCancel.setFont(new Font("Font.PLAIN", Font.BOLD, 12));
    					btnCancel.setBackground(new Color(240, 230, 140));
    					frmLogIn.getContentPane().add(btnCancel);
    					//CANCEL BUTTON ACTION LISTENER
    					btnCancel.addActionListener(new ActionListener() {
    						public void actionPerformed(ActionEvent arg0) 
    						{
    							cbUser.setVisible(true);
    		    				button.setVisible(true);
    		    				label.setVisible(true);
    		    				enroll.setVisible(false);
								login.setVisible(false);  
								btnOk.setVisible(false);
								btnCancel.setVisible(false);
    							frmLogIn.setVisible(true);
    						}
    					});					    				
    				 }
    					//USER TYPE=TEACHER ENTRANCE
    				 if(cbUser.getItemAt(cbUser.getSelectedIndex()).equals("TEACHER")) {
    					 //TEACHER LOG IN
    					 cbUser.setVisible(false);
		    			 button.setVisible(false);
		    			 label.setVisible(false);
		    			 btnLogIn.setVisible(false);	    
    					 JButton btnTeacherLogIn= new JButton("Log in");
    					 btnTeacherLogIn.setBounds(143, 95, 74, 25);
    					 frmLogIn.getContentPane().add(btnTeacherLogIn);

	    		    	 JLabel lblUserName = new JLabel("Name: ");
	    				 lblUserName.setBounds(55, 31, 78, 14);
		    			 frmLogIn.getContentPane().add(lblUserName);
		    				
		    			 txtUserName = new JTextField();
		    			 txtUserName.setBounds(143, 28, 150, 20);
		    			 txtUserName.setColumns(10);
		    			 frmLogIn.getContentPane().add(txtUserName);
		    							    				
		    			 JLabel lblPassword = new JLabel("Password:");//password=name
		    			 lblPassword.setBounds(55, 65, 78, 14);
		    			 frmLogIn.getContentPane().add(lblPassword);
		    						 				
		    			 passwordField = new JPasswordField();
		    			 passwordField.setBounds(143, 59, 150, 20);
		    			 frmLogIn.getContentPane().add(passwordField);
		    			 btnTeacherLogIn.setVisible(true);		
	    				 btnCancel.setBackground(new Color(50, 100, 140));
		    			 btnCancel.setVisible(true);
		    			 btnTeacherLogIn.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent arg0) {
			    				//CONTROLLING IF THEACHER EXISTS IN THE SYSTEM BY CHECKING TEACHER HASHMAP
					    		if(txtUserName.getText().equalsIgnoreCase(passwordField.getText())&&hm_teachers.containsKey(txtUserName.getText().toLowerCase())) {//KEY=TEACHER NAME, VALUE=TEACHER
				    				teacher=hm_teachers.get(txtUserName.getText().toLowerCase());
					    			frmLogIn.setVisible(false);	    		
					    			cbUser.setVisible(false);
				    				button.setVisible(false);
				    				label.setVisible(false);		    	
				    				
				    				//TEACHER MENU PROPERITIES
				    				JRadioButton addStudent= new JRadioButton("Add Student");
				    				addStudent.setBounds(60, 51, 323, 23);
				    				addStudent.setSelected(true);
				    				frmTeacherMenu.getContentPane().add(addStudent);
			    					
			    					JRadioButton deleteStudent = new JRadioButton("Delete student");
			    					deleteStudent.setBounds(60, 77, 323, 23);
			    					frmTeacherMenu.getContentPane().add(deleteStudent);
			    					
			    					JRadioButton takeDayOffDay = new JRadioButton("Take Day Off");
			    					takeDayOffDay.setBounds(60, 103, 323, 23);
			    					frmTeacherMenu.getContentPane().add(takeDayOffDay);
			    					flag_teacher_dayOff=true;
			    					if(!teacher.isAvailable()) {
			    						flag_teacher_dayOff=false;
			    					}
			    					
			    					JRadioButton displayStudent = new JRadioButton("Displays students");
			    					displayStudent.setBounds(60,129, 323, 23);
			    					frmTeacherMenu.getContentPane().add(displayStudent);
			    					
			    					JRadioButton exit = new JRadioButton("Exit");
			    					exit.setBounds(60,155, 323, 23);
			    					frmTeacherMenu.getContentPane().add(exit);
					
			    					btnCancel2.setBounds(115, 225, 100, 25); 	
			    				 	btnCancel2.setFont(new Font("Font.PLAIN", Font.BOLD, 12));
			    					btnCancel2.setBackground(new Color(50, 100, 140));
			    					frmTeacherMenu.getContentPane().add(btnCancel2);
			    					btnCancel2.setVisible(true);
			    				 	

			    					btnCancel2.addActionListener(new ActionListener() {
			    						public void actionPerformed(ActionEvent arg0) 
			    						{
			    							txtUserName.setText("");
			    							passwordField.setText("");
			    							frmTeacherMenu.setVisible(false);
			    							frmLogIn.setVisible(true);
			    							btnCancel2.setVisible(false);
			    							
			    						}
			    					}); 	
			    					
			    					ButtonGroup btnG = new ButtonGroup();
			    					btnG.add(addStudent);
			    					btnG.add(deleteStudent);
			    					btnG.add(takeDayOffDay);
			    					btnG.add(displayStudent);
			    					btnG.add(exit);
			    					
			    					JButton selection_btnOk = new JButton("OK");
			    					selection_btnOk.setBounds(115, 190, 86, 25);
			    					frmTeacherMenu.add(selection_btnOk);	
			    					frmTeacherMenu.getContentPane().add(lblMenu);			    	
			    					frmTeacherMenu.setVisible(true);
			    					//TEACHER MENU SELECTION OK BUTTON ACTION LISTENER
			    					selection_btnOk.addActionListener(new ActionListener() {
						    				public void actionPerformed(ActionEvent arg0) {
							    					if(addStudent.isSelected()){
							    						 //TEACHER SELECTION= ADD STUDENT
							    						 frmTeacherMenu.setVisible(false);
							    						 JButton btnTeacherAddStu= new JButton("Enroll");
							    						//TEACHER ADD STUDENT FRAME
							    						 btnTeacherAddStu.setBounds(143, 95, 86, 25);
							    						 frmTeacherAddStu.getContentPane().add(btnTeacherAddStu);			
							    						 
							    						 JLabel lblNewLabel2 = new JLabel(""); 
							    						 lblNewLabel2.setIcon(new ImageIcon(teacher_image_path));
							    						 lblNewLabel2.setBounds(300, 0, 500, 600);
							    						 //STUDENT ENROLL FRAME CREATED AND INFORMATION & EXISTENCE CHECKED THEN NEW STUDENT RETURNED 
							    						 Student addedStudent=createTeacherStudentEnrollFrame(frmTeacherAddStu,frmTeacherMenu,btnTeacherAddStu,addStudent,lblNewLabel2);								    			
							    						try {																														
																teacher.addStudent(addedStudent);//IF STUDENT IS NOT IN THE RECORDS THEN ADD
															
														} catch (FileNotFoundException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (InterruptedException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
					    							}
							    					//TEACHER SELECTION= DELETE STUDENT
							    					else if(deleteStudent.isSelected())
					    							{
							    						field_id.setText("");
							    						frmTeacherMenu.setVisible(false);
							    						//STUDENT ID TAKEN, CHECKED
							    						JLabel label_id = new JLabel("Student ID: ");
					    			    				label_id.setBounds(55, 56, 78, 14);
					    			    				frmTeacherDeleteStu.getContentPane().add(label_id);
					    			    				field_id = new JTextField();
					    			    				field_id.setBounds(143, 56, 86, 20);
					    			    				frmTeacherDeleteStu.getContentPane().add(field_id);
					    				    			btnLogIn.setVisible(true);
					    				    			frmTeacherDeleteStu.setVisible(true);						    				    			
					    				    			JButton delete_btnOk = new JButton("OK");
					    				    			delete_btnOk.setBounds(143, 150, 86, 25);
					    				    			frmTeacherDeleteStu.add(delete_btnOk);
					    				    			
					    				    			JLabel lblNewLabel2 = new JLabel(""); 
					    				    			lblNewLabel2.setIcon(new ImageIcon(teacher_image_path));
					    				    		 	lblNewLabel2.setBounds(200, -50, 500, 550);
					    				    		 	frmTeacherDeleteStu.getContentPane().add(lblNewLabel2);  			    			    		 	
					    				    			frmTeacherDeleteStu.setVisible(true);	
					    				    			//DELETE STUDENT BUTTON OK ACTION LISTENER
					    				    			delete_btnOk.addActionListener(new ActionListener() {
											    				public void actionPerformed(ActionEvent arg0) {
											    					
											    					
											    					try {
											    						//STUDENT CHECKED, DELETED IF EXISTS IN THE SYSTEM										    					
											    						if(hmStudents.containsKey(field_id.getText())) 
																		{		
																			teacher.deleteStudent(field_id.getText());
																			//SUCCESSFULL DELETION MESSAGE SHOWN
																			JOptionPane.showMessageDialog(frmTeacherDeleteStu, "Student with ID "+field_id.getText()+" deleted successfully!" ,"Information",JOptionPane.INFORMATION_MESSAGE);
																			//STUDENT REMOVED FROM STUDENT HASHMAP
																			hmStudents.remove(field_id.getText(), hmStudents.get(field_id.getText()));
																			//FILE UPDATED BASED ON UPDATED STUDENT HASHMAP
																			FileOperation.reWritetoStudentFileAfterDelete(student_txt_path,hmStudents);		
																			//UPDATED FILE READ AND STUDENT HASHMAP CHECKED
																			readfromFileToHm(student_txt_path,hmStudents);
																			frmTeacherDeleteStu.setVisible(false);																	
													    					frmTeacherMenu.setTitle("Menu");
													    					frmTeacherMenu.setBounds(200, 100, 700, 600);													    					frmTeacherMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
													    					frmTeacherMenu.add(selection_btnOk);
													    					field_id.setText("");
	    	     			
													    					btnCancel2.setBounds(115, 225, 100, 25); 	
													    				 	btnCancel2.setFont(new Font("Font.PLAIN", Font.BOLD, 12));
													    					btnCancel2.setBackground(new Color(50, 100, 140));
													    					frmTeacherMenu.getContentPane().add(btnCancel2);
													    					btnCancel2.setVisible(true);
													    						
													    					btnCancel2.addActionListener(new ActionListener() {
													    						public void actionPerformed(ActionEvent arg0) 
													    						{
													    							
													    							txtUserName.setText("");
													    							passwordField.setText("");
													    							frmTeacherMenu.setVisible(false);
													    							frmLogIn.setVisible(true);
													    							btnCancel2.setVisible(false);
													    							
													    						}
													    					}); 
													   
													    					ButtonGroup btnG = new ButtonGroup();
													    					btnG.add(addStudent);
													    					btnG.add(deleteStudent);
													    					btnG.add(takeDayOffDay);
													    					btnG.add(displayStudent);
													    					btnG.add(exit);
													    					
													    					
													    					JButton selection_btnOk = new JButton("OK");
													    					selection_btnOk.setBounds(115, 190, 86, 25);
													    					frmTeacherMenu.add(selection_btnOk);	
													    					frmTeacherMenu.getContentPane().add(lblMenu);			    	
													    					frmTeacherMenu.setVisible(true);
																		}
																		else {
																			//IF STUDENT DOES NOT EXIST IN THE SYSTEM MESSAGE SHOWN
																			JOptionPane.showMessageDialog(frmTeacherDeleteStu, "Student can not found!" ,"Error!",JOptionPane.ERROR_MESSAGE);
																		}
																	
											    					} catch (FileNotFoundException e) {//FILE OPERATION TRY-CATCH BLOCKS
																		// TODO Auto-generated catch block
																		e.printStackTrace();
																	} catch (InterruptedException e) {
																		// TODO Auto-generated catch block
																		e.printStackTrace();
																	}
											    					
											    				}
					    				    			});
							    						
					    							}
							    					//TEACHER SELECTION= TAKE DAY OFF	
							    					else if(takeDayOffDay.isSelected())
					    							{
							    						//FLAG =TRUE AS DEFAULT,IF TEACHER HAS ALREADY TAKEN DAY OFF THEN FLAG=FALSE
							    						if(!flag_teacher_dayOff) //IF TEACHER HAS ALREADY TAKEN DAY OFF
							    						{
							    							//WARNING MESSAGE SHOWN
								    						JOptionPane.showMessageDialog(frmTeacherMenu, "You have already been taken "+teacher.getDayOff()+" as day off!" ,"Warning!",JOptionPane.WARNING_MESSAGE);
								    						//takeDayOffDay.setEnabled(false);//DAY OFF NOT AVAILABLE ANYMORE
							    						}
							    						//TEACHER SELECTION= DAY OFF
							    						else 
							    						{
							    							frmTeacherMenu.setVisible(false);
											    			String days[] =  new String[3]; //DAY OFF DAYS
															days[0]="Day Off Days";
															days[1]="Saturday";
															days[2]="Sunday";
															
															JComboBox<Object> cbDayOff = new JComboBox<Object>(days);
															cbDayOff.setBounds(200,70,120,30);
															frmTeacherDayOff.getContentPane().add(cbDayOff);
															
															JLabel label6 = new JLabel("Choose Day To Take Day Off: ");
															label6.setBounds(20,70,180,30);
															frmTeacherDayOff.getContentPane().add(label6);
															
															 JButton btndayOff= new JButton("OK");
															 btndayOff.setBounds(350, 70, 86, 30);
															 frmTeacherDayOff.getContentPane().add(btndayOff);
															 
															 JLabel lblNewLabel2 = new JLabel(""); 
							    				    		 lblNewLabel2.setIcon(new ImageIcon(teacher_image_path));
							    				    		 lblNewLabel2.setBounds(300, -50, 500, 550);
							    				    		 frmTeacherDayOff.getContentPane().add(lblNewLabel2);  			   				    		 	
															 frmTeacherDayOff.setVisible(true);	
															//DAY OFF CHOICE BUTTON OK ACTION LISTENER
															 btndayOff.addActionListener(new ActionListener() {
												    				public void actionPerformed(ActionEvent arg0)
												    				{
												    					String dayOff=(String) cbDayOff.getItemAt(cbDayOff.getSelectedIndex());
												    					//IF TEACHER CHOSEN VALID DAY OF FROM THE COMBOBOX
												    					if(!dayOff.equalsIgnoreCase(days[0])) 
																		{
																			//TEACHER DAY OFF SETTED, INFORMATION MESSAGE SHOWN
																			teacher.setDayOff(dayOff);
																			JOptionPane.showMessageDialog(frmTeacherDayOff, "You have been taken "+dayOff+" as day off successfully!" ,"Information",JOptionPane.INFORMATION_MESSAGE);
																			frmTeacherDayOff.setVisible(false);	
													    					frmTeacherMenu.setTitle("Menu");
													    					frmTeacherMenu.setBounds(200, 100, 700, 600);
													    					frmTeacherMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
													    					
													    					btnCancel2.setBounds(115, 225, 100, 25); 	
													    				 	btnCancel2.setFont(new Font("Font.PLAIN", Font.BOLD, 12));
													    					btnCancel2.setBackground(new Color(50, 100, 140));
													    					frmTeacherMenu.getContentPane().add(btnCancel2);
													    					btnCancel2.setVisible(true);
													    					btnCancel2.addActionListener(new ActionListener() {
													    						public void actionPerformed(ActionEvent arg0) 
													    						{
													    							txtUserName.setText("");
													    							passwordField.setText("");
													    							frmTeacherMenu.setVisible(false);
													    							frmLogIn.setVisible(true);
													    							btnCancel2.setVisible(false);
													    							
													    						}
													    					}); 
													   
													    					ButtonGroup btnG = new ButtonGroup();
													    					btnG.add(addStudent);
													    					btnG.add(deleteStudent);
													    					btnG.add(takeDayOffDay);
													    					btnG.add(displayStudent);
													    					btnG.add(exit);
													    					
													    					JButton selection_btnOk = new JButton("OK");
													    					selection_btnOk.setBounds(115, 190, 86, 25);
													    					frmTeacherMenu.add(selection_btnOk);	
													    					frmTeacherMenu.getContentPane().add(lblMenu);			    	
													    					frmTeacherMenu.setVisible(true);
																			flag_teacher_dayOff=false;
																		}	
												    					else {
												    						flag_teacher_dayOff=true;
												    					}
												    				}
											    			 });  																
						    			           }	
					    							}							    					
							    					//-----------
							    					//TEACHER SELECTION= DISPLAY STUDENT
							    					else if(displayStudent.isSelected()){
							    						frmTeacherMenu.setVisible(true);
							    						Student student = Main.getStudent();
							    						//GUI TABLE ACTIVATED, AND SHOWN ON THE SCREEN BASED ON RECORDED STUDENT TXT FILE
							    						//AND TABLE TYPE, THERE IS SEVERAL TABLE DISPLAY TYPES,SUCH AS LESSON,TEACHER,STUDENT
							    						GUI table = new GUI(student_txt_path,student,3);
							    					}
							    					//-----------
							    					//TEACHER SELECTION= EXIT
							    					else if(exit.isSelected()){
							    						System.exit(0);
							    					}
							    											    					
						    				}					    				
		    						});			    				   					
				    			  } 	
				    			else 
				    			{
				    				JOptionPane.showMessageDialog(frmLogIn, "Teacher does not exist!" ,"Error!",JOptionPane.ERROR_MESSAGE);
				    			}				    			  
			    			}
		    			 }); 
		    			//-----------
		    			 //TEACHER SELECTION=CANCEL BUTTON, TEACHER DIRECTED TO THE LOG IN PAGE
		    			 	btnCancel.setBounds(220, 95, 74, 25);
	    					frmLogIn.getContentPane().add(btnCancel);
	    					btnCancel.addActionListener(new ActionListener() {
	    						public void actionPerformed(ActionEvent arg0) 
	    						{
	    							cbUser.setVisible(true);
	    		    				button.setVisible(true);
	    		    				label.setVisible(true);
	    		    				btnTeacherLogIn.setVisible(false);
    								lblUserName.setVisible(false);  
    								lblPassword.setVisible(false);
    								btnTeacherLogIn.setVisible(false);
    								txtUserName.setVisible(false);
    								passwordField.setVisible(false);
    								btnCancel.setVisible(false);
	    							frmLogIn.setVisible(true);
	    						}
	    					});
		    			 
		    			//-----------
    				 }
    				 //USER TYPE= MANAGER 
    				 else if(cbUser.getItemAt(cbUser.getSelectedIndex()).equals("MANAGER")  )
    				 {
    					//MANAGER USER NAME & PASSWORD TAKEN
	    				 cbUser.setVisible(false);
	    				 button.setVisible(false);
	    				 label.setVisible(false);
	    		    	 JLabel lblUserName = new JLabel("User Name: ");
	    				 lblUserName.setBounds(55, 31, 78, 14);
		    			 frmLogIn.getContentPane().add(lblUserName);
		    				
		    			 txtUserName = new JTextField();
		    			 txtUserName.setBounds(143, 28, 150, 20);
		    			 txtUserName.setColumns(10);
		    			 frmLogIn.getContentPane().add(txtUserName);    				
		    				
		    			 JLabel lblPassword = new JLabel("Password:");
		    			 lblPassword.setBounds(55, 65, 78, 14);
		    			 frmLogIn.getContentPane().add(lblPassword);
		    				
		    			 passwordField = new JPasswordField();
		    			 passwordField.setBounds(143, 59, 150, 20);
		    			 frmLogIn.getContentPane().add(passwordField);
		    			 btnLogIn.setVisible(false);

		    			 JButton manager_btnLgnOk= new JButton("Ok");
		    			 manager_btnLgnOk.setBounds(143, 95, 74, 25);
		    			 frmLogIn.add(manager_btnLgnOk);
		    			 frmLogIn.setVisible(true);	
		    			 btnCancel.setVisible(true);
		    			//MANAGER ENTRANCE BUTTON OK ACTION LISTENER
		    			 //-------------------------
		    			 manager_btnLgnOk.addActionListener(new ActionListener() {
			    				public void actionPerformed(ActionEvent arg0) {
			    				 //MANAGER USERNAME & PASSWORD CONTROL
			    					if(passwordField.getText().equalsIgnoreCase("manager") && passwordField.getText().equalsIgnoreCase(txtUserName.getText())) {
				    				frmLogIn.setVisible(false);		
				    				//MANAGER MENU CONSTRUCTION
				    				JRadioButton addStudent= new JRadioButton("Add Student");
				    				addStudent.setBounds(60, 51, 323, 23);
				    				addStudent.setSelected(true);
				    				frmManagerMenu.getContentPane().add(addStudent);
			    					
			    					JRadioButton deleteStudent = new JRadioButton("Delete student");
			    					deleteStudent.setBounds(60, 77, 323, 23);
			    					frmManagerMenu.getContentPane().add(deleteStudent);
			    					
			    					JRadioButton addTeacher = new JRadioButton("Add Teacher");
			    					addTeacher.setBounds(60, 103, 323, 23);
			    					frmManagerMenu.getContentPane().add(addTeacher);
			    					
			    					JRadioButton deleteTeacher = new JRadioButton("Delete Teacher");
			    					deleteTeacher.setBounds(60, 129, 323, 23);
			    					frmManagerMenu.getContentPane().add(deleteTeacher);
			    					
			    					JRadioButton displayTeachers = new JRadioButton("Display Teachers");
			    					displayTeachers.setBounds(60, 155, 323, 23);
			    					frmManagerMenu.getContentPane().add(displayTeachers);
			    					
			    					JRadioButton displayStudents = new JRadioButton("Display Students");
			    					displayStudents.setBounds(60, 181, 323, 23);
			    					frmManagerMenu.getContentPane().add(displayStudents);

			    					JRadioButton exit = new JRadioButton("Exit");
			    					exit.setBounds(60, 207, 323, 23);
			    					frmManagerMenu.getContentPane().add(exit);
			    					
			    					JLabel lblNewLabel2 = new JLabel(""); 
			    					lblNewLabel2.setIcon(new ImageIcon(manager_image_path));
			    				 	lblNewLabel2.setBounds(400, 0, 300, 300);
			    				 	frmManagerMenu.getContentPane().add(lblNewLabel2);
			    				 	
			    				 	btnCancel2.setBounds(180, 330, 86, 30);  
			    				 	btnCancel2.setFont(new Font("Font.PLAIN", Font.BOLD, 12));
			    					btnCancel2.setBackground(new Color(50, 100, 140));
			    					frmManagerMenu.getContentPane().add(btnCancel2);
			    					btnCancel2.setVisible(true);
			    				 	

			    					btnCancel2.addActionListener(new ActionListener() {
			    						public void actionPerformed(ActionEvent arg0) 
			    						{
			    							txtUserName.setText("");
			    							passwordField.setText("");
			    							frmManagerMenu.setVisible(false);
			    							frmLogIn.setVisible(true);
			    							btnCancel2.setVisible(false);
			    							
			    						}
				 						}); 
			    					
			    					ButtonGroup btnG = new ButtonGroup();
			    					btnG.add(addStudent);
			    					btnG.add(deleteStudent);
			    					btnG.add(addTeacher);
			    					btnG.add(deleteTeacher);
			    					btnG.add(displayTeachers);
			    					btnG.add(displayStudents);
			    					btnG.add(exit);
			    					
			    					JButton selection_btnOk = new JButton("OK");
			    					selection_btnOk.setBounds(180,280, 86, 30);
			    					frmManagerMenu.add(selection_btnOk);				    					
			    					frmManagerMenu.setVisible(true);	
			    					//MANAGER MENU SELECTION BUTTON OK ACTION LISTENER
			    					selection_btnOk.addActionListener(new ActionListener() {
					    				public void actionPerformed(ActionEvent arg0) {					    						
					    					//MANAGER SELECTION=ADD STUDENT
						    					if(addStudent.isSelected()){
						    						
						    						 frmManagerMenu.setVisible(false);
						    						 JButton btnManagerAddStu= new JButton("Enroll");
						    						 btnManagerAddStu.setBounds(143, 95, 86, 25);
						    						 frmManagerAddStu.getContentPane().add(btnManagerAddStu);
						    						
						    						 JLabel lblNewLabel2 = new JLabel(""); 
						    						 lblNewLabel2.setIcon(new ImageIcon(manager_image_path));
						    						 lblNewLabel2.setBounds(400, 70, 300, 300);
						    						//STUDENT ENROLL FRAME GENERATED WITH HEPER FUNCTION, NECESSARY FRAMES GIVEN AS PARAMETER
						    						 Student addedStudent=createTeacherStudentEnrollFrame(frmManagerAddStu,frmManagerMenu,btnManagerAddStu,addStudent,lblNewLabel2);				    						
						    						try {			
						    							  //NEW STUDENT CHECKED AND RETURNED IF VALID, THEN ADDED TO RECORDED STUDENTS HASHMAP
															Manager.enrollStudent(addedStudent);
														
													} catch (FileNotFoundException e) {//FILE OPERATION TRY-CATCH BLOCKS
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (InterruptedException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
				    							}
						    					//MANAGER SELECTION=DELETE STUDENT
						    					else if(deleteStudent.isSelected())
				    							{
						    						field_id.setText("");
						    						frmManagerMenu.setVisible(false);
						    						//STUDENT DELETION FRAME CREATED,STUDENT ID FIELD ADDED TO THE FRAME
						    						JLabel label_id = new JLabel("Student ID: ");
				    			    				label_id.setBounds(55, 41, 78, 14);
				    			    				frmManagerDeleteStu.getContentPane().add(label_id);
				    			    				field_id = new JTextField();
				    			    				field_id.setBounds(143, 41, 86, 20);
				    			    				frmManagerDeleteStu.getContentPane().add(field_id);
	
						    						JLabel lblNewLabel2 = new JLabel(""); 
						    						lblNewLabel2.setIcon(new ImageIcon(manager_image_path));
						    						lblNewLabel2.setBounds(275, 30, 250, 200);
						    						frmManagerDeleteStu.getContentPane().add(lblNewLabel2);
						    						
				    				    			btnLogIn.setVisible(true);
				    				    			frmManagerDeleteStu.setVisible(true);						    				    			
				    				    			JButton delete_btnOk = new JButton("OK");
				    				    			delete_btnOk.setBounds(143, 135, 86, 25);
				    				    			frmManagerDeleteStu.add(delete_btnOk);
				    				    			frmManagerDeleteStu.setVisible(true);	
				    				    			//STUDENT DELETION OK BUTTON ACTION LISTENER
				    				    			delete_btnOk.addActionListener(new ActionListener() {
										    				public void actionPerformed(ActionEvent arg0) {
										    					
										    					try {									
										    						//IF STUDENTS EXISTS IN THE SYSTEM
																	if(hmStudents.containsKey(field_id.getText())) 
																	{		
																		Manager.deleteStudent(field_id.getText());
																		//SUCCESSFULL DELETION MESSSAGE SHOWN
																		JOptionPane.showMessageDialog(frmManagerDeleteStu, "Student with ID "+field_id.getText()+" deleted successfully!" ,"Information",JOptionPane.INFORMATION_MESSAGE);
																		//STUDENT REMOVED FROM STUDENT HASHMAP
																		hmStudents.remove(field_id.getText(), hmStudents.get(field_id.getText()));
																		//FILE REWRITTEN BASED ON UPDATED HASHMAP
																		FileOperation.reWritetoStudentFileAfterDelete(student_txt_path,hmStudents);		
																		readfromFileToHm(student_txt_path,hmStudents);
																		frmManagerDeleteStu.setVisible(false);																	
												    					frmManagerMenu.setTitle("Menu");
												    					frmManagerMenu.setBounds(200, 100, 700, 500);
												    					frmManagerMenu.add(selection_btnOk);
												    					field_id.setText("");
									                                 	frmManagerMenu.setVisible(true);										                                 	
																	}
																	else {
																		//IF STUDENT DOES NOT EXISTS IN THE SYSTEM MESSAGE SHOWN
																		JOptionPane.showMessageDialog(frmManagerDeleteStu, "Student can not found!" ,"Error!",JOptionPane.ERROR_MESSAGE);
																	}
																
										    					} catch (FileNotFoundException e) {//FILE OPERATION TRY CATCH BLOCKS
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																} catch (InterruptedException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
										    					
										    				}
				    				    			});						    						
				    							}
				    				    		//--------------------------	
						    					//MANAGER SELECTION= ADD TEACHER
						    					else if(addTeacher.isSelected()){
						    						 frmManagerMenu.setVisible(false);
						    						 JButton btnManagerAddTeacher= new JButton("Enroll");
						    						 btnManagerAddTeacher.setBounds(143, 95, 86, 25);
						    						 frmManagerAddTeacher.getContentPane().add(btnManagerAddTeacher);
						    						//TEACHER ENROLL FRAME CREATED WITH HELPER FUNCTION, TEACHER INFORMATION TAKEN FROM THE MANAGER AND CHECKED IN THE HEPLER FUNCTION
						    						 //TEACHER RETURNED IF VALID, IF TEACHER IS NOT VALID THEN WARNING MESSAGE SHOWN
					                 				 Teacher addedTeacher=createManagerTeacherEnrollFrame(frmManagerAddTeacher,frmManagerMenu,btnManagerAddTeacher,addTeacher);								    								
						    																					
						    					}	
						    					//MANAGER SELECTION= DELETE TEACHER
						    					else if(deleteTeacher.isSelected())
						    					{
						    						try {
						    							//IF DELETION CHOSEN REPEATEDLY, TEACHER HASHMAP UPDATED EVERYTIME BASED ON DELETION UPDATED TEACHER FILE
														readfromFileToTeacherHm(teacher_txt_path,hm_teachers);
													} catch (FileNotFoundException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
						    						field_id.setText("");
						    						frmManagerMenu.setVisible(false);
						    						//TEACHER NAME TAKEN TO PERFORM TEACHER DELETION
						    						JLabel label_id = new JLabel("Teacher name: ");
				    			    				label_id.setBounds(55, 41, 100, 14);
				    			    				frmManagerDeleteTeacher.getContentPane().add(label_id);
				    			    				field_id = new JTextField();
				    			    				field_id.setBounds(160, 41, 86, 20);
				    			    				frmManagerDeleteTeacher.getContentPane().add(field_id);
				    				    			btnLogIn.setVisible(true);
				    				    			frmManagerDeleteTeacher.setVisible(true);						    				    			
				    				    			JButton delete_btnOk = new JButton("OK");
				    				    			delete_btnOk.setBounds(160, 135, 86, 25);
				    				    			frmManagerDeleteTeacher.add(delete_btnOk);
				    				    							    				    	
						    						JLabel lblNewLabel2 = new JLabel(""); 
						    						lblNewLabel2.setIcon(new ImageIcon(manager_image_path));
						    						lblNewLabel2.setBounds(275, 30, 250, 200);
						    						frmManagerDeleteTeacher.getContentPane().add(lblNewLabel2);											
				    				    			frmManagerDeleteTeacher.setVisible(true);
				    				    			//TEACHER DELETION BUTTON OK ACTION LISTENER
				    				    			delete_btnOk.addActionListener(new ActionListener() {
										    				public void actionPerformed(ActionEvent arg0) {
										    					
										    					try {		
										    						//TEACHER NAME CHECKED IF EXISTS IN THE SYSTEM
																	if(hm_teachers.containsKey(field_id.getText())) {		
																		Manager.deleteStudent(field_id.getText());
																		//SUCCESSFULL DELETION MESSAGE SHOWN
																		JOptionPane.showMessageDialog(frmManagerDeleteTeacher, "Teacher with name "+field_id.getText()+" deleted successfully!" ,"Information",JOptionPane.INFORMATION_MESSAGE);
																		//TEACHER DELETED FROM TEACHER HASHMAP
																		hm_teachers.remove(field_id.getText(), hm_teachers.get(field_id.getText()));
																		//TEACHER REWRITTEN BASED ON TEACHER HASHMAP
																		FileOperation.reWritetoTeacherFileAfterDelete(teacher_txt_path,hm_teachers);		
																		//UPDATED FILE READ AND HASHMAP CHECKED BASED ON THE DELETION UPDATED TEACHER FILE
																		readfromFileToTeacherHm(teacher_txt_path,hm_teachers);																	
																		frmManagerDeleteTeacher.setVisible(false);																	
												    					frmManagerMenu.setTitle("Menu");
												    					frmManagerMenu.setBounds(200, 100, 700, 500);
												    					frmManagerMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
												    					frmManagerMenu.add(selection_btnOk);
												    					field_id.setText("");
									                                 	frmManagerMenu.setVisible(true);
																	}
																	else {
																		//IF TEACHER DOES NOT EXISTS IN THE SYSTEM MESSAGE SHOWN
																		JOptionPane.showMessageDialog(frmManagerDeleteTeacher, "Teacher can not found!" ,"Error!",JOptionPane.ERROR_MESSAGE);
																	}
																
										    					} catch (FileNotFoundException e) {//FILE OPERATION TRY-CATCH BLOCKS
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																} catch (InterruptedException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
										    					
										    				}
				    				    			});					    				    		
						    					}
						    					 //MANAGER SELECTION= DISPLAY TEACHERS
						    					else if(displayTeachers.isSelected())
						    					{
						    						frmManagerMenu.setVisible(true);
						    						Student student = Main.getStudent();
						    						//GUI TABLE ACTIVATED, TABLE CONSTRUCTED BASED ON THE GIVEN FILE AND GIVEN TABLE TYPE
						    						GUI table = new GUI(teacher_txt_path,student,4);
						    					}
						    					//MANAGER SELECTION= DISPLAY STUDENTS
						    					else if(displayStudents.isSelected())
						    					{
						    						frmManagerMenu.setVisible(true);
						    						Student student = Main.getStudent();
						    						//GUI TABLE ACTIVATED, TABLE CONSTRUCTED BASED ON THE GIVEN FILE AND GIVEN TABLE TYPE
						    						GUI table = new GUI(student_txt_path,student,3);
						    					}
						    					//MANAGER SELECTION= EXIT
						    					else if(exit.isSelected()){
					    							System.exit(0);							    				
						    						
					    						}
					    				}	   		
	    						});		
				    			}
			    				else 
					    		{
									JOptionPane.showMessageDialog(frmManagerMenu, "Manager can not found!" ,"Error!",JOptionPane.ERROR_MESSAGE);
					    		}			    										    				
			    			}
		    			 });
				    			
			            //----------------------------
		    			    //MANAGER SELECTION= CANCEL
		    			    //MANAGER DIRECTED TO THE MANAGER LOG IN PAGE
		    			 	btnCancel.setBounds(220, 95, 74, 25);
		    			 	btnCancel.setFont(new Font("Font.PLAIN", Font.BOLD, 12));
	    					btnCancel.setBackground(new Color(50, 100, 140));
	    					frmLogIn.getContentPane().add(btnCancel);
	    					btnCancel.addActionListener(new ActionListener() {
	    						public void actionPerformed(ActionEvent arg0) 
	    						{
	    							cbUser.setVisible(true);
	    		    				button.setVisible(true);
	    		    				label.setVisible(true);
	    		    				manager_btnLgnOk.setVisible(false);
    								lblUserName.setVisible(false);  
    								lblPassword.setVisible(false);
    								txtUserName.setVisible(false);
    								passwordField.setVisible(false);
    								btnCancel.setVisible(false);
	    							frmLogIn.setVisible(true);
	    						}
	    					});
		    			 
		    			//----------------------------------
		    		   }
		    		}
		         }
    		 });
    		 
    		 	//STUDENT SELECTION = LOG IN    
    		 	//STUDENT LOG IN OK BUTTON ACTION LISTENER
				btnLogIn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{ 
						//STUDENT EXISTENCE CHECKED FROM THE STUDENT HASHMAP
						if(hmStudents.containsKey((studentIDField.getText()))  && studentIDField.getText().length()==10 ) 
						{
							frmLogin.setVisible(false);
							//IF STUDENT EXIST IN THE SYSTEM
    					try {
    						//STUDENT SEARCHED AND RETURNED FROM THE HASHMAP. KEY=STUDENT ID, VALUE=STUDENT
			    			student = hmStudents.get(studentIDField.getText());
							mp = new MenuPage(student);				
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mp.getFrame().setVisible(true);
	    			}
					
	    			else 
	    			{
	    				//STUDENT ID LENGTH=10 , STUDENT ID CANT NOT BE "" OR LENGTH !=10
 						if(!hmStudents.containsKey((studentIDField.getText()))  && studentIDField.getText().length()==10 ) 
 						{
 							JOptionPane.showMessageDialog(frmEnroll, "ID can not found!" ,"Error!",JOptionPane.ERROR_MESSAGE);
 						}
 						else if(studentIDField.getText().equalsIgnoreCase("")) 
	    				{
								JOptionPane.showMessageDialog(frmEnroll, "ID field can not be empty!\n" ,"Error!",JOptionPane.ERROR_MESSAGE);
	    				}
 						else if(studentIDField.getText().length()!=10) 
	    				{
								JOptionPane.showMessageDialog(frmEnroll, "Wrong Student ID Format!\nExample Student ID Usage: 2018510086" ,"Warning!",JOptionPane.ERROR_MESSAGE);
	    				}
 					}
							
							
					}
				});
				btnLogIn.setBounds(140, 105, 89, 23);
			 	frmLogIn.getContentPane().add(btnLogIn); 			 		    			    	
}	
	
//TEXT FIELD CREATION HELPER FUNCTION
public static void createTextField(String text, int index,JTextField f1, JFrame fr) 
{
	 JLabel label = new JLabel(text);
	 label.setBounds(125, 31 + index, 100, 25);
	 fr.getContentPane().add(label);
		
	 f1.setBounds(220, 31 + index, 100, 25);
	 fr.getContentPane().add(f1);

}
//STUDENT INFORMATION CHECK HELPER FUNCTION
public static boolean checkStudentInfo(Student student, HashMap<String,Student> students) 
{
	boolean flag =true;
	String birthdate = student.getBirthdate();
	//STUDENT EXISTENCE CONTROL
	for (Map.Entry<String, Student> entry : students.entrySet()) { // checks the id is unique or not
		if(entry!=null) 
		{
			if(student.getId().equals(entry.getKey()))
			{
			   flag = false;
				JOptionPane.showMessageDialog(frmLogIn, "This ID is already exist in the system" ,"Error!",JOptionPane.ERROR_MESSAGE);
			   return flag;
			   }
		}
	}
	//STUDENT ID CONTROL
	if(student.getId().length()!=10) { // checking id length
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong Student ID Format\nExample Student ID Usage: 2018510086" ,"Error!",JOptionPane.ERROR_MESSAGE);
		return flag;
		}
	//STUDENT ID CONTENT CONTROL
	for (int i = 0; i < student.getId().length(); i++) 
	{
		if(!((int)(student.getId().charAt(i))<=57 && (int)(student.getId().charAt(i))>=48) ) 
		{
			flag=false;
			JOptionPane.showMessageDialog(frmLogIn, "Wrong Student ID Format!\nExample Student ID Usage: 2018510086" ,"Error!",JOptionPane.ERROR_MESSAGE);
			return flag;
		}	
	}
	//STUDENT EMAIL CONTENT CONTROL, EMAIL MUST CONTAIN @
	if(!student.getEmail().contains("@")) // checks email
	{
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong  Email Format\nExample  email: name.surname@gmail.com" ,"Error!",JOptionPane.ERROR_MESSAGE);

		return flag;
	}
	//STUDENT PHONE NUMBER CONTENT CONTROL,PHONE NUMBER LENGTH=11
	if(student.getPhoneNum().length()!=11 ) 
	{
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong Phone Number Format!\nExample Phone Number: 05334567812" ,"Error!",JOptionPane.ERROR_MESSAGE);
		return flag;
	}
	//STUDENT BIRTHDAY CONTENT CONTROL,EXAMPLE USAGE MESSAGE SHOWN
	for (int i = 0; i < student.getPhoneNum().length(); i++) {
		if(!((int)(student.getPhoneNum().charAt(i))<=57 && (int)(student.getPhoneNum().charAt(i))>=48) ) {
			JOptionPane.showMessageDialog(frmLogIn, "Wrong Phone Number Format!\nExample Phone Number: 05334567812" ,"Error!",JOptionPane.ERROR_MESSAGE);

			flag=false;
			return flag;
			}	
	}
	if(birthdate.length()!=10) 
	{
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong Birthdate Format!\nExample Birthdate: XX/XX/XXXX" ,"Error!",JOptionPane.ERROR_MESSAGE);
		return flag;
	}
	if(birthdate.length()==10) 
	{
		boolean validateDate = validateDate(birthdate);
		if(!validateDate) 
		{
			flag=false;
			JOptionPane.showMessageDialog(frmLogIn, "Wrong Birthdate Format!\nExample Birthdate: MM/DD/YYYY" ,"Error!",JOptionPane.ERROR_MESSAGE);
			return flag;
		}
		
	}
	// add birthday
	return flag;
}

//ENROLL BIRTHDATE VALIDATION CONTROLS
	public static boolean validateDate(String strDate)
	   {
		//Check if date is 'null' */
		if (strDate.trim().equals(""))
		{
		    return true;
		}
		//Date is not 'null'
		else
		{
		    //Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
		    sdfrmt.setLenient(false);
		    // Create Date object parse the string into date 
		    try
		    {
		        Date javaDate = sdfrmt.parse(strDate); 
		    }
		    //Date format is invalid 
		    catch (ParseException e)
		    {
		        return false;
		    }
		    //Return true if date format is valid 
		    return true;
		}
}
	
//READING GIVEN FILE AND COMPARING WITH GIVEN HASHMAP, UPDATING HASHMAP BASED ON THE GIVEN FILE
public static void readfromFileToHm(String path,HashMap<String,Student> hmStudent) throws FileNotFoundException  //HashMap den yapal�m
{
	int rollNum =0;
	try {
		  File file = new File(path);
			 Scanner scn = new Scanner(file);
		     while(scn.hasNext()) {
		    	 rollNum++;//STUDENT ROLL NUM= FILE LINE
		    	 String data= scn.nextLine();			    	 
		    	 String[] line= data.split(",");//RECORDED STUDENT INFORMATIONS
		    	 if(line[0]!=null) {
			    	 if(!hmStudent.containsKey(line[0])) {
			    		 //CHECKING STUDENT HASHMAP AND ADDING IF STUDENT IS NOT IN THE HASHMAP
			    		 Student student= new Student(rollNum,line[0],line[1],line[2],line[3],line[4],line[5],line[6],line[7]);
			    		 hmStudent.put(student.getId(), student); 		
			    	 }
		    	 }
		     }
		     scn.close();
	}
	catch (Exception e) {
	      System.out.println("File not found");
	      e.printStackTrace();
	    }
}

//READING GIVEN FILE AND COMPARING WITH GIVEN HASHMAP, UPDATING HASHMAP BASED ON THE GIVEN FILE
public static void readfromFileToTeacherHm(String path,HashMap<String,Teacher> hm_teacher) throws FileNotFoundException  //HashMap den yapal�m
{

	try {
		  File file = new File(path);
			 Scanner scn = new Scanner(file);
		     while(scn.hasNext()) {	    
		    	 String data= scn.nextLine();
		    	 String[] line= data.split(",");
		    	 if(line[0]!="" &&!hm_teacher.containsKey(line[0])) {
		    		 Lesson lesson= new Lesson();
		    		 lesson.setLessonName(line[2]);
		    		 Teacher teacher= new Teacher(line[0],line[1],lesson);//RECORDED TEACHER INFORMATIONS
			    	//CHECKING TEACHER HASHMAP AND ADDING IF TEACHER IS NOT IN THE HASHMAP
		    		 hm_teacher.put(teacher.getFirstName(), teacher); 		
		    	 }
		     }
		     scn.close();
	}
	catch (Exception e) {
	      System.out.println("File not found");
	      e.printStackTrace();
	    }
}

//GETTER FUNCTIONS
public static int getRollNumber() {
	return rollNumber;
}

public static void setRollNumber(int rollNumber) {
	Main.rollNumber = rollNumber;
}
public static Student getStudent() {
	return student;
}

public HashMap<String, Student> get_hmStudents() {
	return hmStudents;
}
public HashMap<String, Teacher> get_hmTeachers() {
	return hm_teachers;
}

public School getSchool() 
{
	return this.school;
}

//TEACHER ENROLL FRAME GENERATION & CONTROL HELPER FUCTION
public Student createTeacherStudentEnrollFrame(JFrame frmTeacherAddStu,JFrame frmTeacherMenu,JButton btnAddStu,JRadioButton addStudent,JLabel lblNewLabel2) {
	//FRAME CONSTRUCTION
	frmEnroll2.setBounds(200, 100, 700, 600); // boyutu deg�st� 3.55
	frmEnroll2.getContentPane().setBackground(new Color(173, 216, 230));
	frmEnroll2.setTitle("Enroll");
	frmEnroll2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmEnroll2.getContentPane().setLayout(null);
	
	
	Student addedStudent=new Student();
	JLabel title = new JLabel("Student Enroll Information ");
	title.setBounds(150, 16, 200, 50);
	frmEnroll2.getContentPane().add(title);	
 	frmEnroll2.getContentPane().add(lblNewLabel2); 
	 
	 createTextField("Name: ",50,studentName,frmEnroll2);	
	 createTextField("Surname: ",100,studentSurname,frmEnroll2);
	 createTextField("Phone Number: ",150,studentPhone,frmEnroll2);
	 createTextField("Gender: ",200,studentGender,frmEnroll2);
	 createTextField("ID: ",250,studentID,frmEnroll2);//check id control
	 createTextField("Email: ",300,studentEmail,frmEnroll2);
	 createTextField("Birthdate: ",350,studentBirthdate,frmEnroll2);
	 createTextField("Section: ",400,studentSection,frmEnroll2);
	
	 btnAddStu=new JButton("Enroll");  
	 btnAddStu.setBounds(220,480,100,30);
	 frmLogIn.getContentPane().add(btnAddStu);
	 frmEnroll2.getContentPane().add(btnAddStu);
	 frmEnroll2.setVisible(true);
	//ENROLL STUDENT BUTTON ACTION LISTENER
	 btnAddStu.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent arg0) {
			 setRollNumber(rollNumber+1);
			 boolean flag=true;
			//STUDENT INFORMATION CONTROLS AND WARNING MESSAGE SHOWNS
			 Student stu = new Student(getRollNumber(), studentID.getText(),studentSection.getText(),studentName.getText(),studentSurname.getText(),studentGender.getText(),studentPhone.getText(),studentBirthdate.getText(),
					 studentEmail.getText());
			 
			 		//IF TEXT FIELD IS LEFT EMPTY
					if(studentName.getText().isEmpty() || studentGender.getText().isEmpty() || studentSurname.getText().isEmpty() || studentPhone.getText().isEmpty()
							|| studentID.getText().isEmpty() || studentEmail.getText().isEmpty() || studentBirthdate.getText().isEmpty() || studentSection.getText().isEmpty()) 
					{
						flag=false;
						JOptionPane.showMessageDialog(frmEnroll2, "Please fill all informations!" ,"Warning!",JOptionPane.WARNING_MESSAGE);							

					}
					//STUDENT INFORMATION VALIDATION CHECK
					else if(!checkStudentInfo(stu,hmStudents)) 
					{
						flag=false;		
					}
					else 
					{
						
						if(school.getCapacity()!=school.getIndex()) 
						{	flag=true;							
							JOptionPane.showMessageDialog(frmEnroll2, "Student Has Been Enrolled!" ,"Information",JOptionPane.INFORMATION_MESSAGE);
						}						
					}
					//IF ENROLL INFORMATION IS VALID
					if(flag) {
						//STUDENT ATTRIBUTE ASSIGNING
						addedStudent.setRollNum(getRollNumber());
						addedStudent.setId(studentID.getText());
						addedStudent.setSection(studentSection.getText());	
						addedStudent.setFirstName(studentName.getText());
						addedStudent.setLastName(studentSurname.getText());	
						addedStudent.setGender(studentGender.getText());	
						addedStudent.setPhoneNum(studentPhone.getText());	
						addedStudent.setBirthdate(studentBirthdate.getText());	
						addedStudent.setEmail(studentEmail.getText());
						//STUDENT ADDITION TO STUDENT HASHMAP
						school.addStudent_hm(addedStudent);
						//FILE UPDATE BASED ON NEW UPDATED HASHMAP
			    		file_student.writetoStudentFile(student_txt_path,addedStudent,1);
			    		//CLEARING TEXT FIELDS FOR REPETEDLY STUDENT ENROLLMENT
						studentName.setText("");
						studentSurname.setText("");
						studentPhone.setText("");
						studentGender.setText("");
						studentID.setText("");
						studentEmail.setText("");
						studentBirthdate.setText("");
						studentSection.setText("");
						frmEnroll2.setVisible(false);
						frmTeacherAddStu.setVisible(false);				    	
						frmTeacherMenu.setVisible(true);
						
					}
					
				}	    				 					
		});
	 return addedStudent;
}
//TEACHER INFORMATION CHECK HELPER FUNCTION
public static boolean checkTeacherInfo(Teacher teacher, HashMap<String,Teacher> teachers) 
{
	boolean flag =true;
	String birthdate = teacher.getBirthdate();
	//TEACHER EXISTENCE CONTROL
	for (Map.Entry<String, Teacher> entry : teachers.entrySet()) { 
		if(entry!=null) 
		{
			if(teacher.getFirstName().equals(entry.getKey()))
			{
				//TEACHER EXISTENCE MESSAGE SHOWN
			   flag = false;
				JOptionPane.showMessageDialog(frmLogIn, "This teacher already exists in the system!" ,"Error!",JOptionPane.ERROR_MESSAGE);
			   return flag;
			   }
		}
	}
	//TEACHER EMAIL CONTENT CONTROL
	if(!teacher.getEmail().contains("@")) // checks email
	{
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong  Email Format\nExample  email: name.surname@gmail.com" ,"Error!",JOptionPane.ERROR_MESSAGE);

		return flag;
	}
	//TEACHER PHONE NUMBER CONTENT CONTROL
	if(teacher.getPhoneNum().length()!=11 ) 
	{
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong Phone Number Format!\nExample Phone Number: 05334567812" ,"Error!",JOptionPane.ERROR_MESSAGE);
		return flag;
	}
	for (int i = 0; i < teacher.getPhoneNum().length(); i++) {
		if(!((int)(teacher.getPhoneNum().charAt(i))<=57 && (int)(teacher.getPhoneNum().charAt(i))>=48) ) {
			JOptionPane.showMessageDialog(frmLogIn, "Wrong Phone Number Format!\nExample Phone Number: 05334567812" ,"Error!",JOptionPane.ERROR_MESSAGE);

			flag=false;
			return flag;
			}	
	}
	//TEACHER BIRTHDATE CONTENT CONTROL
	if(birthdate.length()!=10) 
	{
		flag=false;
		JOptionPane.showMessageDialog(frmLogIn, "Wrong Birthdate Format!\nExample Birthdate: XX/XX/XXXX" ,"Error!",JOptionPane.ERROR_MESSAGE);
		return flag;
	}
	if(birthdate.length()==10) 
	{
		boolean validateDate = validateDate(birthdate);
		if(!validateDate) 
		{
			flag=false;
			JOptionPane.showMessageDialog(frmLogIn, "Wrong Birthdate Format!\nExample Birthdate: MM/DD/YYYY" ,"Error!",JOptionPane.ERROR_MESSAGE);
			return flag;
		}
		
	}
	// add birthday
	return flag;
}

public Teacher createManagerTeacherEnrollFrame(JFrame frmManagerAddTeacher,JFrame frmManagerMenu,JButton btnAddTeacher,JRadioButton addTeacher) {
	//FRAME CONSTRUCTION
	frmEnroll3.getContentPane().setBackground(new Color(173, 216, 230));
	frmEnroll3.setTitle("Enroll");
	frmEnroll3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmEnroll3.getContentPane().setLayout(null);
	
	
	Teacher addedTeacher=new Teacher();
	JLabel title = new JLabel("Teacher Enroll Information ");
	title.setBounds(150, 16, 200, 50);
	frmLogIn.getContentPane().add(btnAddTeacher);
	frmEnroll3.getContentPane().add(title);
			
	JLabel lblNewLabel2 = new JLabel(""); 
	lblNewLabel2.setIcon(new ImageIcon(manager_image_path));
	lblNewLabel2.setBounds(325, 130, 250, 200);
	frmEnroll3.getContentPane().add(lblNewLabel2);
	
	 createTextField("Name: ",50,teacherName,frmEnroll3);	
	 createTextField("Surname: ",100,teacherSurname,frmEnroll3);
	 createTextField("Phone Number: ",150,teacherPhone,frmEnroll3);
	 createTextField("Gender: ",200,teacherGender,frmEnroll3);
	 createTextField("Email: ",250,teacherEmail,frmEnroll3);
	 createTextField("Birthday: ",300,teacherBirthday,frmEnroll3);
	 createTextField("Lesson Name: ",350,teacherLessonName,frmEnroll3);
	 createTextField("Lesson Credit: ",400,teacherLessonCredit,frmEnroll3);
	
	 btnAddTeacher=new JButton("Enroll");  
	 btnAddTeacher.setBounds(220,480,100,30); 
	 frmEnroll3.getContentPane().add(btnAddTeacher);
	 frmEnroll3.setVisible(true);
	//ENROLL TEACHER BUTTON ACTION LISTENER
	 btnAddTeacher.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent arg0) {				 
			 boolean flag=true;
			//TEACHER INFORMATION CONTROLS AND WARNING MESSAGE SHOWNS
			 Lesson lesson= new Lesson(teacherLessonName.getText(),Integer.parseInt(teacherLessonCredit.getText()));
			 Teacher teacher= new Teacher(teacherName.getText(),teacherSurname.getText(),teacherPhone.getText(),teacherGender.getText()
					 ,teacherBirthday.getText(),teacherEmail.getText(),lesson);
			 		//IF TEXT FIELD IS LEFT EMPTY
			 		if(teacherName.getText().isEmpty() || teacherSurname.getText().isEmpty() || teacherPhone.getText().isEmpty() || teacherGender.getText().isEmpty()
							|| teacherBirthday.getText().isEmpty() || teacherEmail.getText().isEmpty() || teacherLessonName.getText().isEmpty() || teacherLessonCredit.getText().isEmpty()) 
					{
						flag=false;
						JOptionPane.showMessageDialog(frmEnroll2, "Please fill all informations!" ,"Warning!",JOptionPane.WARNING_MESSAGE);							

					}
			 		//TEACHER INFORMATION VALIDATION CHECK
					else if(!checkTeacherInfo(teacher,hm_teachers))
					{	
						flag=false;
					
					}
			 		//IF ENROLL INFORMATION IS VALID
					if(flag) {
				
						 addedTeacher.setFirstName(teacherName.getText());
						 addedTeacher.setLastName(teacherSurname.getText());	
						 addedTeacher.setGender(teacherGender.getText());	
						 addedTeacher.setPhoneNum(teacherPhone.getText());	
						 addedTeacher.setBirthdate(teacherBirthday.getText());	
						 addedTeacher.setEmail(teacherEmail.getText());
						 addedTeacher.setLesson(lesson);
						//Manager addTeacher() FUNCTION USED, IF TEACHER DOES NOT EXIST IN THE SYSTEM TRUE RETURNED
						 boolean canBeAdded=Manager.addTeacher(addedTeacher);	
						 if(canBeAdded) {
							 	//FILE UPDATE BASED ON NEW UPDATED HASHMAP
							 	file_teacher.writetoTeacherFile(teacher_txt_path,addedTeacher,1);
							 	//CLEARING TEXT FIELDS FOR REPETEDLY STUDENT ENROLLMENT
							 	teacherName.setText("");
					    		teacherSurname.setText("");
					    		teacherPhone.setText("");
					    		teacherGender.setText("");
					    		teacherEmail.setText("");
					    		teacherBirthday.setText("");
					    		teacherLessonName.setText("");
					    		teacherLessonCredit.setText("");
								frmEnroll3.setVisible(false);
								frmManagerAddTeacher.setVisible(false);				    	
								frmManagerMenu.setVisible(true);
								//SUCCESSFULL ENROLLMENT MESSAGE SHOWN
							    JOptionPane.showMessageDialog(frmManagerMenu, "Teacher has been enrolled successfully!" ,"Information",JOptionPane.INFORMATION_MESSAGE);	 
						 }
						 	//CLEARING TEXT FIELDS FOR REPETEDLY STUDENT ENROLLMENT
						 	teacherName.setText("");
				    		teacherSurname.setText("");
				    		teacherPhone.setText("");
				    		teacherGender.setText("");
				    		teacherEmail.setText("");
				    		teacherBirthday.setText("");
				    		teacherLessonName.setText("");
				    		teacherLessonCredit.setText("");		
					}
					
				}	    				 					
		});
	 return addedTeacher;
}
}