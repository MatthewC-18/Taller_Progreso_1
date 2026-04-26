package modelo;

public class Ruta {

    // Constantes para no repetir strings por todo el código
    public static final String GUAYAQUIL = "Quito - Guayaquil";
    public static final String CUENCA = "Quito - Cuenca";
    public static final String LOJA = "Quito - Loja";

    public static final int CAPACIDAD = 20;
    public static final int MAX_BOLETOS = 5;

    private String nombre;
    private double precio;

    public Ruta(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // Devuelve las 3 rutas disponibles para llenar el ComboBox
    public static Ruta[] getRutas() {
        Ruta[] rutas = new Ruta[3];
        rutas[0] = new Ruta(GUAYAQUIL, 10.50);
        rutas[1] = new Ruta(CUENCA, 12.75);
        rutas[2] = new Ruta(LOJA, 15.00);
        return rutas;
    }

    @Override
    public String toString() {
        return nombre;
    }
}