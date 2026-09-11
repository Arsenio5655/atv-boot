package com.empresa.gestao.service;

import com.empresa.gestao.model.Veiculo;
import com.empresa.gestao.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo salvar(Veiculo veiculo) {
        if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            throw new RuntimeException("Já existe um veículo cadastrado com a placa: " + veiculo.getPlaca());
        }
        veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Optional<Veiculo> buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa.toUpperCase());
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com o ID: " + id));

        String novaPlaca = veiculoAtualizado.getPlaca().toUpperCase();
        if (!novaPlaca.equals(veiculoExistente.getPlaca()) && veiculoRepository.existsByPlaca(novaPlaca)) {
            throw new RuntimeException("Já existe um veículo cadastrado com a placa: " + novaPlaca);
        }

        veiculoExistente.setPlaca(novaPlaca);
        veiculoExistente.setMarca(veiculoAtualizado.getMarca());
        veiculoExistente.setModelo(veiculoAtualizado.getModelo());
        veiculoExistente.setAno(veiculoAtualizado.getAno());

        return veiculoRepository.save(veiculoExistente);
    }

    public void deletar(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado com o ID: " + id);
        }
        veiculoRepository.deleteById(id);
    }
}
