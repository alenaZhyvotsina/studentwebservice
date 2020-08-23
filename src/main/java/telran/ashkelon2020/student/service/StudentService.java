package telran.ashkelon2020.student.service;

import java.util.List;

import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentResponseDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;

public interface StudentService {
	boolean addStudent(StudentDto studentDto);
	
	StudentResponseDto findStudent(int id);
	
	StudentResponseDto deleteStudent(int id);
	
	StudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto);
	
	boolean addScore(int id, ScoreDto scoreDto);
	
	List<StudentResponseDto> findStudentsByName(String name);
	
	List<StudentResponseDto> findStudentsByNameAndIdGreatThan(String name, int minId);

}
