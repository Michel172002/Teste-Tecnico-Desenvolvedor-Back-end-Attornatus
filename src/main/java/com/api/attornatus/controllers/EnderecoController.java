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

    @PostMapping
    public ResponseEntity<Object> saveEndereco(@RequestBody @Valid EnderecoDto enderecoDto){
        var enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoModel));
    }

    @GetMapping
    public ResponseEntity getAllEnderecos(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }

    @PutMapping("/{enderecoId}/pessoa/{pessoaId}")
    public ResponseEntity<Object> addEnderecoPessoa(@PathVariable(name = "enderecoId") UUID enderecoId,
                                                    @PathVariable(name = "pessoaId") UUID pessoaId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.findById(enderecoId);
        if (!pessoaModelOptional.isPresent() || !enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa ou Endereço não encontrado!");
        }

        var pessoaModel = pessoaModelOptional.get();
        var enderecoModel = enderecoModelOptional.get();

        enderecoModel.vincularPessoa(pessoaModel);

        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(enderecoModel));
    }
}
