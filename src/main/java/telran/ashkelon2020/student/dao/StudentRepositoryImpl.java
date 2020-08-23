package telran.ashkelon2020.student.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Repository;

import telran.ashkelon2020.student.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	Map<Integer, Student> students = new ConcurrentHashMap<>();
	
	Lock lock = new ReentrantLock();
	
	@Override
	public boolean addStudent(Student student) {
		return students.putIfAbsent(student.getId(), student) == null;
	}

	@Override
	public Student findStudentById(int id) {
		try {
			lock.lock();
			return students.get(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Student deleteStudent(int id) {
		try {
			lock.lock();
			return students.remove(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Student updateStudent(int id, String name, String password) {
		try {
			lock.lock();
			
			Student student = students.get(id);
			/*
			if(student == null) {
				return null;
			}
			*/
			if (name != null && !name.isEmpty())
				student.setName(name);
			if (password != null && !password.isEmpty())
				student.setPassword(password);
			return student;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean addScore(int id, String exam, int score) {
		try {
			lock.lock();
			
			Student student = students.get(id);
			/*
			if(student == null) {
				return false;
			}
			*/
			return student.getScores().put(exam, score) == null;
		} finally {
			lock.unlock();
		}
	}

}
