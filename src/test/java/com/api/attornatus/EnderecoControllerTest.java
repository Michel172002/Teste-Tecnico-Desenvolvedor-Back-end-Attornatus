package com.api.attornatus;

import com.api.attornatus.models.EnderecoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String pessoaIdTest = "31000000-0000-0000-0000-000000000000";

    @Test
    public void saveEnderecoTest() throws Exception{
        EnderecoModel enderecoModelTest = new EnderecoModel();

        enderecoModelTest.setId(UUID.fromString("5ba239e7-fc5e-423f-9141-91d6397a58f7"));
        enderecoModelTest.setLogradouro("Miguel Arcanjo Alves");
        enderecoModelTest.setCep("12239039");
        enderecoModelTest.setNumero("240");
        enderecoModelTest.setCidade("São José dos Campos");
        enderecoModelTest.setPrincipal(false);

        mockMvc.perform(post("/endereco/" + pessoaIdTest)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(enderecoModelTest)))
                .andExpect(status().isCreated());
    }
}
