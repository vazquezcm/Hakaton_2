package hackaton;
import java.util.ArrayList;
public class Agenda {
   private Contacto[] contactos;
   private int capacidad;
   private int cantidadActual;



    public Agenda(Contacto[] contactos, int capacidad, int cantidadActual) {
        this.contactos = contactos;
        this.capacidad = capacidad;
        this.cantidadActual = cantidadActual;
    }

    public Agenda(int tamaño) {
        this.capacidad = tamaño;
        this.contactos = new Contacto[tamaño];
        this.cantidadActual = 0;
    }

    public Contacto[] getContactos() {
        return contactos;
    }

    public void setContactos(Contacto[] contactos) {
        this.contactos = contactos;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public void agregarContacto(Contacto c){
        contactos[cantidadActual] = c;
        cantidadActual++;
        System.out.println("Contacto registrado! " + c);
    }

    public void mostrarContactos() {
        for (int i = 0; i < cantidadActual; i++) {
            // Accedemos directamente a los campos del contacto
            System.out.println("Nombre: " + contactos[i].getNombre() +
                    ", Apellido: " + contactos[i].getApellido() +
                    ", Teléfono: " + contactos[i].getTelefono());
        }
    }

    public boolean agendaLena(){
        boolean banderaAgenda = false;
        if (cantidadActual==capacidad){
            banderaAgenda = true;
            return banderaAgenda;
        } else {
            return banderaAgenda;
        }
    }

}
