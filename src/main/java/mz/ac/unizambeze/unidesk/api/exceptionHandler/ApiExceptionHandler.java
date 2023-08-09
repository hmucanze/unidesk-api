package mz.ac.unizambeze.unidesk.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mz.ac.unizambeze.unidesk.api.exceptionHandler.Problem.ProblemBuilder;
import mz.ac.unizambeze.unidesk.domain.exception.EntidadeEmUsoException;
import mz.ac.unizambeze.unidesk.domain.exception.EntidadeInexistenteException;
import mz.ac.unizambeze.unidesk.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_GENERIC_FINAL_USER_ERROR = "Ocorreu um erro inesperado no sistema. "
			+ "Tente novamente e se o problema persistir, entre em contacto " + "com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ EntidadeInexistenteException.class })
	public ResponseEntity<?> handleEntidadeInexistenteException(EntidadeInexistenteException e, WebRequest request) {

		ProblemType problemType = ProblemType.RECURSO_NAO_EXISTENTE;
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler({ EntidadeEmUsoException.class })
	public ResponseEntity<?> handleEntidadeEmUsoExcepption(EntidadeEmUsoException e, WebRequest request) {
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		HttpStatus status = HttpStatus.CONFLICT;
		String detail = e.getMessage();

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler({ NegocioException.class })
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
		ProblemType problemType = ProblemType.NEGOCIO_EXCEPTION;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão incorrectos. Faça o preenchimento correcto e tente novamente.";

		List<Problem.Object> problemObjects = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			return Problem.Object.builder().name(name).userMessage(message).build();
		}).collect(Collectors.toList());

		Problem problem = createProblemBuilder(status, problemType, detail).objects(problemObjects).userMessage(detail)
				.build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido, verifique erro de sintaxe";
		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	
	 @Override
	 protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, 
			 HttpStatus status, WebRequest request) {
	  
		 if(ex instanceof MethodArgumentTypeMismatchException){ 
			 return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
		 }
	  
		 return super.handleTypeMismatch(ex, headers, status, request);
	 }
	 

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder().status(status.value()).timestamp(OffsetDateTime.now())
					.title(status.getReasonPhrase()).userMessage(MSG_GENERIC_FINAL_USER_ERROR).build();
		} else if (body instanceof String) {
			body = Problem.builder().status(status.value()).timestamp(OffsetDateTime.now())
					.title(status.getReasonPhrase()).userMessage(MSG_GENERIC_FINAL_USER_ERROR).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format(
				"O parâmetro de URL '%s' recebeu o valor '%s' "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'",
				e.getName(), e.getValue(), e.getRequiredType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(e, problem, headers, status, request);
	}

	private ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder().status(status.value()).timestamp(OffsetDateTime.now()).title(problemType.getTitle())
				.type(problemType.getUri()).detail(detail);
	}

}
