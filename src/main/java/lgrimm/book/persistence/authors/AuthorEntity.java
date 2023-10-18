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
	private Long id;
	@Column(name = "authors_family_name", nullable = false, columnDefinition = "TEXT")
	private String familyName;
	@Column(name = "authors_given_name", columnDefinition = "TEXT")
	private String givenName;
	@Column(name = "authors_books_ids", columnDefinition = "TEXT")
	private String bookIds;
	@Column(name = "authors_series_ids", columnDefinition = "TEXT")
	private String seriesIds;

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

	public AuthorEntity(Long id,
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorEntity that = (AuthorEntity) o;
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
		return "AuthorEntity{" +
				"id=" + id +
				", familyName='" + familyName + '\'' +
				", givenName='" + givenName + '\'' +
				", bookIds='" + bookIds + '\'' +
				", seriesIds='" + seriesIds + '\'' +
				'}';
	}
}
