package lgrimm.book.persistence.core;

import lgrimm.book.persistence.core.payloads.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

@WebMvcTest(PersistenceController.class)
@AutoConfigureDataJpa
class PersistenceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorsService authorsService;

	String basePath = "/api/v1";

	@BeforeEach
	void setUp() {
	}

	@Test
	void createAuthor_WrongRequestBody() throws Exception {
		String content = "";
		mockMvc
				.perform(
						post(basePath + "/author")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.accept(MediaType.APPLICATION_JSON_VALUE)
								.content(content)
				)
				//.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	void createAuthor_WrongDataInRequestBody() throws Exception {
		String json = "{" +
				"\"id\":1," +
				"\"familyName\":\"fn\"," +
				"\"givenName\":\"gn\"," +
				"\"bookIds\":[2,3]," +
				"\"seriesIds\":[4,5]," +
				"\"message\":\"\"" +
				"}";
		AuthorPayload authorPayload = new AuthorPayload(
				1L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"");

		AuthorPayload expectedAuthorPayload = new AuthorPayload(
				1L,
				"fn",
				"gn",
				List.of(2L, 3L),
				List.of(4L, 5L),
				"error message");
		when(authorsService.createAuthor(authorPayload))
				.thenReturn(expectedAuthorPayload);

		String expectedJson = "{" +
				"\"id\":1," +
				"\"familyName\":\"fn\"," +
				"\"givenName\":\"gn\"," +
				"\"bookIds\":[2,3]," +
				"\"seriesIds\":[4,5]," +
				"\"message\":\"error message\"" +
				"}";
		mockMvc
				.perform(
						post(basePath + "/author")
								.contentType(MediaType.APPLICATION_JSON_VALUE)
								.accept(MediaType.APPLICATION_JSON_VALUE)
								.content(json)
				)
				//.andDo(print())
/*
				.andExpect(status().is4xxClientError())
				.andExpect(content().string(containsString("message")));
*/
				//.andExpect(result -> assertEquals("message", result.getResolvedException().getMessage()));
				.andExpect(content().string(expectedJson));
	}
}