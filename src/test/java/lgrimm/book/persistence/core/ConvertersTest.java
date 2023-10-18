package lgrimm.book.persistence.core;

import lgrimm.book.persistence.authors.*;
import lgrimm.book.persistence.core.payloads.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ConvertersTest {

	Converters converters;

	@BeforeEach
	void setUp() {
		converters = new Converters();
	}

	@Test
	void authorPayloadToEntity_NullPayload() {
		Assertions.assertNull(converters.AuthorPayloadToEntity(null));
	}

	@Test
	void authorPayloadToEntity_FullPayload() {
		AuthorPayload authorPayload = new AuthorPayload(
				11L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L)
		);
		AuthorEntity expectedAuthorEntity = new AuthorEntity(
				11L,
				"fn",
				"gn",
				"2,3",
				"4,5"
		);
		AuthorEntity authorEntity = converters.AuthorPayloadToEntity(authorPayload);
		Assertions.assertEquals(expectedAuthorEntity, authorEntity);
	}

	@Test
	void authorEntityToPayload_NullEntity() {
		Assertions.assertNull(converters.AuthorEntityToPayload(null));
	}

	@Test
	void authorEntityToPayload_FullEntity() {
		AuthorEntity authorEntity = new AuthorEntity(
				11L,
				"fn",
				"gn",
				"2,3",
				"4,5"
		);
		AuthorPayload expectedAuthorPayload = new AuthorPayload(
				11L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L)
		);
		AuthorPayload authorPayload = converters.AuthorEntityToPayload(authorEntity);
		Assertions.assertEquals(expectedAuthorPayload, authorPayload);
	}
}