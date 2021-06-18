package com.ecuex.ornekproje;

import com.ecuex.ornekproje.model.Address;
import com.ecuex.ornekproje.model.City;
import com.ecuex.ornekproje.model.CustomerEntity;
import com.ecuex.ornekproje.model.Town;
import com.ecuex.ornekproje.repository.AddressRepository;
import com.ecuex.ornekproje.repository.CityRepository;
import com.ecuex.ornekproje.repository.CustomerRepository;
import com.ecuex.ornekproje.repository.TownRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.ArrayList;

@SpringBootApplication
public class OrnekprojeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrnekprojeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
										AddressRepository addressRepository) {
		return args -> {
			CustomerEntity customerEntity =
					new CustomerEntity(
							19750055956L, "Haluk", "Erd",
							new Address("Adana", "Seyhan"));
			customerRepository.save(customerEntity);
		};
	}


}