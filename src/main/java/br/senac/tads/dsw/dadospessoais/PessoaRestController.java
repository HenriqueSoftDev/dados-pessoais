package br.senac.tads.dsw.dadospessoais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController //Caso utilize a anotação @Controller, precisaria complementar as classes com o @ResponseBody
public class PessoaRestController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<PessoaDto> findAll(){
        return service.findAll();
    }

    //Passando os valores diretamente na URL
    @GetMapping("/apelido")
    public PessoaDto findByUsernameRequestParam(@RequestParam(value = "username", required = false) String u){
        return service.findByUsername(u);
    }

    //Passando os valores dos parametros por meio de variaveis
    @GetMapping("/{username}")
    public PessoaDto findbyUsername(@PathVariable("username") String username){
        PessoaDto pessoa = service.findByUsername(username);
        if (pessoa == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa %s não encontrada".formatted(username));
        }
        return pessoa;
    }

    @PostMapping
    public ResponseEntity<PessoaDto> addNew(@RequestBody PessoaDto input){
        PessoaDto pessoa = service.addNew(input);

        //TODO: CRIAR URI
        return ResponseEntity.created(null).build();

    }
}
