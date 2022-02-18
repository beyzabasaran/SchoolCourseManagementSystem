import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.*;

class GUI implements ActionListener{
	
	//ATTRIBUTES
	JFrame frame;
	JButton button;
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane scrollPane;
	Student student;
	Lesson  compulsoryLessons = new Lesson();
	Lesson lesson = new Lesson();
	private int type;
	
	//FILE OPERATIONS
	String filePath;
	String file;
	FileReader fileReader;
	JFileChooser fileChooser;
	public static String lessons_txt_path="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\lessons.txt";
	public static String student_txt_path="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\studentsList.txt"; 
	public static String teacher_txt_path="C:\\Users\\LENOVO\\eclipse-workspace\\SchoolCourseManagement\\src\\teacher.txt";	
		
	public GUI(String filePath, Student student,int type) //CONSTRUCTOR		
	{ 
		this.filePath=filePath;
		this.student=student;
		this.type=type;
		frame=new JFrame();
		tableModel=new DefaultTableModel();
		table=new JTable(tableModel);
		scrollPane=new JScrollPane(table);
		frame.add(scrollPane,BorderLayout.CENTER);		
		button=new JButton("Show");
		frame.add(button,BorderLayout.SOUTH);	
		frame.setBounds(300,250,500,400);
		frame.setVisible(true);	
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(type==1) //TYPE 1= PRINT LESSONS
		{
			frame.setSize(500, 400);
			frame.setTitle("LESSONS");
			
			Vector<String> vectorColumn=new Vector<String>();
			vectorColumn.add("LESSONS LISTED BELOW");
			Vector<Vector<String>> dataVector=new Vector<Vector<String>>();
			Vector<String> dataVector2=new Vector<String>();			
			Queue<Lesson> compLessonsTemp = lesson.getCompulsoryLessons();
			Queue<Lesson> electiveLessonsTemp = lesson.getElectiveLessons();
			String str ="";
			Lesson tempLesson = compLessonsTemp.peek();
			while(tempLesson!=null) 
			{
				dataVector2=new Vector<String>();
				str = tempLesson.getLessonName() + " " + tempLesson.getCredit() +" credit";
				dataVector2.add(str);
				dataVector.add(dataVector2);
				compLessonsTemp.remove();
				tempLesson= compLessonsTemp.peek();
			}
			tempLesson = electiveLessonsTemp.peek();			
			while(tempLesson!=null) 
			{
				dataVector2=new Vector<String>();
				str = tempLesson.getLessonName() + " " + tempLesson.getCredit() +" credit";
				dataVector2.add(str);
				dataVector.add(dataVector2);
				electiveLessonsTemp.remove();
				tempLesson= electiveLessonsTemp.peek();
			}
			tableModel.setDataVector(dataVector, vectorColumn);
			
		}
		else if(type==2)//TYPE 2= STUDENT'S INDIVIDUAL LESSONS
		{
			try {
				file=filePath;
				frame.setSize(1000, 400);
				frame.setTitle("YOUR LESSONS LISTED BELOW");
				if(file.equalsIgnoreCase(lessons_txt_path)) // for student to list program
				{
					fileReader=new FileReader(file);				
					Vector<String> vectorColumn=new Vector<String>();					
					vectorColumn.add("student ID");vectorColumn.add("Name");vectorColumn.add("Surname");vectorColumn.add("Lesson 1 / credits");vectorColumn.add("Lesson 2 / credits");vectorColumn.add("Lesson 3 / credits");
					vectorColumn.add("Lesson 4 / credits"); vectorColumn.add("Lesson 5 / credits"); vectorColumn.add("Lesson 6 / credits"); vectorColumn.add("Lesson 7 / credits");
					Vector<Vector<String>> dataVector=new Vector<Vector<String>>();
					Vector<String> dataVector2=new Vector<String>();
					
					dataVector2.add(student.getId());					
					dataVector2.add(student.getFirstName());					
					dataVector2.add(student.getLastName());
					
					Queue<Lesson> temp = compulsoryLessons.getCompulsoryLessons();
					while(temp.peek()!=null) {
						Lesson tempLesson = temp.peek();
						dataVector2.add(tempLesson.getLessonName() + " " + tempLesson.getCredit());
						temp.remove();
					}					
					Lesson tempLessonElective1 = student.getElectiveLesson1();								
					Lesson tempLessonElective2 = student.getElectiveLesson2();					
					if(tempLessonElective1!=null &&tempLessonElective2!=null) {
						dataVector2.add(tempLessonElective1.getLessonName() + " " + tempLessonElective1.getCredit());	
						dataVector2.add(tempLessonElective2.getLessonName() + " " + tempLessonElective2.getCredit());
					}
					else {
						dataVector2.add("Not Chosen yet!");	
						dataVector2.add("Not Chosen yet!");
					}
					
					dataVector.add(dataVector2);					
					tableModel.setDataVector(dataVector, vectorColumn);
					
				}
				
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//-----------------------
		else if(type==3) {//TYPE 3= PRINT ALL STUDENTS
			frame.setSize(500, 400);
			frame.setTitle("STUDENTS LISTED BELOW");
			try {
				file=filePath;
				fileReader=new FileReader(file);				
				Vector<Vector<String>> dataVector=new Vector<Vector<String>>();
				Vector<String> dataVector2=new Vector<String>();
				Vector<String> vectorColumn=new Vector<String>();
				vectorColumn.add("student ID");vectorColumn.add("Section");vectorColumn.add("Name");vectorColumn.add("Surname");vectorColumn.add("Gender");vectorColumn.add("Phone Number");vectorColumn.add("Birthdate");
				vectorColumn.add("Email"); 
			
				Main main= new Main();
				HashMap<String,Student>hm_students= main.get_hmStudents();
				//READ FILE TO TEACHER HASHMAP
				main.readfromFileToHm(student_txt_path,hm_students);
				
				//CONSTRUCT TABLE FROM THE STUDENT HASHMAP			
				for(Map.Entry<String,Student> entry : hm_students.entrySet()) 
				{
					Student student= entry.getValue();					
					dataVector2=new Vector<String>();					
					dataVector2.add(student.getId());
					dataVector2.add(student.getSection());
					dataVector2.add(student.getFirstName());				
					dataVector2.add(student.getLastName());		
					dataVector2.add(student.getGender());
					dataVector2.add(student.getPhoneNum());
					dataVector2.add(student.getBirthdate());
					dataVector2.add(student.getEmail());				
					dataVector.add(dataVector2);									
				}			
				tableModel.setDataVector(dataVector, vectorColumn);							
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		//-----------------------
		else if(type==4) { //TYPE 3= PRINT ALL TEACHERS
			frame.setSize(500, 400);
			frame.setTitle("TEACHERS LISTED BELOW");
			try {
				file=filePath;
				fileReader=new FileReader(file);				
				Vector<Vector<String>> dataVector=new Vector<Vector<String>>();
				Vector<String> dataVector2=new Vector<String>();
				Vector<String> vectorColumn=new Vector<String>();
				vectorColumn.add("Teacher Name");vectorColumn.add("Teacher Surname");vectorColumn.add("Lesson Name");
				
				Main main= new Main();
				HashMap<String, Teacher>hm_teachers= main.get_hmTeachers();
				//READ FILE TO TEACHER HASHMAP
				main.readfromFileToTeacherHm(teacher_txt_path,hm_teachers);
				
				//CONSTRUCT TABLE FROM THE TEACHER HASHMAP
				for(Entry<String, Teacher> entry : hm_teachers.entrySet()) 
				{					
					Teacher teacher= entry.getValue();					
					dataVector2=new Vector<String>();
					dataVector2.add(teacher.getFirstName());				
					dataVector2.add(teacher.getLastName());							
					dataVector2.add(teacher.getLesson().getLessonName());				
					dataVector.add(dataVector2);
									
				}			
				tableModel.setDataVector(dataVector, vectorColumn);						
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//-----------------------
			
	}

}
