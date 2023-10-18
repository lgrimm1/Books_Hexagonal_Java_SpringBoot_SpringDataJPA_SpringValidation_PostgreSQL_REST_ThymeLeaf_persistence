package lgrimm.book.persistence.core;

import lgrimm.book.persistence.authors.*;
import lgrimm.book.persistence.core.payloads.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AuthorsService {

	private final AuthorsRepository authorsRepository;
	private final Converters converters;

	@Autowired
	public AuthorsService(AuthorsRepository authorsRepository, Converters converters) {
		this.authorsRepository = authorsRepository;
		this.converters = converters;
	}

	public AuthorPayload createAuthor(AuthorPayload authorPayload) {
		if (authorPayload == null) {
			throw new RuntimeException("No author was defined.");
		}
		if (authorPayload.getId() != null) {
			throw new RuntimeException("Author ID was defined.");
		}
		if (authorPayload.getFamilyName() == null || authorPayload.getFamilyName().isBlank()) {
			throw new RuntimeException("Author family name was not defined.");
		}
		AuthorEntity authorEntity = converters.AuthorPayloadToEntity(authorPayload);
		if (authorEntity == null) {
			throw new RuntimeException("Author contained wrong data.");
		}
		return converters.AuthorEntityToPayload(authorsRepository.save(authorEntity));
	}
}
