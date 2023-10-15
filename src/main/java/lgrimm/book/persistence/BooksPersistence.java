package lgrimm.book.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@EnableJpaRepositories
public class BooksPersistence {

	public static void main(String[] args) {
		SpringApplication.run(BooksPersistence.class, args);
	}

}
