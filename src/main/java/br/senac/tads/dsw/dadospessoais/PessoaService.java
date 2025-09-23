package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PessoaService {

    private Map<String, PessoaDto> mapPessoas;

    public PessoaService() {
        mapPessoas = new HashMap<>();
        mapPessoas.put("henrique",
                new PessoaDto("henrique", "Henrique José Da Silva", LocalDate.parse("2000-10-20"),
                        "henrique@email.com", "11 91234-1234", List.of("Java", "Spring Boot"),
                        "Abcd1234"));
        mapPessoas.put("ryan",
                new PessoaDto("ryan", "Ryan Alexandrino", LocalDate.parse("1999-05-25"),
                        "ryan@email.com", "11 98765-8765", List.of("HTML", "CSS", "Javascript"),
                        "Abcd1234"));
        mapPessoas.put("lucas",
                new PessoaDto("lucas", "Lucas Beltrao", LocalDate.parse("2001-02-14"),
                        "beltrao@email.com", "11 91122-1122", List.of("Javascript", "React", "Angular"),
                        "Abcd1234"));
    }

    public List<PessoaDto> findAll() {
        return new ArrayList<>(mapPessoas.values());
    }

    public PessoaDto findByUsername(String username) {
        return mapPessoas.get(username);
    }

    public PessoaDto addNew(PessoaDto dto) {
        //Validação do Usuário
        if (mapPessoas.containsKey(dto.getUsername())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuario já cadastrado");
        }

        //Validação do Interesse
        boolean temInteresseValido = false;
        if (dto.getInteresses() != null) {
            for (String interesse : dto.getInteresses()) {
                if (interesse != null && !interesse.trim().isEmpty()) {
                    temInteresseValido = true;
                    break;
                }
            }
        }

        if (!temInteresseValido) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Preencha algum interesse");
        }

        //Validação da Senha
        if (!Objects.equals(dto.getSenha(), dto.getRepeticaoSenha())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "As senhas não conferem");
        } else if (dto.getSenha() == null || dto.getRepeticaoSenha() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A senha ou a repetição da senha não podem ser vazias");
        }

        mapPessoas.put(dto.getUsername(), dto);
        return dto;
    }

    public PessoaDto update(String username, PessoaDto dto) {
        return null;
    }

    public void delete(String username) {

    }
}