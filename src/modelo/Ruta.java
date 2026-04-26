package modelo;

public enum Ruta {
    QUITO_GUAYAQUIL("Quito - Guayaquil", 10.50),
    QUITO_CUENCA("Quito - Cuenca", 12.75),
    QUITO_LOJA("Quito - Loja", 15.00);

    public static final int CAPACIDAD_MAXIMA = 20;
    public static final int MAX_BOLETOS_POR_CEDULA = 5;

    private final String nombre;
    private final double precio;

    Ruta(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() { return nombre; }
}