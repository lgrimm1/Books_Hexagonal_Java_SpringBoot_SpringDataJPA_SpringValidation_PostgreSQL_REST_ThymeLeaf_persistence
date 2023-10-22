package lgrimm.book.persistence.core;

import lgrimm.book.persistence.authors.*;
import lgrimm.book.persistence.core.payloads.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Component
public class Converters {

	private String idListToString(List<Long> idList) {
		if (idList == null) {
			return null;
		}
		if (idList.isEmpty()) {
			return "";
		}
		return String.join(
				",",
				idList.stream()
						.sorted()
						.map(String::valueOf)
						.toList()
		);
	}

	private List<Long> IdStringToList(String idString) {
		if (idString == null) {
			return null;
		}
		if (idString.isEmpty()) {
			return new ArrayList<>();
		}
		return Arrays.stream(idString.split(","))
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

	public AuthorEntity AuthorPayloadToEntity(AuthorPayload authorPayload) {
		if (authorPayload == null) {
			return null;
		}
		return new AuthorEntity(
				authorPayload.getId(),
				authorPayload.getFamilyName(),
				authorPayload.getGivenName(),
				idListToString(authorPayload.getBookIds()),
				idListToString(authorPayload.getSeriesIds()));
	}

	public AuthorPayload AuthorEntityToPayload(AuthorEntity authorEntity, String message) {
		if (authorEntity == null) {
			return null;
		}
		if (message == null || message.isBlank()) {
			message = "";
		}
		return new AuthorPayload(
				authorEntity.getId(),
				authorEntity.getFamilyName(),
				authorEntity.getGivenName(),
				IdStringToList(authorEntity.getBookIds()),
				IdStringToList(authorEntity.getSeriesIds()),
				message);
	}
}
