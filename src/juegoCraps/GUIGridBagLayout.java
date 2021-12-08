package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is the visual interface with a GridBagLayout
 * @autor Luis-F. Belalcazar - A. 2028783 <luis.felipe.belalcazar@correounivalle.edu.co>
 * @Version v.1.0.0 date 30/11/21
 */
public class GUIGridBagLayout extends JFrame {
    public static final String MENSAJE_INICIO="Bienvenido a Craps.\n" +
            "Oprime el boton lanzar para iniciar el juego."
            +"\nSi tu tiro de salida es 7 u 11 ganas con Natural."
            +"\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps."
            +"\nSi sacas cualquier otro valor estableceras el Punto."
            +"\nEstando en punto podras seguir lanzando los dados."
            +"\nPero ahora ganaras si sacas nuevamente el valor del punto."
            +"\nSi sacas 7 antes perderas.";


    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar,ayuda,salir;
    private JPanel panelDados;
    private JTextArea mensajeSalida, resultadosDados ;
    private ImageIcon imageDados;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private JFrame window;
    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout (){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(55,105,155,80));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }

    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints= new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha= new Escucha();
        modelCraps = new ModelCraps();
        window=this;
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        headerProject.addMouseListener(escucha);
        headerProject.addMouseMotionListener(escucha);
        headerProject.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda=new JButton( " ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir=new JButton( " Salir ");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        imageDados= new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1= new JLabel(imageDados);
        dado2= new JLabel(imageDados);


        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelDados,constraints);

        resultadosDados = new JTextArea(4,31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Debes lanzar los dados");
        resultadosDados.setBackground(null);
        resultadosDados.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);

        lanzar =new JButton( " Lanzar ");
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(lanzar,constraints);

        mensajeSalida = new JTextArea(4,31);
        mensajeSalida.setText("Usa el boton (?) para ver las instrucciones");
        mensajeSalida.setBorder(BorderFactory.createTitledBorder(" Mensajes "));
        mensajeSalida.setBackground(null);
        mensajeSalida.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(mensajeSalida,constraints);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUIGridBagLayout = new GUIGridBagLayout() ;
        });
    }


    private class Escucha implements ActionListener, MouseListener, MouseMotionListener {
        private int x,y;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lanzar) {
                modelCraps.calcularTiro();
                int[] caras = modelCraps.getCaras();
                imageDados = new ImageIcon(getClass().getResource("/resources/" + caras[0] + ".png"));
                dado1.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/resources/" + caras[1] + ".png"));
                dado2.setIcon(imageDados);

                modelCraps.determinarJuego();
                resultadosDados.setText(modelCraps.getEstadoJuego()[0]);
                mensajeSalida.setRows(4);
                mensajeSalida.setText(modelCraps.getEstadoJuego()[1]);
            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
           x= e.getX();
           y= e.getY();

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            setLocation(window.getLocation().x + e.getX()-x,
                        window.getLocation().y + e.getY()-y);


        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
