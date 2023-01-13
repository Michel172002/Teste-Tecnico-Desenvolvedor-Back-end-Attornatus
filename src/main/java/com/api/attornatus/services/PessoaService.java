package com.api.attornatus.services;

import com.api.attornatus.models.EnderecoModel;
import com.api.attornatus.models.PessoaModel;
import com.api.attornatus.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {
    final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaModel save(PessoaModel pessoaModel){
        return pessoaRepository.save(pessoaModel);
    }

    public List<PessoaModel> findAll(){
        return pessoaRepository.findAll();
    }

    public Optional<PessoaModel> findById(UUID id){
        return pessoaRepository.findById(id);
    }

    @Transactional
    public void delete(PessoaModel pessoaModel){
        pessoaRepository.delete(pessoaModel);
    }

}
