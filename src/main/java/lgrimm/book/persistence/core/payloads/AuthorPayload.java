package lgrimm.book.persistence.core.payloads;

import lgrimm.book.persistence.authors.*;

import java.util.*;
import java.util.stream.*;

public class AuthorPayload {

	private final Long id;
	private final String familyName;
	private final String givenName;
	private final List<Long> bookIds;
	private final List<Long> seriesIds;

	public AuthorPayload(String familyName,
						 String givenName,
						 List<Long> bookIds,
						 List<Long> seriesIds) {
		this.id = null;
		this.familyName = familyName;
		this.givenName = givenName;
		this.bookIds = bookIds;
		this.seriesIds = seriesIds;
	}

	public AuthorPayload(Long id,
						 String familyName,
						 String givenName,
						 List<Long> bookIds,
						 List<Long> seriesIds) {
		this.id = id;
		this.familyName = familyName;
		this.givenName = givenName;
		this.bookIds = bookIds;
		this.seriesIds = seriesIds;
	}

	public Long getId() {
		return id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public List<Long> getBookIds() {
		return bookIds;
	}

	public List<Long> getSeriesIds() {
		return seriesIds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorPayload that = (AuthorPayload) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(familyName, that.familyName) &&
				Objects.equals(givenName, that.givenName) &&
				Objects.equals(bookIds, that.bookIds) &&
				Objects.equals(seriesIds, that.seriesIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, familyName, givenName, bookIds, seriesIds);
	}

	@Override
	public String toString() {
		return "AuthorPayload{" +
				"id=" + id +
				", familyName='" + familyName + '\'' +
				", givenName='" + givenName + '\'' +
				", bookIds=" + bookIds +
				", seriesIds=" + seriesIds +
				'}';
	}
}
