package com.althaus.dev.GestorPeregrinos.util;

import java.time.format.DateTimeFormatter;

/**
 * Esta clase define constantes utilizadas en la aplicación.
 * Proporciona rutas de archivos y un formateador de fecha común.
 */
public final class Constantes {

	/**
	 * Ruta del archivo que almacena información de paradas.
	 */
	public final static String PATH_STOPS = "src\\main\\java\\archivos\\paradas.dat";

	/**
	 * Ruta del archivo XML que contiene datos de países.
	 */
	public final static String PATH_COUNTRIES = "src\\main\\resources\\paises.xml";

	/**
	 * Ruta base para exportar archivos XML.
	 */
	public final static String PATH_EXPORTS = "src/main/java/com/althaus/dev/GestorPeregrinos/model/data/xml_exports/";

	/**
	 * Formateador de fecha utilizado en toda la aplicación.
	 */
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

}
