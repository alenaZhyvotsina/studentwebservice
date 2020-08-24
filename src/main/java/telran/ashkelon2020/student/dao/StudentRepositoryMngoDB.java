package telran.ashkelon2020.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import telran.ashkelon2020.student.model.Student;

//public interface StudentRepositoryMngoDB extends PagingAndSortingRepository<Student, Integer> {
public interface StudentRepositoryMngoDB extends MongoRepository<Student, Integer> {
	
	Stream<Student> findByName(String name);   // it is equals find({name: "name"})
	
	Stream<Student> findByNameAndIdGreaterThan(String name, int minId); 
	
	//Stream<Student> findByNameIn(List<String> names);
	long countByNameIn(List<String> names);
	
	Stream<Student> findBy();  // Stream of the all documents in db
	
	//@Query("{?0: {$gte: ?1}}")
	@Query("{'scores.?0': {$gte: ?1}}")
	Stream<Student> findByExamAndScoreGreaterThanEqual(String exam, int score); 
	

}
