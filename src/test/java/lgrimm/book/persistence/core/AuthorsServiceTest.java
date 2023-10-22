package lgrimm.book.persistence.core;

import lgrimm.book.persistence.authors.*;
import lgrimm.book.persistence.core.payloads.*;
import org.junit.jupiter.api.*;

import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

class AuthorsServiceTest {

	AuthorsRepository authorsRepository;
	Converters converters;
	AuthorsService service;
	AuthorPayload authorPayload, expectedAuthorPayload;

	@BeforeEach
	void setUp() {
		authorsRepository = Mockito.mock(AuthorsRepository.class);
		converters = Mockito.mock(Converters.class);
		//setting up common mocked behaviours
		//e.g. in case the Element is a class declared elsewhere, and
		//repositoryName.getElement(int id) would return an Element with the passed ID, and
		//the service get() method calls repositoryName.getElement(...), then mocking:
		//when(repositoryName.getElement(12)).thenReturn(new Element(12, "abc", 3.14d));
		service = new AuthorsService(authorsRepository, converters);
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(service);
	}

	@Test
	void createAuthor_NullAuthorPayload() {
/*
		Exception e = Assertions.assertThrows(RuntimeException.class, () -> service.createAuthor(null));
		Assertions.assertEquals("No author was defined.", e.getMessage());
*/
		expectedAuthorPayload = new AuthorPayload(
				null,
				null,
				null,
				null,
				null,
				"No author was defined.");
		Assertions.assertEquals(expectedAuthorPayload, service.createAuthor(authorPayload));
	}

	@Test
	void createAuthor_ExistingId() {
/*
		AuthorPayload payload = new AuthorPayload(
				2L,
				"fn",
				"gn",
				List.of(3L, 4L),
				List.of(5L, 6L),
				message);
		Exception e = Assertions.assertThrows(RuntimeException.class, () -> service.createAuthor(payload));
		Assertions.assertEquals("Author ID was defined.", e.getMessage());
*/
		authorPayload = new AuthorPayload(
				2L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");
		expectedAuthorPayload = new AuthorPayload(
				2L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"Author ID was defined.");
		Assertions.assertEquals(expectedAuthorPayload, service.createAuthor(authorPayload));
	}

	@Test
	void createAuthor_NullFamilyName() {
		authorPayload = new AuthorPayload(
				null,
				null,
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");
		expectedAuthorPayload = new AuthorPayload(
				null,
				null,
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"Author family name was not defined.");
/*
		Exception e = Assertions.assertThrows(RuntimeException.class, () -> service.createAuthor(payload));
		Assertions.assertEquals("Author family name was not defined.", e.getMessage());
*/
		Assertions.assertEquals(expectedAuthorPayload, service.createAuthor(authorPayload));
	}

	@Test
	void createAuthor_BlankFamilyName() {
		authorPayload = new AuthorPayload(
				null,
				"  ",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");
		expectedAuthorPayload = new AuthorPayload(
				null,
				"  ",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"Author family name was not defined.");
/*
		Exception e = Assertions.assertThrows(RuntimeException.class, () -> service.createAuthor(payload));
		Assertions.assertEquals("Author family name was not defined.", e.getMessage());
*/
		Assertions.assertEquals(expectedAuthorPayload, service.createAuthor(authorPayload));
	}

	@Test
	void createAuthor_WrongData() {
		authorPayload = new AuthorPayload(
				null,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");
		expectedAuthorPayload = new AuthorPayload(
				null,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"Author contained wrong data.");
		when(converters.AuthorPayloadToEntity(authorPayload))
				.thenReturn(null);
/*
		Exception e = Assertions.assertThrows(RuntimeException.class, () -> service.createAuthor(payload));
		Assertions.assertEquals("Author contained wrong data.", e.getMessage());
*/
		Assertions.assertEquals(expectedAuthorPayload, service.createAuthor(authorPayload));
	}

	@Test
	void createAuthor_RightData() {
		authorPayload = new AuthorPayload(
				null,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");
		AuthorEntity entity = new AuthorEntity(
				null,
				"fn",
				"gn",
				"3,4",
				"5,6"
		);
		when(converters.AuthorPayloadToEntity(authorPayload))
				.thenReturn(entity);

		AuthorEntity savedEntity = new AuthorEntity(
				2L,
				"fn",
				"gn",
				"3,4",
				"5,6"
		);
		when(authorsRepository.save(entity))
				.thenReturn(savedEntity);

		expectedAuthorPayload = new AuthorPayload(
				2L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");
		when(converters.AuthorEntityToPayload(savedEntity, ""))
				.thenReturn(expectedAuthorPayload);
		Assertions.assertEquals(expectedAuthorPayload, service.createAuthor(authorPayload));
	}
}