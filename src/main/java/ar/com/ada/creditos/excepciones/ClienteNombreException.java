package ar.com.ada.creditos.excepciones;
import ar.com.ada.creditos.entities.*;

public class ClienteNombreException extends ClienteInfoException {
    
    public ClienteNombreException(Cliente cliente, String mensaje){
        super(cliente, mensaje);
    }

}

