package br.senac.tads.dsw.dadospessoais;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HeaderController {

    //HeaderController
    @GetMapping("/headers")
    public Map<String, Object> findHttpHeader(
            @RequestHeader Map<String, Object> cabecalhos){
        return  cabecalhos;
    }

    @GetMapping("/headers/user-agent")
    public String findUserAgent(
            @RequestHeader("user-agent") String userAgent){
        return userAgent;
    }
}
