public class Jugador {
    private int identificador;
    private String nombre;
    private String posicion;
    private float rendimiento;
    private int edad;

    public Jugador(int identificador, String nombre, String posicion, float rendimiento, int edad) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.posicion = posicion;
        this.rendimiento = rendimiento;
        this.edad = edad;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public float getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(float rendimiento) {
        this.rendimiento = rendimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugador " +
                "ID: " + identificador +
                ", Nombre: " + nombre +
                ", Posicion: " + posicion +
                ", Rendimiento: " + rendimiento +
                ", Edad: " + edad;
    }
}
