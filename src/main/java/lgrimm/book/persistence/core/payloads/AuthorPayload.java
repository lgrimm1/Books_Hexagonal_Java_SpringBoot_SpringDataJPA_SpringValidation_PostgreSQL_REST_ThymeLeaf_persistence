package lgrimm.book.persistence.core.payloads;

import java.util.*;

public class AuthorPayload {

	private Long id;
	private String familyName;
	private String givenName;
	private List<Long> bookIds;
	private List<Long> seriesIds;
	private String message;

	public AuthorPayload() {
	}

	public AuthorPayload(String familyName,
						 String givenName,
						 List<Long> bookIds,
						 List<Long> seriesIds,
						 String message) {
		this.id = null;
		this.familyName = familyName;
		this.givenName = givenName;
		this.bookIds = bookIds;
		this.seriesIds = seriesIds;
		this.message = message;
	}

	public AuthorPayload(Long id,
						 String familyName,
						 String givenName,
						 List<Long> bookIds,
						 List<Long> seriesIds,
						 String message) {
		this.id = id;
		this.familyName = familyName;
		this.givenName = givenName;
		this.bookIds = bookIds;
		this.seriesIds = seriesIds;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}

	public List<Long> getSeriesIds() {
		return seriesIds;
	}

	public void setSeriesIds(List<Long> seriesIds) {
		this.seriesIds = seriesIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
				Objects.equals(seriesIds, that.seriesIds) &&
				Objects.equals(message, that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, familyName, givenName, bookIds, seriesIds, message);
	}

	@Override
	public String toString() {
		return "AuthorPayload{" +
				"id=" + id +
				", familyName='" + familyName + '\'' +
				", givenName='" + givenName + '\'' +
				", bookIds=" + bookIds +
				", seriesIds=" + seriesIds +
				", message='" + message + '\'' +
				'}';
	}
}
