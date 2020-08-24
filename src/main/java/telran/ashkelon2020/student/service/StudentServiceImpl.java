package telran.ashkelon2020.student.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.sym.Name;

import telran.ashkelon2020.student.dao.StudentRepository;
import telran.ashkelon2020.student.dao.StudentRepositoryMngoDB;
import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentResponseDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;
import telran.ashkelon2020.student.dto.exceptions.StudentNotFoundException;
import telran.ashkelon2020.student.model.Student;

@Component   // для добавления в контекст
public class StudentServiceImpl implements StudentService {

	//@Autowired
	//StudentRepository studentRepository;
	
	@Autowired
	StudentRepositoryMngoDB studentRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean addStudent(StudentDto studentDto) {
		if(studentDto == null) return false;
		//Student student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
		Student student = modelMapper.map(studentDto, Student.class);
		
		if(studentRepository.existsById(student.getId())) {
			return false;
		}		
		//return studentRepository.addStudent(student);
		
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentResponseDto findStudent(int id) {		
			//Student student = studentRepository.findStudentById(id);
			Student student = 
					studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
			/*
			if(student == null) {
				throw new StudentNotFoundException(id);
			}
			*/
						
			return modelMapper.map(student, StudentResponseDto.class);
						
			/*
			return StudentResponseDto.builder().
					id(id)
					.name(student.getName())
					.scores(student.getScores())
					.build();
			*/
		
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		//try {
			//Student student = studentRepository.deleteStudent(id);
			Student student = 
					studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
			
			studentRepository.deleteById(id);
			
			return modelMapper.map(student, StudentResponseDto.class);
						
			/*
			return StudentResponseDto.builder()
					.id(id)
					.name(student.getName())
					.scores(student.getScores())
					.build();
					
			*/
		/*
		} catch (NullPointerException e) {
			throw new StudentNotFoundException(id);
		}
		*/
	}

	@Override
	public StudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {		
		//try {
			//Student student = studentRepository.updateStudent(id, studentUpdateDto.getName(), studentUpdateDto.getPassword());
			
			Student student = 
					studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
			
			String name = studentUpdateDto.getName();
			if(name == null) {
				name = student.getName();
			}
			String password = studentUpdateDto.getPassword();
			if(password == null) {
				password = student.getPassword();
			}
			
			student.setName(name);
			student.setPassword(password);
			
			studentRepository.save(student);
			
			return modelMapper.map(student, StudentDto.class);
						
			/*
			return StudentDto.builder()
					.id(id)
					.name(student.getName())
					.password(student.getPassword())
					.build();					
			*/
		/*
		} catch (NullPointerException e) {
			throw new StudentNotFoundException(id);
		}
		*/
	}

	@Override
	public boolean addScore(int id, ScoreDto scoreDto) {
		/*
		try {
			return studentRepository.addScore(id, scoreDto.getExamName(), scoreDto.getScore());
		} catch (NullPointerException e) {
			throw new StudentNotFoundException(id);
		}	
		*/
		Student student = 
				studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
		
	}

	@Override
	public List<StudentResponseDto> findStudentsByName(String name) {
		/*
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.filter(s -> name.equalsIgnoreCase(s.getName()))
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());		
		*/
		return studentRepository.findByName(name)
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());			
	}
	
	@Override
	public List<StudentResponseDto> findStudentsByNameAndIdGreatThan(String name, int minId) {
		/*
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.filter(s -> name.equalsIgnoreCase(s.getName()))
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());		
		*/
		return studentRepository.findByNameAndIdGreaterThan(name, minId)
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());			
	}

	@Override
	public long studentsQuantity(List<String> names) {
		//return studentRepository.findByNameIn(names).count();
		return studentRepository.countByNameIn(names);
	}

	@Override
	public List<StudentResponseDto> findStudentsByExamScore(String exam, int score) {
		/*
		return studentRepository.findBy()
				.filter(s -> s.getScores().containsKey(exam))
				.filter(s -> s.getScores().get(exam) >= score)
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());
		*/
		
		return studentRepository.findByExamAndScoreGreaterThanEqual(exam, score)
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());
	}

}
