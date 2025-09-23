
package br.senac.tads.dsw.dadospessoais;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaRestController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<PessoaDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/apelido")
    public PessoaDto findByUsernameRequestParam(
            @RequestParam(value = "username", defaultValue = "beltrana") String u) {
        return service.findByUsername(u);
    }

    @GetMapping("/{username}")
    public PessoaDto findByUsername(@PathVariable("username") String username) {
        PessoaDto pessoa = service.findByUsername(username);
        if (pessoa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa %s não encontrada".formatted(username));
        }
        return pessoa;
    }

    @PostMapping
    public ResponseEntity<PessoaDto> addNew(@RequestBody @Valid PessoaDto input) {
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

