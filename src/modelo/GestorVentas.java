package modelo;

import java.util.ArrayList;

public class GestorVentas {

    private ArrayList<Compra> compras;

    public GestorVentas() {
        compras = new ArrayList<>();
    }

    // Registra una compra si pasa todas las validaciones
    public Compra registrarCompra(Ruta ruta, String cedula, String nombre, int cantidad) {

        // Validar que haya ruta seleccionada
        if (ruta == null) {
            throw new IllegalArgumentException("Debe seleccionar una ruta.");
        }

        // Validar cedula
        if (cedula == null || cedula.trim().equals("")) {
            throw new IllegalArgumentException("La cedula no puede estar vacia.");
        }
        String ced = cedula.trim();
        if (ced.length() != 10) {
            throw new IllegalArgumentException("La cedula debe tener 10 digitos.");
        }
        for (int i = 0; i < ced.length(); i++) {
            if (!Character.isDigit(ced.charAt(i))) {
                throw new IllegalArgumentException("La cedula solo debe tener numeros.");
            }
        }

        // Validar nombre
        if (nombre == null || nombre.trim().equals("")) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }

        // Validar cantidad entre 1 y 5
        if (cantidad < 1 || cantidad > Ruta.MAX_BOLETOS) {
            throw new IllegalArgumentException("La cantidad debe estar entre 1 y " + Ruta.MAX_BOLETOS + ".");
        }

        // Validar capacidad disponible
        int disponibles = getDisponibles(ruta.getNombre());
        if (cantidad > disponibles) {
            throw new IllegalArgumentException("No hay suficientes asientos. Disponibles: " + disponibles);
        }

        // Validar que la cedula no haya comprado ya 5 en esta ruta
        int yaComprados = getBoletosPorCedula(ruta.getNombre(), ced);
        if (yaComprados + cantidad > Ruta.MAX_BOLETOS) {
            throw new IllegalArgumentException("La cedula " + ced + " ya tiene "
                    + yaComprados + " boleto(s) en esta ruta. Maximo " + Ruta.MAX_BOLETOS + ".");
        }

        // Si todo esta bien, crear la compra y guardarla
        Compra compra = new Compra(ruta.getNombre(), ced, nombre.trim(), cantidad, ruta.getPrecio());
        compras.add(compra);
        return compra;
    }

    // Cuenta cuantos boletos se han vendido en una ruta
    public int getVendidos(String rutaNombre) {
        int total = 0;
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getRutaNombre().equals(rutaNombre)) {
                total += c.getCantidad();
            }
        }
        return total;
    }

    // Cuantos asientos quedan libres en una ruta
    public int getDisponibles(String rutaNombre) {
        return Ruta.CAPACIDAD - getVendidos(rutaNombre);
    }

    // Total de dinero recaudado
    public double getTotalRecaudado() {
        double total = 0;
        for (int i = 0; i < compras.size(); i++) {
            total += compras.get(i).getPrecioTotal();
        }
        return total;
    }

    // Cuantos boletos lleva una cedula en una ruta
    private int getBoletosPorCedula(String rutaNombre, String cedula) {
        int total = 0;
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getRutaNombre().equals(rutaNombre) && c.getCedula().equals(cedula)) {
                total += c.getCantidad();
            }
        }
        return total;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }
}