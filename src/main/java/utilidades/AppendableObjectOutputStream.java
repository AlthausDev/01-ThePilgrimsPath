package utilidades;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Esta clase es una extensión de ObjectOutputStream que permite escribir objetos en un flujo de salida
 * con la capacidad de anexar datos a un archivo existente sin sobrescribirlo.
 */
public class AppendableObjectOutputStream extends ObjectOutputStream {

    private boolean append;
    private boolean initialized;
    private DataOutputStream dout;

    /**
     * Constructor de la clase.
     *
     * @param append Define si se debe anexar al archivo existente (true) o sobrescribirlo (false).
     * @throws IOException            Si ocurre un error de entrada/salida.
     * @throws SecurityException      Si existe una violación de seguridad.
     */
    protected AppendableObjectOutputStream(boolean append) throws IOException, SecurityException {
        super();
        this.append = append;
        this.initialized = true;
    }

    /**
     * Constructor de la clase que recibe un flujo de salida y define si se debe anexar al archivo existente.
     *
     * @param out    El flujo de salida en el que se escribirán los objetos.
     * @param append Define si se debe anexar al archivo existente (true) o sobrescribirlo (false).
     * @throws IOException            Si ocurre un error de entrada/salida.
     */
    public AppendableObjectOutputStream(OutputStream out, boolean append) throws IOException {
        super(out);
        this.append = append;
        this.initialized = true;
        this.dout = new DataOutputStream(out);
        this.writeStreamHeader();
    }

    /**
     * Escribe la cabecera del flujo de objetos si no se ha inicializado previamente y no se está anexando.
     *
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void writeStreamHeader() throws IOException {
        if (!this.initialized || this.append) return;
        if (dout != null) {
            dout.writeShort(STREAM_MAGIC);
            dout.writeShort(STREAM_VERSION);
        }
    }
}
