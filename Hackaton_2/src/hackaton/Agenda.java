package hackaton;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    // Atributos de la clase
    private Contacto[] contactos;       // Arreglo para almacenar los contactos
    private int capacidad;              // Capacidad máxima de la agenda
    private int cantidadActual;         // Cantidad actual de contactos almacenados

    // Constructor con parámetros para todos los atributos
    public Agenda(Contacto[] contactos, int capacidad, int cantidadActual) {
        this.contactos = contactos;
        this.capacidad = capacidad;
        this.cantidadActual = cantidadActual;
    }

    // Constructor principal que recibe el tamaño de la agenda
    public Agenda(int tamaño) {
        this.capacidad = tamaño;
        this.contactos = new Contacto[tamaño];  // Inicializa el arreglo de contactos
        this.cantidadActual = 0;                // Inicia con 0 contactos
    }

    // Métodos getters y setters para los atributos
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

    // Método para agregar un nuevo contacto a la agenda
    public void agregarContacto(Contacto c){
        contactos[cantidadActual] = c;  // Agrega el contacto en la posición actual
        cantidadActual++;               // Incrementa el contador de contactos
        System.out.println("Contacto registrado! " + c);
    }

    // Método para mostrar todos los contactos almacenados
    public void mostrarContactos() {
        for (int i = 0; i < cantidadActual; i++) {
            // Imprime los datos de cada contacto
            System.out.println("Nombre: " + contactos[i].getNombre() +
                    ", Apellido: " + contactos[i].getApellido() +
                    ", Teléfono: " + contactos[i].getTelefono());
        }
    }

    // Método para verificar si la agenda está llena
    public boolean agendaLena(){
        boolean banderaAgenda = false;
        if (cantidadActual==capacidad){
            banderaAgenda = true;
            return banderaAgenda;
        } else {
            return banderaAgenda;
        }
    }

    // Método para verificar si la agenda está vacía
    public boolean agendaVacia(){
        boolean banderaAgenda = false;
        if (cantidadActual==0){
            banderaAgenda = true;
            return banderaAgenda;
        } else {
            return banderaAgenda;
        }
    }

    // Método para buscar un contacto por nombre y apellido
    public void buscarContacto(String nombre, String apellido) {
        for(Contacto contacto : this.contactos) {
            if (contacto != null && contacto.getNombre().equalsIgnoreCase(nombre)
                    && contacto.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Contacto encontrado. Teléfono: " + contacto.getTelefono());
                return;
            }
        }
        System.out.println("Contacto '" + nombre + " " + apellido + "' no encontrado.");
    }

    // Método para validar si un contacto ya existe (duplicado)
    public boolean validarDuplicado(String nombre, String apellido) {
        boolean duplicado = false;
        for (Contacto contacto : contactos) {
            if (contacto != null) {
                // Compara nombre y apellido (ignorando mayúsculas/minúsculas)
                if (contacto.getNombre().equalsIgnoreCase(nombre)
                        && contacto.getApellido().equalsIgnoreCase(apellido)) {
                    System.out.println("Contacto duplicado! " + contacto);
                    duplicado = true;
                    return duplicado;
                }
            }
        }
        return duplicado;
    }

    // Método para buscar un contacto completo (nombre, apellido y teléfono)
    public void buscarContactoCompleto(String nombre, String apellido, String telefono) {
        for (Contacto contacto : contactos) {
            if (contacto != null) {
                // Compara los tres campos (ignorando mayúsculas/minúsculas)
                if (contacto.getNombre().equalsIgnoreCase(nombre)
                        && contacto.getApellido().equalsIgnoreCase(apellido)
                        && contacto.getTelefono().equalsIgnoreCase(telefono)) {
                    System.out.println("Contacto encontrado: " + contacto);
                    return;
                }
            }
        }
        System.out.println("Contacto '" + nombre + " " + apellido + " " + telefono + "' no encontrado.");
    }

    // Método para eliminar un contacto de la agenda
    public void eliminarContacto(String nombre, String apellido) {
        for (int i = 0; i < cantidadActual; i++) {
            if(contactos[i].getNombre().equals(nombre) && contactos[i].getApellido().equals(apellido)){
                // Desplaza los contactos para llenar el espacio vacío
                for (int j = i; j < cantidadActual - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                contactos[cantidadActual - 1] = null;  // Elimina la última referencia
                cantidadActual--;                       // Reduce el contador de contactos
                System.out.println("Se elimino a " + nombre +" "+ apellido);
                return;
            }
        }
        System.out.println("No se encontro contacto");
    }

    // Método para modificar el teléfono de un contacto existente
    public void modificarTelefono(String nombre, String apellido){
        Scanner scan = new Scanner(System.in);
        String telefonoNew;
        for (Contacto contacto : contactos) {
            if (contacto != null) {
                if (contacto.getNombre().equalsIgnoreCase(nombre)
                        && contacto.getApellido().equalsIgnoreCase(apellido)) {
                    System.out.println("Va a cambiar el telefono del siguiente contacto = "
                            + contacto.getNombre() + " "+contacto.getApellido());

                        System.out.println("¿Cuál va a ser su nuevo número telefónico?");
                    do {
                        telefonoNew = scan.nextLine();
                        if (telefonoNew.contains(" ")) {
                            System.out.println("El teléfono no debe contener espacios. Inténtalo de nuevo.");
                        } else if (!telefonoNew.matches("\\d+")) {
                            System.out.println("El teléfono debe contener solo números. Inténtalo de nuevo.");
                        }
                    } while (telefonoNew.contains(" ") || !telefonoNew.matches("\\d+"));
                    contacto.setTelefono(telefonoNew);  // Actualiza el teléfono
                    System.out.println("Teléfono cambiado exitosamente! ");
                    return;
                }
            }
        }
    }
}

