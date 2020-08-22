package telran.ashkelon2020.student.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import telran.ashkelon2020.student.dao.StudentRepository;
import telran.ashkelon2020.student.dao.StudentRepositoryImpl;

@Configuration
public class StudentConfiguration {
	
	//@Bean			// analogue of @Component
	public StudentRepository getRepository() {
		return new StudentRepositoryImpl();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
