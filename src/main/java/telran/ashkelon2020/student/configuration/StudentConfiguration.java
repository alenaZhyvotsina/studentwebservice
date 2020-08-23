package telran.ashkelon2020.student.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
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
		ModelMapper modelMapper = new ModelMapper();
		
		//чтобы это работало, должны быть установлены setter-ы в классах destination
		// or
		/*
		modelMapper.createTypeMap(Student.class, StudentResponseDto.class);
		modelMapper.validate();
		
		modelMapper.createTypeMap(Student.class, StudentDto.class);
		modelMapper.validate();
		*/
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setSkipNullEnabled(true)
			//.setFieldAccessLevel(AccessLevel.PROTECTED)
			.setFieldAccessLevel(AccessLevel.PRIVATE);
		
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return modelMapper;
	}

}
