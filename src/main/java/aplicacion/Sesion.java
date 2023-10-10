//package aplicacion;

//import entities.Perfil;
//
//public class Sesion {
//
//	private static long id;
//	public static Perfil perfil = null;
//}

package aplicacion;

import entities.Perfil;

public class Sesion {
	private static boolean sesionActiva = false;
	public static Perfil perfil = null;

	public static boolean iniciarSesion(Perfil usuarioPerfil) {
		if (!sesionActiva) {
			perfil = usuarioPerfil;
			sesionActiva = true;
			return true;
		}
		return false;
	}

	public static void cerrarSesion() {
		sesionActiva = false;
		perfil = null;
	}

	public static boolean isSesionActiva() {
		return sesionActiva;
	}

	public static Perfil getPerfil() {
		return perfil;
	}
}
