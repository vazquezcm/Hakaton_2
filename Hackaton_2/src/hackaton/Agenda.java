package hackaton;
import java.util.ArrayList;
import java.util.Scanner;
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

    public boolean agendaVacia(){
        boolean banderaAgenda = false;
        if (cantidadActual==0){
            banderaAgenda = true;
            return banderaAgenda;
        } else {
            return banderaAgenda;
        }
    }


    public void buscarContacto(String nombre, String apellido) {
        for (Contacto contacto : contactos) {
            if (contacto != null) {
                if (contacto.getNombre().equalsIgnoreCase(nombre)
                        && contacto.getApellido().equalsIgnoreCase(apellido)) {
                    System.out.println("Contacto encontrado. Teléfono: " + contacto.getTelefono());
                    return;
                }
            }
        }
        System.out.println("Contacto '" + nombre + " " + apellido + "' no encontrado.");
    }

    public void eliminarContacto(String nombre, String apellido) {
        for (int i = 0; i < cantidadActual; i++) {
            if(contactos[i].getNombre().equals(nombre) && contactos[i].getApellido().equals(apellido)){
                for (int j = i; j < cantidadActual - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                contactos[cantidadActual - 1] = null;
                cantidadActual--;
                System.out.println("Se elimino a " + nombre +" "+ apellido);
                return;
            }

        }
        System.out.println("No se encontro contacto");
    }

    public void modificarTelefono(String nombre, String apellido){
        Scanner scan = new Scanner(System.in);
        String telefonoNew;
        for (Contacto contacto : contactos) {
            if (contacto != null) {
                if (contacto.getNombre().equalsIgnoreCase(nombre)
                        && contacto.getApellido().equalsIgnoreCase(apellido)) {
                    System.out.println("Va a cambiar el telefono del siguiente contacto = " + contacto.getNombre() + " "+contacto.getApellido());
                    System.out.println("¿Cuál va a ser su nuevo número telefónico?");
                    telefonoNew = scan.nextLine();
                    contacto.setTelefono(telefonoNew);
                    return;
                }
            }
        }
    }

}
