package demo;
import java.lang.reflect.Field;

public class StudentArray {

	public static void main(String[] args)  {
try {
		Student[] students = new Student[3];

		students[0] = new Student(101, "Jagruti");
		students[1] = new Student(102, "Rinki");
		students[2] = new Student(103, "Sahara");

		Class c = Student.class;

		Field field = c.getDeclaredField("name");

		for (Student s : students) {
			System.out.println(field.get(s));
		}
	
}
catch (Exception e) {

	e.printStackTrace();
}

	}
}