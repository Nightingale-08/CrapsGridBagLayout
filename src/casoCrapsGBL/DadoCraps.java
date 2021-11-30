package casoCrapsGBL;

import java.util.Random ;
/**
* This class generate a random value between 1 and 6
 * @autor Luis-F. Belalcazar - A. 2028783 <luis.felipe.belalcazar@correounivalle.edu.co>
 * @Version v.1.0.0 date 30/11/21
*/
public class DadoCraps {
    private int caraDado;
    /**
     * This method generate a random value to caraDado
     * @return one number between (1,6)
     */
    public int getCaraDado() {
        Random aleatory = new Random();
        caraDado = aleatory.nextInt(6) + 1;//nextInt devuelve un numero menos ya que inicia en 0 por eso el +1


        return caraDado;
    }


}
