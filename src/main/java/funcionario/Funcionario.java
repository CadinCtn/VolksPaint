package funcionario;

import java.sql.Date;

public class Funcionario{
    private int cpf;
    private String nome;
    private Date dataNascimento;
    private boolean permissao;

    public Funcionario(int cpf, String nome, Date dataNascimento, boolean permissao) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.permissao = permissao;
    }

    
    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public boolean isPermissao() {
        return permissao;
    }

}