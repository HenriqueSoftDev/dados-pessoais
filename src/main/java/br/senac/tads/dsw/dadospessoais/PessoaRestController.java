package br.senac.tads.dsw.dadospessoais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return  service.findByUsername(username);
    }
}
