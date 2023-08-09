package mz.ac.unizambeze.unidesk.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {
	
	private Integer status;
	private OffsetDateTime timestamp;
	private String title;
	private String type;
	private String detail;
	private String userMessage;
	private List<Object> objects;
	
	@Builder
	@Getter
	public static class Object {
		private String name;
		private String userMessage;
	}

}
