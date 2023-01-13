package com.api.attornatus.services;

import com.api.attornatus.models.EnderecoModel;
import com.api.attornatus.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

}
