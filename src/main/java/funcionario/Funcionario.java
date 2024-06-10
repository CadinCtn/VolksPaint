package funcionario;

import java.sql.Date;

public class Funcionario{
    private String cpf;
    private String nome;
    private String senha;
    private Date dataNascimento;

    public Funcionario(String cpf, String nome, String senha,Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getSenha() {
        return senha;
    }
}