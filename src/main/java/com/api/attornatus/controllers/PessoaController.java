package com.api.attornatus.controllers;

import com.api.attornatus.dtos.PessoaDto;
import com.api.attornatus.models.PessoaModel;
import com.api.attornatus.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoa")
public class PessoaController {
    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Object> savePessoa(@RequestBody @Valid PessoaDto pessoaDto){
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value = "id") UUID id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);

        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não Encontrada!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

}
