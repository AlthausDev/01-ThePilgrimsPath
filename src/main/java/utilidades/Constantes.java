package utilidades;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Constantes {
	public final static String PATH_CREDENTIALS = "src\\main\\java\\archivos\\credenciales.txt";
	public final static String PATH_STOPS = "src\\main\\java\\archivos\\paradas.dat";
	public final static String PATH_COUNTRIES = "src\\main\\resources\\paises.xml";
	public final static String PATH_EXPORTS = "src\\main\\java\\archivos\\xml\\";
	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}