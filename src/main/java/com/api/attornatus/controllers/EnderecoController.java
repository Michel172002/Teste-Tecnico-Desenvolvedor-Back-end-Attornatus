package com.api.attornatus.controllers;

import com.api.attornatus.dtos.EnderecoDto;
import com.api.attornatus.models.EnderecoModel;
import com.api.attornatus.models.PessoaModel;
import com.api.attornatus.services.EnderecoService;
import com.api.attornatus.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/endereco")
public class EnderecoController {
    final EnderecoService enderecoService;
    final PessoaService pessoaService;

    public EnderecoController(EnderecoService enderecoService, PessoaService pessoaService){
        this.enderecoService = enderecoService;
        this.pessoaService = pessoaService;
    }

    @PostMapping("/{pessoaId}")
    public ResponseEntity<Object> saveEndereco(@RequestBody @Valid EnderecoDto enderecoDto,
                                               @PathVariable(value = "pessoaId") UUID pessoaId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrado!");
        }

        var enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setPessoa(pessoaModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoModel));
    }
}
