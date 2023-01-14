package com.api.attornatus;

import com.api.attornatus.models.PessoaModel;
import com.api.attornatus.services.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String pessoaIdTest = "31000000-0000-0000-0000-000000000000";

    @Test
    public void savePessoaTest() throws Exception{
        LocalDate nascimento = LocalDate.parse("1999-10-23");

        PessoaModel pessoaModelTest = new PessoaModel();
        pessoaModelTest.setId(UUID.fromString("ba6e3659-3b4a-48a0-ab20-57d4af329dbb"));
        pessoaModelTest.setNome("michel");
        pessoaModelTest.setNascimento(nascimento);

        mockMvc.perform(post("/pessoa")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pessoaModelTest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllPessoasTest() throws Exception{
        mockMvc.perform(get("/pessoa"))
                .andExpect(status().isOk());
    }

    @Test
    public void getOnePessoaTest() throws Exception{
        mockMvc.perform(get("/pessoa/" + pessoaIdTest))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllEnderecosPessoaTest() throws Exception{
        mockMvc.perform(get("/pessoa/" + pessoaIdTest + "/enderecos"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPrincipalEnderecoTest() throws Exception{
        mockMvc.perform(get("/pessoa/" + pessoaIdTest + "/principalEnd"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePessoaTest() throws Exception{
        LocalDate nascimento = LocalDate.parse("1999-10-23");

        PessoaModel pessoaModelTest = new PessoaModel();
        pessoaModelTest.setId(UUID.fromString("ba6e3659-3b4a-48a0-ab20-57d4af329dbb"));
        pessoaModelTest.setNome("michel");
        pessoaModelTest.setNascimento(nascimento);

        mockMvc.perform(put("/pessoa/" + pessoaIdTest)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pessoaModelTest)))
                .andExpect(status().isOk());
    }

}
