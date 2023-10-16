package lgrimm.book.persistence.authors;

import org.junit.jupiter.api.*;

import java.util.*;

class AuthorEntityTest {

	@Test
	void getBookIdList_NullIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				null,
				null);
		Assertions.assertNull(authorEntity.getBookIdList());
	}

	@Test
	void getBookIdList_EmptyIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				"",
				null);
		Assertions.assertEquals(0, authorEntity.getBookIdList().size());
	}

	@Test
	void getBookIdList_BlankIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				"  ",
				null);
		Assertions.assertEquals(0, authorEntity.getBookIdList().size());
	}

	@Test
	void getBookIdList_OneId() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				"2",
				null);
		List<Long> expectedList = List.of(2L);
		Assertions.assertEquals(expectedList, authorEntity.getBookIdList());
	}

	@Test
	void getBookIdList_MoreIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				"2,3",
				null);
		List<Long> expectedList = List.of(2L, 3L);
		Assertions.assertEquals(expectedList, authorEntity.getBookIdList());
	}

	@Test
	void setBookIdList_nullIdList() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setBookIdList(null);
		Assertions.assertNull(authorEntity.getBookIds());
	}

	@Test
	void setBookIdList_EmptyIdList() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setBookIdList(new ArrayList<>());
		Assertions.assertTrue(authorEntity.getBookIds().isEmpty());
	}

	@Test
	void setBookIdList_OneId() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setBookIdList(List.of(2L));
		Assertions.assertEquals("2", authorEntity.getBookIds());
	}

	@Test
	void setBookIdList_MoreIds() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setBookIdList(List.of(2L, 3L));
		Assertions.assertEquals("2,3", authorEntity.getBookIds());
	}

	@Test
	void getSeriesIdList_NullIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				null,
				null);
		Assertions.assertNull(authorEntity.getSeriesIdList());
	}

	@Test
	void getSeriesIdList_EmptyIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				null,
				"");
		Assertions.assertEquals(0, authorEntity.getSeriesIdList().size());
	}

	@Test
	void getSeriesIdList_BlankIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				null,
				"  ");
		Assertions.assertEquals(0, authorEntity.getSeriesIdList().size());
	}

	@Test
	void getSeriesIdList_OneId() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				null,
				"4");
		List<Long> expectedList = List.of(4L);
		Assertions.assertEquals(expectedList, authorEntity.getSeriesIdList());
	}

	@Test
	void getSeriesIdList_MoreIds() {
		AuthorEntity authorEntity = new AuthorEntity(
				null,
				null,
				null,
				"4,5");
		List<Long> expectedList = List.of(4L, 5L);
		Assertions.assertEquals(expectedList, authorEntity.getSeriesIdList());
	}

	@Test
	void setSeriesIdList_nullIdList() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setSeriesIdList(null);
		Assertions.assertNull(authorEntity.getSeriesIds());
	}

	@Test
	void setSeriesIdList_EmptyIdList() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setSeriesIdList(new ArrayList<>());
		Assertions.assertTrue(authorEntity.getSeriesIds().isEmpty());
	}

	@Test
	void setSeriesIdList_OneId() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setSeriesIdList(List.of(4L));
		Assertions.assertEquals("4", authorEntity.getSeriesIds());
	}

	@Test
	void setSeriesIdList_MoreIds() {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setSeriesIdList(List.of(4L, 5L));
		Assertions.assertEquals("4,5", authorEntity.getSeriesIds());
	}
}