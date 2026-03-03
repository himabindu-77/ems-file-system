package hbp.encap;

public class TestOld {

	public static void main(String[] args) {

		Student sd1 =new Student();
		sd1.setName("bindu");
		sd1.setCourseTaken("Grad");
		sd1.setRollNumber(90);
		sd1.gradStu();
		
		Student sd2= new Student();
		sd2.setCourseTaken("undergrad");
		sd2.setName("kush");
		sd2.setRollNumber(222);
		sd2.underGradStu();
		
		Student sd3=new Student();
		sd3.setName("rohan");
		sd3.setCourseTaken("Grad");
		
		sd3.setRollNumber(91);
		sd3.gradStu();
	
		
	}

}

