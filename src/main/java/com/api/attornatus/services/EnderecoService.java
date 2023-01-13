package com.api.attornatus.services;

import com.api.attornatus.models.EnderecoModel;
import com.api.attornatus.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {
    final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public EnderecoModel save(EnderecoModel enderecoModel){
        return enderecoRepository.save(enderecoModel);
    }

    public List<EnderecoModel> findAll(){
        return enderecoRepository.findAll();
    }

    public Optional<EnderecoModel> findById(UUID id){
        return enderecoRepository.findById(id);
    }

    @Transactional
    public void delete(EnderecoModel enderecoModel){
        enderecoRepository.delete(enderecoModel);
    }
}
