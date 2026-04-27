package modelo;
import javax.swing.*;
public class Ventana {

    private JPanel panelPrincipal;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JComboBox<Ruta> cboRutas;
    private JButton btnComprar;
    private JTextArea txtAreaCompras;

    private JLabel lblVendidosGYE;
    private JLabel lblDisponiblesGYE;
    private JLabel lblVendidosCUE;
    private JLabel lblDisponiblesCUE;
    private JLabel lblVendidosLOJ;
    private JLabel lblDisponiblesLOJ;
    private JLabel lblTotalRecaudado;

    private GestorVentas gestor;

    public Ventana() {

        gestor = new GestorVentas();

        // llenar combo
        for (Ruta r : Ruta.getRutas()) {
            cboRutas.addItem(r);
        }

        // valores iniciales
        actualizarLabels();

        // botón comprar
        btnComprar.addActionListener(e -> comprar());
    }

    private void comprar() {

        try {
            Ruta ruta = (Ruta) cboRutas.getSelectedItem();
            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());

            Compra compra = gestor.registrarCompra(ruta, cedula, nombre, cantidad);

            // mostrar en textarea
            txtAreaCompras.append(compra.toString() + "\n");

            // actualizar labels
            actualizarLabels();

            // limpiar campos
            txtCedula.setText("");
            txtNombre.setText("");
            txtCantidad.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void actualizarLabels() {

        lblVendidosGYE.setText("" + gestor.getVendidos(Ruta.GUAYAQUIL));
        lblDisponiblesGYE.setText("" + gestor.getDisponibles(Ruta.GUAYAQUIL));

        lblVendidosCUE.setText("" + gestor.getVendidos(Ruta.CUENCA));
        lblDisponiblesCUE.setText("" + gestor.getDisponibles(Ruta.CUENCA));

        lblVendidosLOJ.setText("" + gestor.getVendidos(Ruta.LOJA));
        lblDisponiblesLOJ.setText("" + gestor.getDisponibles(Ruta.LOJA));

        lblTotalRecaudado.setText("$" + gestor.getTotalRecaudado());
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}