import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class MenuPage {

	//JFRAMES
	private JFrame mainFrame;
	private JFrame enrollCourseframe;
	private JFrame takeLessonframe;
	
	//JRADIO BUTTONS
    private JRadioButton rbtnEnrollCourse;
	private JRadioButton rbtnDisplayAllLessons;
    private JRadioButton rbtnShowMyLessons;
    private JRadioButton rbtnExit;
    private JRadioButton rbtnTakeExam;
 
    //FILE OPERATIONS
    private FileOperation file;   
    private String lesson_txt_path ="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\lessons.txt";
    private String examQuota_txt_path ="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\examQuota.txt"; 
    private String child_image_path = "C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\PngItem_1746580.png"; // child image
      
    //CLASS OBJECTS
    private Lesson lessons = new Lesson();
    private boolean studentCanTakeTest;  
    private Student student;
	private static Main main;
    private int totalCredits;
   
    
    public MenuPage(Student student) throws FileNotFoundException, InterruptedException
	{
		
    	studentCanTakeTest=false; 
		String stdDate = student.getBirthdate();
		int year = Integer.parseInt(stdDate.substring(6, 10));
		this.student=student;
		file = new FileOperation(lesson_txt_path);
		main = new Main();
		
    	//IMAGE LABEL
		JLabel lblNewLabel2 = new JLabel(""); 
		lblNewLabel2.setIcon(new ImageIcon(child_image_path));
		lblNewLabel2.setBounds(390, 0, 600, 600);
		
		//FRAME CONSTUCTION
		mainFrame = new JFrame();
		mainFrame.setTitle("Menu");
		mainFrame.getContentPane().setBackground(new Color(173, 216, 230));
		mainFrame.setBackground(new Color(178, 238, 238));
		mainFrame.setBounds(200, 100, 700, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		enrollCourseframe = new JFrame();
		enrollCourseframe.setTitle("Enroll Course");
		enrollCourseframe.getContentPane().setBackground(new Color(173, 216, 230));
		enrollCourseframe.setBackground(new Color(178, 238, 238));
		enrollCourseframe.setBounds(200, 100, 700, 600);
		enrollCourseframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		enrollCourseframe.getContentPane().setLayout(null);

		takeLessonframe = new JFrame(); 
		takeLessonframe.setTitle("Take Lesson");
		takeLessonframe.getContentPane().setBackground(new Color(173, 216, 230));
		takeLessonframe.setBackground(new Color(178, 238, 238));
		takeLessonframe.setBounds(200, 100, 700, 600);
		takeLessonframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		takeLessonframe.getContentPane().setLayout(null);
		
		rbtnTakeExam = new JRadioButton("Take Exam");
		rbtnTakeExam.setBounds(60, 129, 160, 23); // 60 155 109 23
		mainFrame.getContentPane().add(rbtnTakeExam);
		
		//8TH GRADE TAKE EXAM AVAILABILITY FLAG VALUE ASSINGN BASED ON THE STUDENT AGE
		if(14 <= 2021-year) 
		{						
			studentCanTakeTest =true;
		} 
		
		//STUDENT MENU
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setBounds(123, 30, 46, 14);
		mainFrame.getContentPane().add(lblMenu);
				
		rbtnEnrollCourse = new JRadioButton("Enroll Course");
		rbtnEnrollCourse.setBounds(60, 51, 109, 23);
		rbtnEnrollCourse.setSelected(true);
		mainFrame.getContentPane().add(rbtnEnrollCourse);
		
		rbtnDisplayAllLessons = new JRadioButton("Display All Lessons");
		rbtnDisplayAllLessons.setBounds(60, 77, 323, 23);
		mainFrame.getContentPane().add(rbtnDisplayAllLessons);
	
		rbtnShowMyLessons = new JRadioButton("Show my Lessons");
		rbtnShowMyLessons.setBounds(60, 103, 213, 23);
		mainFrame.getContentPane().add(rbtnShowMyLessons);
		
		rbtnExit = new JRadioButton("Exit");
		rbtnExit.setBounds(60, 155, 160, 23); // 60 155 109 23
		mainFrame.getContentPane().add(rbtnExit);
		
		lblNewLabel2.setIcon(new ImageIcon(child_image_path));
		lblNewLabel2.setBounds(390, 0, 600, 600);
		mainFrame.getContentPane().add(lblNewLabel2);
		
		ButtonGroup btnG = new ButtonGroup();
		btnG.add(rbtnEnrollCourse);
		btnG.add(rbtnDisplayAllLessons);
		btnG.add(rbtnTakeExam);
		btnG.add(rbtnShowMyLessons);
		btnG.add(rbtnExit);
		
		mainFrame.setVisible(true);		
		JButton menuBtnOk = new JButton("OK");
        //STUDENT MENU SELECTION OK BUTTON ACTION LISTENER
		menuBtnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{				
				totalCredits=0;
				//STUDENT SELECTION= ENROLL COURSE
				if(rbtnEnrollCourse.isSelected())
				{
					mainFrame.setVisible(false);
					int size = lessons.getCompulsoryLessons().size();
					//DEFAULT COMPULSORY LESSON QUEUE
					Queue<Lesson> temp = lessons.getCompulsoryLessons();
					String text ="You already enrolled these lessons:";
					JLabel label = new JLabel(text);
					label.setBounds(20,25,400,30);
					enrollCourseframe.getContentPane().add(label);
					int index =20;
					//COMPULSORY LESSON LABELS ADDED TO FRAME
					for (int i = 0; i < size; i++) {
						text= "> " + temp.peek().getLessonName() +
								" " + temp.peek().getCredit()+ " credits";
		                totalCredits+=temp.peek().getCredit();
		                temp.remove();
						label = new JLabel(text);
						if(i==4) 
						{
							label.setBounds(20,150,200,30);
							enrollCourseframe.getContentPane().add(label);
						}
						else {
						label.setBounds(20,index*(i+1)+50,200,30);
						enrollCourseframe.getContentPane().add(label);}
					}
					//ELECTIVE LESSEN CHOICE FRAME CONSTRUCTION
					String text5 = "Total credits of compulsory lessons = " +totalCredits;
					JLabel label5 = new JLabel(text5);
					label5.setBounds(20,195,500,30);
					enrollCourseframe.getContentPane().add(label5);
					
					String text2 = "You can choose elective lessons below:";
					JLabel label2 = new JLabel(text2);
					label2.setBounds(20,225,500,30);
					enrollCourseframe.getContentPane().add(label2);
					
					//ELECTIVE LESSON DEFAULT QUEUE
					Queue<Lesson> electiveLessons =lessons.getElectiveLessons();
					int queueSize = electiveLessons.size();
					Lesson[] elecLessons = new Lesson[queueSize];
					for (int i = 0; i < queueSize; i++) {
						elecLessons [i]= electiveLessons.peek();
						electiveLessons.remove();
					}
					String types[] =  new String[queueSize+1]; 
					types[0]="Elective Lessons";
					for (int i = 1; i < types.length; i++) {
						types[i]=elecLessons[i-1].getLessonName() + " (" + elecLessons[i-1].getCredit() + " credits)";
					}
					//ELECTIVE LESSON CHOICE COMBOBOX CONSTRUCTION
					String days[] =  new String[3]; 
					days[0]="Elective Lesson Day";
					days[1]="Saturday";
					days[2]="Sunday";
					
					JComboBox<Object> cbElevtiveLessons = new JComboBox<Object>(types);
					cbElevtiveLessons.setBounds(200,285,300,30);
					enrollCourseframe.getContentPane().add(cbElevtiveLessons);
					
					JLabel label3 = new JLabel("Elective Lesson 1: ");
					label3.setBounds(20,285,300,30);
					enrollCourseframe.getContentPane().add(label3);
					
					JComboBox<Object> cbElevtiveLessons_day = new JComboBox<Object>(days);
					cbElevtiveLessons_day.setBounds(200,325,200,30);
					enrollCourseframe.getContentPane().add(cbElevtiveLessons_day);
					
					JLabel label6 = new JLabel("Elective Lesson 1 day: ");
					label6.setBounds(20,325,200,30);
					enrollCourseframe.getContentPane().add(label6);
					
					JComboBox<Object> cbElevtiveLessons_2 = new JComboBox<Object>(types);
					cbElevtiveLessons_2.setBounds(200,375,300,30);
					enrollCourseframe.getContentPane().add(cbElevtiveLessons_2);
					
					JLabel label4 = new JLabel("Elective Lesson 2: ");
					label4.setBounds(20,375,300,30);
					enrollCourseframe.getContentPane().add(label4);
					
					JComboBox<Object> cbElevtiveLessons_day2 = new JComboBox<Object>(days);
					cbElevtiveLessons_day2.setBounds(200,425,200,30);
					enrollCourseframe.getContentPane().add(cbElevtiveLessons_day2);
					
					JLabel label7 = new JLabel("Elective Lesson 2 day: ");
					label7.setBounds(20,425,300,30);
					enrollCourseframe.getContentPane().add(label7);
					
					JButton lessonsOK = new JButton("OK");
					//ELECTIVE LESSON SELECTION BUTTON OK ACTION LISTENER
					lessonsOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) 
						{
							String lesson1 = (String) cbElevtiveLessons.getItemAt(cbElevtiveLessons.getSelectedIndex());
							String lesson2 = (String) cbElevtiveLessons_2.getItemAt(cbElevtiveLessons_2.getSelectedIndex());
							//IF SAME LESSON CHOSEN, WARNING MESSAGE SHOWN
							if(lesson1.equalsIgnoreCase(lesson2)) 
							{
 								JOptionPane.showMessageDialog(enrollCourseframe, "You can not choose same elective lessons" ,"Error!",JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								//IF SAME LESSON NAMES, AND DAYS CHECKED BASED ON THE TEACHER DAY OFF DAYS
								int credit_lesson1 = Integer.parseInt(lesson1.substring(lesson1.length()-10, lesson1.length()-9));
								int credit_lesson2 = Integer.parseInt(lesson2.substring(lesson2.length()-10, lesson2.length()-9));
								String lesson1_name = lesson1.substring(0, lesson1.length()-12);
								String lesson2_name = lesson2.substring(0, lesson2.length()-12);
								String day1= (String) cbElevtiveLessons_day.getItemAt(cbElevtiveLessons_day.getSelectedIndex());
								String day2= (String) cbElevtiveLessons_day2.getItemAt(cbElevtiveLessons_day2.getSelectedIndex());
								Lesson lesson = new Lesson();
								Teacher teacher1 = null;
								Teacher teacher2 = null;
								
								//CHECK IF TEACHER IS AVAILABLE ON SELECTED DAY
								HashMap<Teacher,Lesson> electiveLesson_teacher_hm= lesson.getElectiveLesson_teacher_hm(); //read file to HM								
								for(Entry<Teacher, Lesson> entry: electiveLesson_teacher_hm.entrySet()) {
									if(entry.getValue().getLessonName().equalsIgnoreCase(lesson1_name)) {
										teacher1=entry.getKey();
									}
									else if(entry.getValue().getLessonName().equalsIgnoreCase(lesson2_name)) {
										teacher2=entry.getKey();
									}
								}
								//CHECK IF SELECTED LESSONS CREDIT MATCHES WITH TOTAL LESSON CREDIT, IF NOT WARNING MESSAGE SHOWN
								if(!(credit_lesson1 +credit_lesson2 + totalCredits == lessons.getTotalCreditsCapacity()))
								{
	 								JOptionPane.showMessageDialog(enrollCourseframe, "You must have total "+ lessons.getTotalCreditsCapacity()+" credits" ,"Error!",JOptionPane.ERROR_MESSAGE);
								}
								else if(teacher1.getDayOff().equalsIgnoreCase(day1)) {
									JOptionPane.showMessageDialog(enrollCourseframe, "You can not take "+lesson1_name+" lesson on "+day1+"\nTeacher "+teacher1.getFirstName()
											+" is not available on "+day1+"\nPlease choose another day!","Error!",JOptionPane.ERROR_MESSAGE);
								}
								else if(teacher2.getDayOff().equalsIgnoreCase(day2)) {
									JOptionPane.showMessageDialog(enrollCourseframe, "You can not take "+lesson2_name+" lesson on "+day2+"\nTeacher "+teacher2.getFirstName()
									+" is not available on "+day2+"\nPlease choose another day!","Error!",JOptionPane.ERROR_MESSAGE);
								}
								else 
								{
									boolean flag= true;
									Queue<Lesson> takenLessons =lessons.getCompulsoryLessons();
									student.setCompulsoryLessons(takenLessons);									
									Lesson taken1 = lessons.searchElectiveLesson(lesson1_name);
									Lesson taken2 = lessons.searchElectiveLesson(lesson2_name);	
									//SELECTED LESSON DATE VALIDATION CONTROLS
									if(taken1 !=null && taken2 != null) 
									{										
										if(!day1.equalsIgnoreCase(days[0])&&!day2.equalsIgnoreCase(days[0])) {
											taken1.setElectiveLesson1_day(day1);
											taken2.setElectiveLesson2_day(day2);
											checkElectivelesson(taken1.getLessonName());
											checkElectivelesson(taken2.getLessonName());
											takenLessons.add(taken1);
											takenLessons.add(taken2);
											student.setElectiveLesson1(taken1);
											student.setElectiveLesson2(taken2);
										}
										else {
											flag=false;					
											enrollCourseframe.setVisible(true);
											JOptionPane.showMessageDialog(enrollCourseframe, "Invalid day choice!" ,"Warning!",JOptionPane.INFORMATION_MESSAGE);
										}

									}
									if(flag) {
										rbtnEnrollCourse.setVisible(false);
										enrollCourseframe.setVisible(false);
										lblNewLabel2.setIcon(new ImageIcon(child_image_path));
										lblNewLabel2.setBounds(390, 0, 600, 600);
										mainFrame.getContentPane().add(lblNewLabel2);
										mainFrame.setVisible(true);	
										//IF LESSON SELECTION IS VALID , THEN RECORDED TO LESSON FILE WITH ITS STUDENT INFORMATION
										FileOperation.writetoLessonFile(lesson_txt_path, takenLessons, Main.student); // writing to the lessons.txt file	
										JOptionPane.showMessageDialog(mainFrame, "You have enrolled the lessons successfully" ,"Message!",JOptionPane.INFORMATION_MESSAGE);
																																	
									}								
								}								
							}

						}});
					//FRAME BACKGROUND IMAGE
					lblNewLabel2.setIcon(new ImageIcon(child_image_path));
					lblNewLabel2.setBounds(390, 0, 600, 600);
					enrollCourseframe.getContentPane().add(lblNewLabel2);
					
					lessonsOK.setBounds(20,470,89, 23);
					enrollCourseframe.getContentPane().add(lessonsOK);
				
					enrollCourseframe.setVisible(true);
					//INFORMATION MESSAGE FOR TOTAL LESSON CREDITS
				    JOptionPane.showMessageDialog(enrollCourseframe, "Please choose elective lesson to complete \nYour total compulsory 23 lesson credits." ,"Warning!",JOptionPane.INFORMATION_MESSAGE);

					
				}
				//STUDENT SELECTION= DISPLAY ALL LESSONS
				else if(rbtnDisplayAllLessons.isSelected())
				{					
					mainFrame.setVisible(true);
					Student student = Main.getStudent();
					GUI table = new GUI(lesson_txt_path,student,1);//type=1 print lessons
				}
				//STUDENT SELECTION= DISPLAY MY LESSONS
				else if(rbtnShowMyLessons.isSelected())//type=2 print Student's individual lessons
				{			
					mainFrame.setVisible(true);
					Student student = Main.getStudent();
					GUI table = new GUI(lesson_txt_path,student,2);
				}
				//STUDENT SELECTION= TAKE EXAM
				else if(rbtnTakeExam.isSelected()) 
				{
					String filename = examQuota_txt_path;
					File textFile = new File(filename);
					Scanner in;
					int TESTCAPACITY=0;
					try {
						in = new Scanner(textFile);
						 TESTCAPACITY = in.nextInt();	//LEFT TEST CAPACITY RECORDED IN THE examQuota FILE, READED FROM THE FILE		
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//TAKE EXAM TEST CAPACITY CONTROLS&WARNING MESSAGES		       
					if(TESTCAPACITY!=0)
					{					
						TESTCAPACITY--;
						String message = "You have successfully enrolled to the test\nRemaining test capacity: " + TESTCAPACITY+"/1111";
						JOptionPane.showMessageDialog(mainFrame, message ,"Message!",JOptionPane.INFORMATION_MESSAGE);						
					}
					else 
					{
						JOptionPane.showMessageDialog(mainFrame, "Test capacity is full!\nYou can not take test." ,"Warning!",JOptionPane.WARNING_MESSAGE);
					}
					
					File file = new File(examQuota_txt_path ); 
					FileWriter fileWriter;
					try {
						fileWriter = new FileWriter(file);
					
					BufferedWriter bWriter = new BufferedWriter(fileWriter); 					
					bWriter.write(Integer.toString(TESTCAPACITY)); 				
					bWriter.close(); 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					if (!file.exists()) { try {
						file.createNewFile();
				
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} }
				}
				else if(rbtnExit.isSelected())
				{
					System.exit(0);
				}
			}
		});
		menuBtnOk.setBounds(218, 193, 89, 23);
		mainFrame.getContentPane().add(menuBtnOk);

		if(studentCanTakeTest==true) 
		{
			JOptionPane.showMessageDialog(mainFrame, "You can take test " ,"Information message!",JOptionPane.INFORMATION_MESSAGE);
		}
		else 
		{
			JOptionPane.showMessageDialog(mainFrame, "You can not take test!\nTest is only available for 8th grades " ,"Information message!",JOptionPane.INFORMATION_MESSAGE);
			rbtnTakeExam.setEnabled(false);
		} 
		
	}
    //ELECTIVE LESSON QUOTA CHECK HELPER FUNCTIONS
	private void checkElectivelesson(String name) 
	{
		if(name.equalsIgnoreCase(lessons.getElectiveLesson1Name()))
		{
			if(lessons.getElectiveLesson1()!=lessons.electiveCoursesQuota())
				lessons.setElectiveLesson1(); // capacity increases 1
			else 
			{
				JOptionPane.showMessageDialog(mainFrame, "This course is full!" ,"Message!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(name.equalsIgnoreCase(lessons.getElectiveLesson2Name())) 
		{
			if(lessons.getElectiveLesson2()!=lessons.electiveCoursesQuota())
				lessons.setElectiveLesson2();
			else 
			{
				JOptionPane.showMessageDialog(mainFrame, "This course is full!" ,"Message!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(name.equalsIgnoreCase(lessons.getElectiveLesson3Name()))
		{
			if(lessons.getElectiveLesson3()!=lessons.electiveCoursesQuota())
				lessons.setElectiveLesson3();
			else 
			{
				JOptionPane.showMessageDialog(mainFrame, "This course is full!" ,"Message!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(name.equalsIgnoreCase(lessons.getElectiveLesson4Name()))
		{
			if(lessons.getElectiveLesson4()!=lessons.electiveCoursesQuota())
				lessons.setElectiveLesson4();
			else 
			{
				JOptionPane.showMessageDialog(mainFrame, "This course is full!" ,"Message!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(name.equalsIgnoreCase(lessons.getElectiveLesson5Name()))
		{
			if(lessons.getElectiveLesson5()!=lessons.electiveCoursesQuota())
				lessons.setElectiveLesson5();
			else 
			{
				JOptionPane.showMessageDialog(mainFrame, "This course is full!" ,"Message!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
    //FRAME GETTER FUNCTION
	public JFrame getFrame() {
		return mainFrame;
	}
	public void setFrame(JFrame frame) {
		this.mainFrame = frame;
	}
}


