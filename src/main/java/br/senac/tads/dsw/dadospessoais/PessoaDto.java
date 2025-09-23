
package br.senac.tads.dsw.dadospessoais;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class PessoaDto {

    @NotBlank
    @Size(min = 3, max = 64)
    private String username;

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @PastOrPresent
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 3, max = 100)
    @Email
    private String email;

    @Size(min = 0, max = 20)
    private String telefone;

    @Size(min = 1)
    private List<@NotBlank String> interesses;

    private String senha;

    private String repeticaoSenha;


    public PessoaDto() {
    }

    public PessoaDto(String username, String nome, LocalDate dataNascimento,
                     String email, String telefone, List<String> interesses, String senha) {
        this.username = username;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.interesses = interesses;
        this.senha = senha;
        this.repeticaoSenha = getRepeticaoSenha();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getInteresses() {
        return interesses;
    }

    public void setInteresses(List<String> interesses) {
        this.interesses = interesses;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepeticaoSenha() {
        return repeticaoSenha;
    }

    public void setRepeticaoSenha(String repeticaoSenha) {
        this.repeticaoSenha = repeticaoSenha;
    }
}
