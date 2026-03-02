package hbp.encap;

public class Student {

	private int rollNumber;
	private String name;
	private String courseTaken;

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseTaken() {
		return courseTaken;
	}

	public void setCourseTaken(String courseTaken) {
		this.courseTaken = courseTaken;
	}

//public abstract class CourseActivity() {
	void gradStu() {
		System.out.println("grad student, give seminar");
	}

	void underGradStu() {
		System.out.println(": UG student, submit project");
	}
}
