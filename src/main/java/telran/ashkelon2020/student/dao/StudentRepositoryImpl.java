package telran.ashkelon2020.student.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import telran.ashkelon2020.student.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	Map<Integer, Student> students = new ConcurrentHashMap<>();
	
	@Override
	public boolean addStudent(Student student) {
		return students.putIfAbsent(student.getId(), student) == null;
	}

	@Override
	public Student findStudentById(int id) {
		return students.get(id);
	}

	@Override
	public Student deleteStudent(int id) {
		return students.remove(id);
	}

	@Override
	public Student updateStudent(int id, String name, String password) {
		Student student = students.get(id);
		if(student == null) {
			return null;
		}
		if(name != null && !name.isEmpty()) student.setName(name);
		if(password != null && !password.isEmpty()) student.setPassword(password);
		return student;
	}

	@Override
	public boolean addScore(int id, String exam, int score) {
		Student student = students.get(id);
		/*
		if(student == null) {
			return false;
		}
		*/
		return student.getScores().put(exam, score) == null;
	}

}
