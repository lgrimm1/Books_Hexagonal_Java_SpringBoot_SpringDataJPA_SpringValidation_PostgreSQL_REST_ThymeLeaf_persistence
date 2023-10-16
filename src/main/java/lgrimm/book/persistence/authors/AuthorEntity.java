package lgrimm.book.persistence.authors;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.*;

@Entity
@Table(name = "authors")
public class AuthorEntity {

	@Id
	@SequenceGenerator(name = "authors_sequence", sequenceName = "authors_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_sequence")
	@Column(name = "authors_id")
	private long id;
	@Column(name = "authors_family_name", nullable = false, columnDefinition = "TEXT")
	private String familyName;
	@Column(name = "authors_given_name", columnDefinition = "TEXT")
	private String givenName;
	@Column(name = "authors_books_ids", columnDefinition = "TEXT")
	private String bookIds;
	@Column(name = "authors_series_ids", columnDefinition = "TEXT")
	private String seriesIds;

	@Transient
	private List<Long> bookIdList;
	@Transient
	private List<Long> seriesIdList;

	public AuthorEntity() {
	}

	public AuthorEntity(String familyName,
						String givenName,
						String bookIds,
						String seriesIds) {
		this.familyName = familyName;
		this.givenName = givenName;
		this.bookIds = bookIds;
		this.seriesIds = seriesIds;
	}

	public AuthorEntity(long id,
						String familyName,
						String givenName,
						String bookIds,
						String seriesIds) {
		this.id = id;
		this.familyName = familyName;
		this.givenName = givenName;
		this.bookIds = bookIds;
		this.seriesIds = seriesIds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getBookIds() {
		return bookIds;
	}

	public void setBookIds(String bookIds) {
		this.bookIds = bookIds;
	}

	public String getSeriesIds() {
		return seriesIds;
	}

	public void setSeriesIds(String seriesIds) {
		this.seriesIds = seriesIds;
	}

	public List<Long> getBookIdList() {
		if (bookIds == null) {
			return null;
		}
		if (bookIds.isBlank()) {
			return new ArrayList<>();
		}
		return Arrays.stream(bookIds.split(","))
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

	public void setBookIdList(List<Long> bookIdList) {
		if (bookIdList == null) {
			bookIds = null;
		}
		else if (bookIdList.isEmpty()) {
			bookIds = "";
		}
		else {
			bookIds = String.join(",", bookIdList.stream()
					.sorted()
					.map(String::valueOf)
					.toList());
		}
	}

	public List<Long> getSeriesIdList() {
		if (seriesIds == null) {
			return null;
		}
		if (seriesIds.isBlank()) {
			return new ArrayList<>();
		}
		return Arrays.stream(seriesIds.split(","))
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}

	public void setSeriesIdList(List<Long> seriesIdList) {
		if (seriesIdList == null) {
			seriesIds = null;
		}
		else if (seriesIdList.isEmpty()) {
			seriesIds = "";
		}
		else {
			seriesIds = String.join(",", seriesIdList.stream()
					.sorted()
					.map(String::valueOf)
					.toList());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorEntity that = (AuthorEntity) o;
		return id == that.id &&
				familyName.equals(that.familyName) &&
				Objects.equals(givenName, that.givenName) &&
				Objects.equals(bookIds, that.bookIds) &&
				Objects.equals(seriesIds, that.seriesIds) &&
				Objects.equals(bookIdList, that.bookIdList) &&
				Objects.equals(seriesIdList, that.seriesIdList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, familyName, givenName, bookIds, seriesIds, bookIdList, seriesIdList);
	}

	@Override
	public String toString() {
		return "AuthorEntity{" +
				"id=" + id +
				", familyName='" + familyName + '\'' +
				", givenName='" + givenName + '\'' +
				", bookIds='" + bookIds + '\'' +
				", seriesIds='" + seriesIds + '\'' +
				", bookIdList=" + bookIdList +
				", seriesIdList=" + seriesIdList +
				'}';
	}
}
