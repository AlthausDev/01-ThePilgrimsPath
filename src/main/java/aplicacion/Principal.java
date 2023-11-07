package aplicacion;

import entities.AdminParada;
import entities.Parada;

import static entities.Perfil.ADMIN_PARADA;
import static io.Escritor.writeCredencial;
import static io.Escritor.writeParada;

/**
 * Esta es la clase principal de la aplicación. Aquí se inicia la ejecución del programa.
 *
 * @author S.Althaus
 *
 * */
public class Principal {
	/**
	 * El método principal de la aplicación. Se llama al iniciar la ejecución del programa.
	 *
	 * @param args Los argumentos de la línea de comandos (no se utilizan en esta aplicación).
	 */
	public static void main(String[] args) {
		// Creamos una nueva instancia de la clase Sesion para iniciar la aplicación
		new Sesion();

		AdminParada adminParada = new AdminParada(1, "Juan");
		Parada nuevaParada = new Parada(1, "aviles", 'A', adminParada);

		writeCredencial("Juan", "123", ADMIN_PARADA, 1);
		writeParada(nuevaParada);
//
//		AdminParada adminParada = new AdminParada(2, "Pedro");
//		Parada nuevaParada = new Parada(2, "madrid", 'M', adminParada);
//
//		writeCredencial("Pedro", "652", ADMIN_PARADA, 2);
//		writeParada(nuevaParada);
	}
}

