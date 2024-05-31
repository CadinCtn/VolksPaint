package tinta;

import java.sql.Date;



/**
 *
 * @author Lenovo
 */
public class ConsumoTinta {
    
    
    Date data_registro;
    float turno1;
    float turno2;
    float turno3;

    public ConsumoTinta(Date data_registro, float turno1, float turno2, float turno3) {
        this.data_registro = data_registro;
        this.turno1 = turno1;
        this.turno2 = turno2;
        this.turno3 = turno3;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public float getTurno1() {
        return turno1;
    }

    public float getTurno2() {
        return turno2;
    }

    public float getTurno3() {
        return turno3;
    }
    
    public float getTotal(){
        return turno1 + turno2 + turno3;
    }
    
    
}
