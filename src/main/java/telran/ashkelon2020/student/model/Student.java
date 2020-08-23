package telran.ashkelon2020.student.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"id"})
//@NoArgsConstructor
@Document(collection = "students")  // этому классу соответствует документ в mongoDB, default name - student
public class Student {
	@Id   // if name is id this annotation is not necessary
	int id;
	@Setter
	String name;
	@Setter
	String password;
	Map<String, Integer> scores;
	
	public Student() {
		scores = new HashMap<>();
	}
	
	public Student(int id, String name, String password) {
		this();
		this.id = id;
		this.name = name;
		this.password = password;
		
		//scores = new HashMap<>();
	}
	
	public boolean addScore(String exam, Integer score) {
		return scores.put(exam, score) == null;  //true - если первый раз, false - пересдача  
	}

}
