package entities;

import java.util.Scanner;

public class Registrar {

    private Scanner sc = new Scanner(System.in);
    public void registrar() {
        if (Sesion.perfil == null) {
            nuevoPeregrino();
        } else {
            nuevaParada();
        }
    }


    public void nuevoPeregrino() {
        System.out.println("Registrar nuevo usuario");
        System.out.println("Indique su nombre");
        String nombre = sc.nextLine();

        System.out.println("Indique una contraseña");
        String pass = sc.nextLine();
        do {
            System.out.println("Repita su contraseña");
        } while (!sc.nextLine().equals(pass));
    }

    private void nuevaParada() {
    }
}
