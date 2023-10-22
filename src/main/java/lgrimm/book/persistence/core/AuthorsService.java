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
		String message;
		if (authorPayload == null) {
			return new AuthorPayload(
					null,
					null,
					null,
					null,
					"No author was defined.");
		}
		if (authorPayload.getId() != null) {
			return new AuthorPayload(
					authorPayload.getId(),
					authorPayload.getFamilyName(),
					authorPayload.getGivenName(),
					authorPayload.getBookIds(),
					authorPayload.getSeriesIds(),
					"Author ID was defined.");
		}
		if (authorPayload.getFamilyName() == null || authorPayload.getFamilyName().isBlank()) {
			return new AuthorPayload(
					authorPayload.getId(),
					authorPayload.getFamilyName(),
					authorPayload.getGivenName(),
					authorPayload.getBookIds(),
					authorPayload.getSeriesIds(),
					"Author family name was not defined.");
		}
		AuthorEntity authorEntity = converters.AuthorPayloadToEntity(authorPayload);
		if (authorEntity == null) {
			return new AuthorPayload(
					authorPayload.getId(),
					authorPayload.getFamilyName(),
					authorPayload.getGivenName(),
					authorPayload.getBookIds(),
					authorPayload.getSeriesIds(),
					"Author contained wrong data.");
		}
		return converters.AuthorEntityToPayload(authorsRepository.save(authorEntity), "");
	}
}
