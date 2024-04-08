
package com.redisworld.redis;

import com.redisworld.redis.dto.Carro;
import com.redisworld.redis.dto.CarroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final CarroRepository carroRepository;


	@Autowired
	public RedisApplication(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Guardando carros. Cuenta de libros es {}.", carroRepository.count());
		Carro Ford = new Carro("Ford","Mustang", 45000);
		Carro Audi = new Carro("Audi","Q5", 60000);
		Carro Jeep = new Carro("Jeep","Wrangler", 38000);
		Carro Ferrari = new Carro("Ferrari","Enzo", 4500000);
		Carro BMW = new Carro("BMW","I8", 130000);
		Carro Chevrolet = new Carro("Chevrolet","Corvette", 45000);

		carroRepository.save(Ford);
		carroRepository.save(Audi);
		carroRepository.save(Jeep);
		carroRepository.save(Ferrari);
		carroRepository.save(BMW);
		carroRepository.save(Chevrolet);
		LOG.info("Se ha guardado los carros. Datos: {}.", carroRepository.findAll());
	}
}
