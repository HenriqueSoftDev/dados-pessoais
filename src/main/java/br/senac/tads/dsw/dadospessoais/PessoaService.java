package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaService {

    private Map<String, PessoaDto> mapPessoas;

    public PessoaService(){
        mapPessoas = new HashMap<>();

        mapPessoas.put("Henrique", new PessoaDto("Henrique","Henrique José","henrisilva2003@gmail.com",LocalDate.parse("2003-06-03"),"11 99999-9999",List.of("Java", "Spring Boot"),"123"));

        mapPessoas.put("Alexandrino", new PessoaDto("Alexandrino","Ryan Alexandrino","ryansoftdev@gmail.com",LocalDate.parse("2002-11-25"),"11 99999-9999",List.of("Python", "PyCharm"),"123"));

        mapPessoas.put("Lucas", new PessoaDto("Lucas","Lucas Beltrao","lucasgabriel405@gmail.com",LocalDate.parse("2001-12-31"),"11 99999-9999", List.of("Java", "SQL"),"123"));
    }
    public List<PessoaDto> findAll(){
        return new ArrayList<>(mapPessoas.values());
    }

    public PessoaDto findByUsername(String username){
        return null;
    }

    public PessoaDto addNew(PessoaDto dto){
        return null;
    }

    public PessoaDto update(String username, PessoaDto dto){
        return null;
    }

    public void delete(String username){
    }
}
