package br.senac.tads.dsw.dadospessoais;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController //Caso utilize a anotação @Controller, precisaria complementar as classes com o @ResponseBody
@RequestMapping("/pessoas")
public class PessoaRestController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<PessoaDto> findAll(){
        return service.findAll();
    }

    //Passando os valores diretamente na URL
    @GetMapping("/apelido")
    public PessoaDto findByUsernameRequestParam(@RequestParam(value = "username", defaultValue = "teste") String u){
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
    public ResponseEntity<PessoaDto> addNew(@RequestBody PessoaDto input) {
        PessoaDto pessoa = service.addNew(input);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{username}")
                .buildAndExpand(pessoa.getUsername())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{username}")
    public ResponseEntity<PessoaDto> update(@PathVariable String username,
                                            @RequestBody @Valid PessoaDto input) {
        PessoaDto pessoa = service.findByUsername(username);
        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }
        service.update(username, input);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        service.delete(username);
        return ResponseEntity.noContent().build();
    }
}
