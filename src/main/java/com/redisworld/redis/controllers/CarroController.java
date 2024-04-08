package com.redisworld.redis.controllers;

import com.redisworld.redis.dto.Carro;
import com.redisworld.redis.dto.CarroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CarroController {
    private  final Logger LOG = LoggerFactory.getLogger(getClass());

    private  final CarroRepository carroRepository;

    @Autowired
    public CarroController(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }

    @Cacheable(value = "carros", key = "#carroId", unless = "#result.precio < 60000")
    @RequestMapping (value = "/get/{carroId}",method = RequestMethod.GET)
    public Carro getCarro(@PathVariable String carroId){
        LOG.info("Obteniendo el carro con el ID {}.", carroId);
        long i = Long.valueOf(carroId);
        return carroRepository.findById(i).orElse(null);
    }

    @CachePut(value = "carros", key = "#carro.id")
    @PutMapping("/add")
    public Carro addCarro(@RequestBody Carro carro){
        carroRepository.save(carro);
        return carro;
    }

    @CacheEvict(value = "carros", allEntries = true)
    @DeleteMapping("/delete/{carroId}")
    public void deleteCarroByID(@PathVariable Long carroId){
        LOG.info("Eliminando Carro con el Id {}.", carroId);
        Optional<Carro> car = carroRepository.findById(carroId);
        if (!car.isEmpty()){
            Carro carro = car.get();
            carroRepository.delete(carro);
        }
    }
}
