package pintura;

import java.sql.Date;
import java.text.SimpleDateFormat;
import peca.ServicePeca;
import tinta.ServiceTinta;


public class Pintura {

    private int id;
    private Date data;
    private int linhaProducao;
    private int turno;
    private int idPeca;
    private int qtdPeca;
    private int idTinta;
    private float qtdTinta;
    private float limiteConsumoTinta;

    public Pintura(int id, Date data, int linhaProducao, int turno, int idPeca, int qtdPeca, int idTinta, float qtdTinta, float limiteConsumoTinta) {
        this.id = id;
        this.data = data;
        this.linhaProducao = linhaProducao;
        this.turno = turno;
        this.idPeca = idPeca;
        this.qtdPeca = qtdPeca;
        this.idTinta = idTinta;
        this.qtdTinta = qtdTinta;
        this.limiteConsumoTinta = limiteConsumoTinta;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public int getLinhaProducao() {
        return linhaProducao;
    }

    public int getTurno() {
        return turno;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public int getQtdPeca() {
        return qtdPeca;
    }

    public int getIdTinta() {
        return idTinta;
    }

    public float getQtdTinta() {
        return qtdTinta;
    }

    public float getLimiteConsumoTinta() {
        return limiteConsumoTinta;
    }

    
    public Object[] pinturaInTable(){
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy");
        
        Object[] line = {id,dateForm.format(data),linhaProducao,turno,new ServicePeca().selectByID(idPeca).getModelo(),qtdPeca,new ServiceTinta().selectByID(idTinta).getCor(),qtdTinta,limiteConsumoTinta};
        return line;
    }

}
