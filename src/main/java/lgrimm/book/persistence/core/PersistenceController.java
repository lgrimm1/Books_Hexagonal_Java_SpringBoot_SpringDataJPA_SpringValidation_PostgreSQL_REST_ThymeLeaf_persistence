package lgrimm.book.persistence.core;

import lgrimm.book.persistence.core.payloads.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PersistenceController {

	private final AuthorsService authorsService;

	@Autowired
	public PersistenceController(AuthorsService authorsService) {
		this.authorsService = authorsService;
	}

	@PostMapping(path = "/author")
	public AuthorPayload createAuthor(@RequestBody AuthorPayload authorPayload) {
		return authorsService.createAuthor(authorPayload);
	}

/*
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<AuthorPayload> handleException(RuntimeException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(400));
	}
*/
}
