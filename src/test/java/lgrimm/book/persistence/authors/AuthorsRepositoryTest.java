package lgrimm.book.persistence.authors;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorsRepositoryTest {

	@Autowired
	AuthorsRepository repository;
	AuthorEntity entity, entity2, entity3;

	@BeforeEach
	void setUp() {
		entity = new AuthorEntity(
				"Surname_1 Surname_2",
				"Firstname_1 Firstname_2",
				null,
				null);
		entity.setBookIdList(List.of(11L, 12L));
		entity.setSeriesIdList(List.of(21L, 22L));
	}

	@AfterEach
	void tearDown() {
		repository.deleteAll();
	}

	@Test
	void saveAuthor_GeneratesId_InheritsFieldValues() {
		entity2 = repository.save(entity);
		Assertions.assertNotEquals(0, entity2.getId());
		Assertions.assertTrue(equalsWithoutId(entity, entity2));
	}

	@Test
	void loadAuthor_DatabaseContainsNewRecord_NewRecordHasSameFieldValues() {
		long id = repository.save(entity).getId();
		Optional<AuthorEntity> optionalAuthorEntity = repository.findById(id);
		Assertions.assertTrue(optionalAuthorEntity.isPresent());
		entity2 = optionalAuthorEntity.get();
		Assertions.assertTrue(equalsWithoutId(entity, entity2));
	}

	private boolean equalsWithoutId(AuthorEntity entity1, AuthorEntity entity2) {
		if (entity1 == null || entity2 == null) {
			return false;
		}
		return entity1.getFamilyName().equals(entity2.getFamilyName()) &&
				entity1.getGivenName().equals(entity2.getGivenName()) &&
				entity1.getBookIds().equals(entity2.getBookIds()) &&
				entity1.getSeriesIds().equals(entity2.getSeriesIds());
	}
}