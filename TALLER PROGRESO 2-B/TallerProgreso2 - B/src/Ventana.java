import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JSpinner spId;
    private JTextField txtNombre;
    private JComboBox cboPosicion;
    private JSpinner spEdad;
    private JTextField txtRendimiento;
    private JButton btnRegistrar;
    private JList lstJugadores;
    private JButton btnListar;
    private JTextField txtAId;
    private JTextField txtANombre;
    private JComboBox cboAPosicion;
    private JTextField txtAEdad;
    private JTextField txtARendimiento;
    private JButton btnActualizar;
    private JComboBox cboSPosicion;
    private JButton btnSumar;
    private JTextField txtResultado;
    private JButton btnEliminar;
    private JTextArea txtConteo;
    private JButton btnConteo;
    private Lista equipo=new Lista();

    private void llenarJlist(){
        List<Jugador> listado=equipo.getEquipo();
        DefaultListModel dlm=new DefaultListModel();
        for(Jugador j:listado){
            dlm.addElement(j.toString());
        }
        lstJugadores.setModel(dlm);
    }
    public Ventana() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (estaVacion()){
                        int id=Integer.parseInt(spId.getValue().toString());
                        String nombre=txtNombre.getText();
                        int edad=Integer.parseInt(spEdad.getValue().toString());
                        float rendimiento=Float.parseFloat(txtRendimiento.getText());
                        String posicion=cboPosicion.getSelectedItem().toString();
                        if (id==0){
                            JOptionPane.showMessageDialog(null,"Ponga una ID valida");
                        }else{
                            if (equipo.verificarID(id)){
                                if(equipo.verificadorRangos(rendimiento,edad)){
                                    Jugador jugadornuevo = new Jugador(id,nombre,posicion,rendimiento,edad);
                                    equipo.agregar(jugadornuevo);
                                    JOptionPane.showMessageDialog(null,"REGISTRO NUEVO");

                                }else{
                                    JOptionPane.showMessageDialog(null,"Ingrese en el rango estipulado");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"ID EN USO");
                            }
                        }


                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                llenarJlist();
            }

        });
        lstJugadores.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstJugadores.getSelectedIndex()!=-1){
                    int indice=lstJugadores.getSelectedIndex();
                    Jugador j=equipo.getEquipo().get(indice);
                    txtAId.setText(""+j.getIdentificador());
                    txtANombre.setText(j.getNombre());
                    txtAEdad.setText(""+j.getEdad());
                    txtARendimiento.setText(""+j.getRendimiento());
                    cboAPosicion.setSelectedItem(j.getPosicion());

                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(txtAId.getText());
                String nombre=txtANombre.getText();
                int edad=Integer.parseInt(txtAEdad.getText());
                float rendimiento=Float.parseFloat(txtARendimiento.getText());
                String posicion=cboAPosicion.getSelectedItem().toString();
                Jugador jugador=new Jugador(id,nombre,posicion,rendimiento,edad);
                if(equipo.actualizar(jugador)) {
                    JOptionPane.showMessageDialog(null,
                            "Elemento actualizado");
                    llenarJlist();
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "No se pudo actualizar");


            }
        });
        btnSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultado.setText("La suma es: "+
                        equipo.suma(cboSPosicion.getSelectedItem().toString()));
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    equipo.eliminarJugador();
                    JOptionPane.showMessageDialog(null,"JUGADOR CON MENOR RENDIMIENTO ELIMINADO CORRECTAMENTE");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        btnConteo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtConteo.setText(""+equipo.contarMenoress20());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public boolean estaVacion() throws Exception {
        if (txtNombre.getText().equals("")||txtRendimiento.getText().equals("")){
            throw new Exception("ELEMENTOS VACIOS");
        }
        return true;
    }

//    public boolean estaVacion() throws Exception {
//        if (txtNombre.getText().equals("") || txtRendimiento.getText().equals("")){
//            throw new Exception("ELEMENTOS VACIOS");
//        }
//        return true;
//    }

}
