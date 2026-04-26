package modelo;

public class Compra {

    private String rutaNombre;
    private String cedula;
    private String nombre;
    private int cantidad;
    private double precioTotal;

    public Compra(String rutaNombre, String cedula, String nombre, int cantidad, double precioUnitario) {
        this.rutaNombre = rutaNombre;
        this.cedula = cedula;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioTotal = cantidad * precioUnitario;
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    @Override
    public String toString() {
        return "Ruta: " + rutaNombre + " | Boletos: " + cantidad
                + " | Pasajero: " + nombre + " | Total: $" + precioTotal;
    }
}