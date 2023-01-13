package com.api.attornatus.controllers;

import com.api.attornatus.dtos.PessoaDto;
import com.api.attornatus.models.EnderecoModel;
import com.api.attornatus.models.PessoaModel;
import com.api.attornatus.services.EnderecoService;
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
    final EnderecoService enderecoService;

    public PessoaController(PessoaService pessoaService, EnderecoService enderecoService){
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
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

    @GetMapping("/{pessoaId}/enderecos")
    public ResponseEntity<List<EnderecoModel>> getAllEnderecosPessoa(@PathVariable(value = "pessoaId") UUID pessoaId){
        var pessoaModel = pessoaService.findById(pessoaId).get();

        return ResponseEntity.status(HttpStatus.OK).body(pessoaModel.getEnderecos());
    }

    @GetMapping("/{pessoaId}/principalEnd")
    public ResponseEntity<Object> getPrincipalEndereco(@PathVariable(value = "pessoaId") UUID pessoaId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);

        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        List<EnderecoModel> listEnderecos = pessoaModelOptional.get().getEnderecos();
        EnderecoModel enderecoPrincipal = new EnderecoModel();

        for(EnderecoModel enderecoModel : listEnderecos){
            if(enderecoModel.getPrincipal()){
                enderecoPrincipal = enderecoModel;
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(enderecoPrincipal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") UUID id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);

        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa Deletada com Sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid PessoaDto pessoaDto){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);

        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        var pessoaModel = pessoaModelOptional.get();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setId(pessoaModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));
    }
}
