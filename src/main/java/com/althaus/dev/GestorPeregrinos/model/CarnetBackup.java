package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "carnet_backup")
public class CarnetBackup {

    /**
     * Nombre del archivo de backup, utilizado como identificador.
     */
    private String fileName;

    /**
     * Lista de carnets almacenados en el backup.
     */
    private List<String> carnets;

    /**
     * Constructor por defecto.
     */
    public CarnetBackup() {
    }

    /**
     * Constructor que inicializa el nombre del archivo y la lista de carnets.
     *
     * @param fileName Nombre del archivo de backup.
     * @param carnets Lista de carnets a almacenar en el backup.
     */
    public CarnetBackup(String fileName, List<String> carnets) {
        this.fileName = fileName;
        this.carnets = carnets;
    }

}
