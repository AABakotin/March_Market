package ru.geekbrains.march.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarchMarketApplication {


	// com.fasterxml.jackson.core.JsonParseException: Unexpected character ('�' (code 65533 / 0xfffd)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
	// at [Source: (String)"��\u001E�쉅����!L��؉"; line: 1, column: 2]

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketApplication.class, args);
	}
}
