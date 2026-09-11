package com.empresa.gestao.controller;

import com.empresa.gestao.model.Veiculo;
import com.empresa.gestao.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoSalvo = veiculoService.salvar(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarTodos() {
        List<Veiculo> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return veiculoService.buscarPorId(id)
                .map(veiculo -> ResponseEntity.ok().body((Object) veiculo))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Veículo não encontrado com o ID: " + id));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
        return veiculoService.buscarPorPlaca(placa)
                .map(veiculo -> ResponseEntity.ok().body((Object) veiculo))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Veículo não encontrado com a placa: " + placa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                        @Valid @RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizar(id, veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        } catch (RuntimeException e) {
            String mensagem = e.getMessage();
            if (mensagem != null && mensagem.contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
            }
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            veiculoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
