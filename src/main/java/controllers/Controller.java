package controllers;

import entities.Usuario;
import java.util.*;

public class Controller {
    protected static LinkedHashMap<Long, Usuario> mapaUsuarios = new LinkedHashMap<>();
    protected static Long count = 0L;
}
