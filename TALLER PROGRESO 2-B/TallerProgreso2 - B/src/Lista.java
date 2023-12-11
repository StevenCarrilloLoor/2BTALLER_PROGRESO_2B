import java.util.ArrayList;
import java.util.List;

public class Lista {

    List<Jugador> equipo;

    public Lista(){
        equipo=new ArrayList<Jugador>();
        predefinir();
    }

    public void predefinir(){
        equipo.add(new Jugador(1,"Steven","1",30f,18));
        equipo.add(new Jugador(2,"Daniela","2",35f,20));
        equipo.add(new Jugador(3,"Juan","3",36,30));
        equipo.add(new Jugador(4,"Jose","4",40f,25));
    }


    public void agregar(Jugador dato){
        equipo.add(dato);
    }

    public boolean actualizar (Jugador dato){

        for(Jugador j:equipo){
            if(j.getIdentificador()==dato.getIdentificador()){
                j.setNombre(dato.getNombre());
                j.setEdad(dato.getEdad());
                j.setRendimiento(dato.getRendimiento());
                j.setPosicion(dato.getPosicion());
                return true;
            }
        }
        return false;
    }

    public List<Jugador> getEquipo() {
        return equipo;
    }

    private float sumatoria(int indice, String posicion){
        if(indice<equipo.size()){
            //caso general
            if(equipo.get(indice).getPosicion().compareTo(posicion)==0)
               return equipo.get(indice).getRendimiento()+sumatoria(indice+1,posicion);
            else
                return sumatoria(indice+1,posicion);
        }else{
            //caso base
            return 0;
        }
    }

    public boolean verificarID(int tracking){
        int i =0;
        int s=equipo.size()-1;
        int c=0;
        while((i<=s)){
            c=(i+s)/2;
            Jugador aux=equipo.get(c);
            if(tracking==aux.getIdentificador()){
                return false;
            } else if(tracking<aux.getIdentificador()){
                s=c-1;
            }else{
                i=c+1;
            }
        }
        return true;
    }
    public float suma(String posicion){
        return sumatoria(0,posicion);
    }

    //VERIFICAR CASOS VACIOS Y RANGOS ===================================================

    public boolean verificadorRangos(float rendimiento, int edad) {
        if (rendimiento >= 1 && rendimiento <= 40 && edad >= 18 && edad <= 37) {
            return true;
        }
        return false;
    }


    //VERIFICAR MENOR RENDIMIENTO ==========================================000

    public void eliminarJugador() throws Exception {
        if (equipo.isEmpty()) {
            throw new Exception("Esta vacío, no se puede eliminar a nadie :(.");
        }
        float menorRendimiento = equipo.get(0).getRendimiento();
        for (Jugador j : equipo) {
            if (j.getRendimiento() < menorRendimiento) {
                menorRendimiento = j.getRendimiento();
            }
        }
        int contador = 0;
        for (Jugador j : equipo) {
            if (j.getRendimiento() == menorRendimiento) {
                contador++;
            }
        }
        if (contador == 1) {
            for (int i = 0; i < equipo.size(); i++) {
                if (equipo.get(i).getRendimiento() == menorRendimiento) {
                    equipo.remove(i);
                }
            }
        } else {
            throw new Exception("EXISTE" + contador + " jugadores con el mismo rendimiento mínimo.");
        }
    }
    private int contarMenores20(int indice) {
        if (indice < equipo.size()) {
            if (equipo.get(indice).getRendimiento() < 20) {
                return 1 + contarMenores20(indice + 1);
            } else {
                return contarMenores20(indice + 1);
            }
        } else {
            return 0;
        }
    }
    public int contarMenoress20() {
        return contarMenores20(0);
    }




}
