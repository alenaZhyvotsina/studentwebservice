package telran.ashkelon2020.student.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Setter
public class StudentResponseDto {
	
	Integer id;
	String name;
	Map<String, Integer> scores;

}
