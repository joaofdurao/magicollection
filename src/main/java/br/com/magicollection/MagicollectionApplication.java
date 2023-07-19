package br.com.magicollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MagicollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagicollectionApplication.class, args);
	}

	// @Bean
	// CommandLineRunner init(PlayerRepository repository){
	// 	return args ->{
	// 		repository.deleteAll();
	// 		LongStream.range(1, 9).mapToObj(i ->{

	// 			Player player = new Player();
	// 			player.setUsername("Testename");
	// 			player.setEmail("testeemail");
	// 			player.setPassword("1234");
	// 			player.setGender("MALE");
	// 			return player;
	// 		})
	// 		.map(m->repository.save(m))
	// 		.forEach(System.out::println);
	// 	};
	// }
}
