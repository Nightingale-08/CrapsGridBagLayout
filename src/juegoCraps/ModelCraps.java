package juegoCraps;
/**
 * This class control the game state
 * estado 1= Natural Winner
 * estado 2= Craps looser
 * estado 3= Establish Punto
 * estado 4= Punto looser
 * estado 5= Punto Winner
 * Apply Craps rules
 * Communicates with View Class.
 * @autor Luis-F. Belalcazar - A. 2028783 <luis.felipe.belalcazar@correounivalle.edu.co>
 * @Version v.1.0.0 date 30/11/21
 */
public class ModelCraps {
     private DadoCraps dado1,dado2;
     private  int tiro, punto, estado, flag;
     private String[] estadoJuego;
     private int[] caras;

    /**
     * Class Constructor
     */
    public ModelCraps(){

        dado1 = new DadoCraps();
        dado2 = new DadoCraps();
        caras= new int[2];
        estadoJuego = new String[2];
        flag = 0;

    }
    /**
     * This method calculate Tiro value, according to each dices
     */

    public void calcularTiro(){
       caras[0] =  dado1.getCaraDado();
       caras[1] =  dado2.getCaraDado();
       tiro = caras[0] + caras[1];

    }
    /**
     * This method establish game state according to estado value
     *  * estado 1= Natural Winner
     *  * estado 2= Craps looser
     *  * estado 3= Establish Punto

     */

    public void determinarJuego(){
        if(flag==0){
            if(tiro== 7 || tiro==11){
                estado = 1 ;
            }else{
                if(tiro==2 || tiro ==3 || tiro==12 ){
                    estado = 2;
                }else{
                    estado = 3;
                    punto = tiro;
                    flag = 1;
                }
            }
        }else{
            //ronda punto
            rondaPunto();
        }
    }

    /**
     * This method establish game state according to attribute Punto.
     * estado 4= Punto looser
     * estado 5= Punto Winner
     */

    public void rondaPunto(){

        if(tiro==punto){
            estado =4;
            flag = 0;

        }else {
            if (tiro == 7) {
                estado = 5;
                flag = 0;

            }else{
                estado=6;
            }
        }

    }

    public int getTiro() {
        return tiro;
    }


    public int getPunto() {
        return punto;
    }

    /**
     * This method establish messages of game state according to estado value
     * @return Message to View class
     */


    public String[] getEstadoJuego() {
        switch (estado){
            case 1 :  estadoJuego[0]= "Tiro de Salida= "+ tiro+".";
                      estadoJuego[1]= "Sacaste Natural, GANASTE Felicidades!!!";
                break;
            case 2 :  estadoJuego[0]= "Tiro de Salida= "+ tiro+".";
                      estadoJuego[1]= "Sacaste un valor de Craps, PERDISTE !!!.";
                break;
            case 3 :  estadoJuego[0]= "Tiro de Salida= "+ tiro+"." +
                                      "\nPunto = "+ punto+ ".";

                      estadoJuego[1]= "Estableciste Punto " + punto +
                                      " vuelve a tirar para sacar "+punto+" de nuevo."+
                                      "\nPero si sacas 7 antes de lograrlo, PERDERAS!!!";
                break;
            case 4 :  estadoJuego[0]= "Tiro de Salida= "+ punto+"." +
                                      "\nPunto = "+ punto+ "." +
                                      "\nEl valor del  nuevo tiro="+ tiro + ".";

                      estadoJuego[1]= "Volviste a a sacar "+punto+ " GANASTE!!!";
                break;
            case 5 :  estadoJuego[0]= "Tiro de Salida= "+ punto+"." +
                                      "\nPunto = "+ punto+ "." +
                                      "\nEl valor del  nuevo tiro="+ tiro + ".";
                      estadoJuego[1] = "Sacaste 7 antes que "+punto+" has PERDIDO!!";
                break;
            case 6: estadoJuego[0]= "Tiro de Salida= "+ punto+"." +
                    "\nPunto = "+ punto+ "." +
                    "\nEl valor del  nuevo tiro="+ tiro + ".";
                    estadoJuego[1] = "Estas en Punto y debes seguir lanzando!!!" +
                                     "\nPero si sacas 7 antes que "+punto+" has PERDIDO!!";
                break;
        }
        return estadoJuego;
    }

    public int[] getCaras() {
        return caras;
    }
}




