import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class FileOperation  
{
	public static String PATH; //ATTRIBUTE

	public FileOperation(String PATH) throws FileNotFoundException //CONSTRUCTOR
	{
		this.PATH=PATH;
	}
	
	public static void writetoStudentFile(String path ,Student student,int type)  //GIVEN STUDENT RECORDED TO GIVEN STUDENT FILE
	{
		//STUDENT ATTRIBUTES AND TYPES
		//integer rollNum, String id ,String section , String firstName, String lastName, String gender, String phoneNum,String birthday, String email
		try {

			File file = new File(path);
			//ADD TO FILE IF NOT CONTAINED
		    boolean isContaining =isContainsStudent(path,student); //STUDENT CHECKED,IF EXISTS IN THE FILE "FALSE" RETURNED
		    
		    //TYPE= 0 DEFAULT, IF THERE IS NO STUDENT ENROLLED & FILE EMTPY, WRITE DEFAULT STUDENTS TO FILE
			if(type==0 && !isContaining) // IF STUDENT DOES NOT EXIST ALREADY IN THE FILE,THEN ADD
			{ 	
				FileWriter fr = new FileWriter(file, true);	
				BufferedWriter br = new BufferedWriter(fr);
				br.write(student.getId() +","+ student.getSection()+"," +student.getFirstName()+","+student.getLastName()+
							","+student.getGender()+","+student.getPhoneNum()+","+student.getBirthdate() + ","+student.getEmail()+"\n");				
			    br.close();
				fr.close();
				
			}
			 //TYPE= 1 , IF THERE ARE STUDENTS DEFAULT OR ENROLLED FILE IS NOT EMPTY, THEN APPEND & UPDATE STUDENT FILE
			if(type==1) 
			{				
				FileWriter fr2 = new FileWriter(file, true);			
				BufferedWriter br2 = new BufferedWriter(fr2);
			    br2.append(student.getId() +","+ student.getSection()+"," +student.getFirstName()+","+student.getLastName()+
						","+student.getGender()+","+student.getPhoneNum()+","+student.getBirthdate() + ","+student.getEmail());
			    br2.append("\n");
			    br2.close();
				fr2.close();
			}

			
		    } catch (IOException e) {
		      System.err.println("Error occurred while writing on the file");
		      e.printStackTrace();
		    }
		
	}
	
	public static void writetoTeacherFile(String path ,Teacher teacher,int type) //GIVEN TEACHER RECORDED TO GIVEN TEACHER FILE
	{
		try {

			File file = new File(path);
		    boolean isContaining =isContainsTeacher(path,teacher);
		    //ADD TO FILE IF NOT CONTAINED
		    //TYPE= 0 DEFAULT, IF THERE IS NO TEACHER ENROLLED & FILE EMTPY, WRITE DEFAULT TEACHERS TO FILE
			if(type==0 && !isContaining) // IF TEACHER DOES NOT EXIST ALREADY IN THE FILE,THEN ADD
			{ 				
				FileWriter fr = new FileWriter(file, true);	
				BufferedWriter br = new BufferedWriter(fr);
				br.write(teacher.getFirstName()+","+teacher.getLastName()+","+teacher.getLesson().getLessonName()+"\n");				
			    br.close();
				fr.close();				
			}
			 //TYPE= 1 , IF THERE ARE TEACHERS DEFAULT OR ENROLLED FILE IS NOT EMPTY, THEN APPEND & UPDATE TEACHER FILE
			if(type==1) 
			{				
				FileWriter fr2 = new FileWriter(file, true);			
				BufferedWriter br2 = new BufferedWriter(fr2);
			    br2.append(teacher.getFirstName()+","+teacher.getLastName()+","+teacher.getLesson().getLessonName()+"\n");				
			    br2.close();
				fr2.close();
			}

			
		    } catch (IOException e) {
		      System.err.println("Error occurred while writing on the file");
		      e.printStackTrace();
		    }
	}
	
	public static void reWritetoStudentFileAfterDelete(String path ,HashMap<String,Student> hmStudents) //BASED ON DELETION UPDATED STUDENT HASHMAP,UPDATE FILE
	{
		try {

			File file = new File(path);
			FileWriter fr = new FileWriter(file);	
			BufferedWriter br = new BufferedWriter(fr);
			//REWRITE FILE BASED ON UPDATED HASHMAP
			for(Map.Entry<String,Student> entry : hmStudents.entrySet()) {
				Student student= entry.getValue();
				br.write(student.getId() +","+ student.getSection()+"," +student.getFirstName()+","+student.getLastName()+
							","+student.getGender()+","+student.getPhoneNum()+","+student.getBirthdate() + ","+student.getEmail()+"\n");							   
			}			
			 br.close();
			 fr.close();
		
		    } catch (IOException e) {
		      System.err.println("Error occurred while writing on the file");
		      e.printStackTrace();
		    }
	}
		
	public static void reWritetoTeacherFileAfterDelete(String path ,HashMap<String,Teacher> hmTeachers) //BASED ON DELETION UPDATED TEACHER HASHMAP,UPDATE FILE
	{
		
		try {

			File file = new File(path);
			FileWriter fr = new FileWriter(file);	
			BufferedWriter br = new BufferedWriter(fr);
			//REWRITE FILE BASED ON UPDATED HASHMAP
			for(Entry<String, Teacher> entry : hmTeachers.entrySet()) {
				Teacher teacher= entry.getValue();
				br.write(teacher.getFirstName()+","+teacher.getLastName()+","+teacher.getLesson().getLessonName()+"\n");						   
			}			
			 br.close();
			 fr.close();
		
		    } catch (IOException e) {
		      System.err.println("Error occurred while writing on the file");
		      e.printStackTrace();
		    }

	}
	public static void writetoLessonFile(String path ,Queue<Lesson> lessons,Student student) //GIVEN LESSON RECORDED TO GIVEN LESSON FILE
	{
		try {
					File file = new File(path);				
					FileWriter fr = new FileWriter(file, true);
					BufferedWriter br = new BufferedWriter(fr);
					//LESSON QUEUE
					Queue<Lesson> temp = lessons;
					int size = temp.size();
					String text ="";
					int count=1;					
					while (temp.peek()!=null)
					{			
						text+= "," + temp.peek().getLessonName() +" " + temp.peek().getCredit() + " credits ";
						//DEFAULT ELECTIVE LESSONS RECORDED TO THE FILE
						if(count==6 && temp.peek().getElectiveLesson1_day()!=null ) {
							text+= "," + temp.peek().getElectiveLesson1_day();
						}
						if(count==7 && temp.peek().getElectiveLesson2_day()!=null ) {
							text+= "," + temp.peek().getElectiveLesson2_day();
						}
						
						temp.remove();
						count++;
					}
					text += "\n";
						br.write( student.getId() +"," + student.getFirstName()+","+student.getLastName()+ text);
					br.close();
					fr.close();				
		    } catch (IOException e) {
		      System.err.println("Error occurred while writing on the file" );
		      e.printStackTrace();
		    }
	}
	
	//STUDENT CONTAINING CHECK FROM THE FILE HELPER FUNCTION
	public static boolean isContainsStudent (String path,Student student) throws FileNotFoundException {
		 File file = new File(path);
		 Scanner scn = new Scanner(file);
	     while(scn.hasNext()) {
	    	 String data= scn.nextLine();
	    	 String[] line= data.split(",");
	    	 if(line[0].equalsIgnoreCase(student.getId())) { //CHECK IF THE FILE LINE[0] AND STUDENT ID MATCHES
	    		 scn.close();
	    		return true;
	    	 }
	     }
	     scn.close();
		return false;
	}
	
	//TEACHER CONTAINING CHECK FROM THE FILE HELPER FUNCTION
	public static boolean isContainsTeacher (String path,Teacher teacher) throws FileNotFoundException {
		 File file = new File(path);
		 Scanner scn = new Scanner(file);
	     while(scn.hasNext()) {
	    	 String data= scn.nextLine();
	    	 String[] line= data.split(",");
	    	 if(line[0].equalsIgnoreCase(teacher.getFirstName())) {//CHECK IF THE FILE LINE[0] AND TEACHER NAME MATCHES
	    		 scn.close();
	    		return true;
	    	 }
	     }
	     scn.close();
		return false;
	}
}
