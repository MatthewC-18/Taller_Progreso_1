package modelo;

public class Compra {
    private final Ruta ruta;
    private final String cedula;
    private final String nombre;
    private final int cantidad;
    private final double precioTotal;

    public Compra(Ruta ruta, String cedula, String nombre, int cantidad) {
        this.ruta = ruta;
        this.cedula = cedula;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioTotal = cantidad * ruta.getPrecio();
    }

    public Ruta getRuta() { return ruta; }
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecioTotal() { return precioTotal; }

    @Override
    public String toString() {
        return String.format("Ruta: %s | Boletos: %d | Pasajero: %s | Total: $%.2f",
                ruta.getNombre(), cantidad, nombre, precioTotal);
    }
}