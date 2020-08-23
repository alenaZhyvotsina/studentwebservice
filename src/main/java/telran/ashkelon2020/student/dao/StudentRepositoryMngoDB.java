package telran.ashkelon2020.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.PagingAndSortingRepository;

import telran.ashkelon2020.student.model.Student;

public interface StudentRepositoryMngoDB extends PagingAndSortingRepository<Student, Integer> {
	
	Stream<Student> findByName(String name);   // it is equals find({name: "name"})
	
	Stream<Student> findByNameAndIdGreaterThan(String name, int minId); 

}
