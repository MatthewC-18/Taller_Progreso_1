package modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorVentas {

    private final List<Compra> compras = new ArrayList<>();

    public Compra registrarCompra(Ruta ruta, String cedula, String nombre, int cantidad) {
        validarDatos(ruta, cedula, nombre, cantidad);
        validarCapacidad(ruta, cantidad);
        validarCedulaEnRuta(ruta, cedula, cantidad);

        Compra compra = new Compra(ruta, cedula.trim(), nombre.trim(), cantidad);
        compras.add(compra);
        return compra;
    }

    // ---------- Validaciones ----------

    private void validarDatos(Ruta ruta, String cedula, String nombre, int cantidad) {
        if (ruta == null) {
            throw new IllegalArgumentException("Debe seleccionar una ruta.");
        }
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía.");
        }
        if (!cedula.trim().matches("\\d{10}")) {
            throw new IllegalArgumentException("La cédula debe tener 10 dígitos numéricos.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (cantidad < 1 || cantidad > Ruta.MAX_BOLETOS_POR_CEDULA) {
            throw new IllegalArgumentException(
                    "La cantidad debe estar entre 1 y " + Ruta.MAX_BOLETOS_POR_CEDULA + " boletos.");
        }
    }

    private void validarCapacidad(Ruta ruta, int cantidad) {
        int disponibles = getBoletosDisponibles(ruta);
        if (cantidad > disponibles) {
            throw new IllegalArgumentException(
                    "No hay suficientes asientos disponibles en " + ruta.getNombre()
                            + ". Disponibles: " + disponibles);
        }
    }

    private void validarCedulaEnRuta(Ruta ruta, String cedula, int cantidad) {
        int yaComprados = getBoletosComprados(ruta, cedula.trim());
        if (yaComprados + cantidad > Ruta.MAX_BOLETOS_POR_CEDULA) {
            throw new IllegalArgumentException(
                    "La cédula " + cedula.trim() + " ya tiene " + yaComprados
                            + " boleto(s) en esta ruta. Máximo permitido: "
                            + Ruta.MAX_BOLETOS_POR_CEDULA + ".");
        }
    }


    public int getBoletosVendidos(Ruta ruta) {
        return compras.stream()
                .filter(c -> c.getRuta() == ruta)
                .mapToInt(Compra::getCantidad)
                .sum();
    }

    public int getBoletosDisponibles(Ruta ruta) {
        return Ruta.CAPACIDAD_MAXIMA - getBoletosVendidos(ruta);
    }

    public double getTotalRecaudado() {
        return compras.stream()
                .mapToDouble(Compra::getPrecioTotal)
                .sum();
    }

    private int getBoletosComprados(Ruta ruta, String cedula) {
        return compras.stream()
                .filter(c -> c.getRuta() == ruta && c.getCedula().equalsIgnoreCase(cedula))
                .mapToInt(Compra::getCantidad)
                .sum();
    }

    public List<Compra> getCompras() {
        return new ArrayList<>(compras);
    }
}