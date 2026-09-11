package com.empresa.gestao;

import com.empresa.gestao.model.Veiculo;
import com.empresa.gestao.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (veiculoRepository.count() == 0) {
            veiculoRepository.save(new Veiculo(null, "ABC1234", "Toyota",     "Corolla", 2022));
            veiculoRepository.save(new Veiculo(null, "DEF5678", "Volkswagen", "Gol",     2020));
            veiculoRepository.save(new Veiculo(null, "GHI9012", "Fiat",       "Uno",     2019));
            veiculoRepository.save(new Veiculo(null, "JKL3456", "Honda",      "Civic",   2023));
            veiculoRepository.save(new Veiculo(null, "MNO7890", "Chevrolet",  "Onix",    2021));
        }
    }
}
