package controllers;

import models.PersonaModel;

/**
 *
 * @author Diegomrza
 */
public class AutenticacionController {

    // Declaramos static para que los valores perseveren por toda la ejecucion del programa
    private static int Contador = 0;
    private static final PersonaModel[] Usuarios = new PersonaModel[100];
    
    public AutenticacionController() {
    }
    
    private PersonaModel ExisteUsuario(String usuario) {
        // Si solo se pueden registrar pero no eliminar mejor hasta el contador, asi no recorre todos
        for (int i = 0; i < Contador; i++) {
            if (Usuarios[i].getUsuario().equals(usuario)) {
                
                return Usuarios[i];
            }
        }
        return null;
    }
    
    public boolean RegistrarPersona(String usuario, String password, int edad, String carnet) {
        // Buscamos al usuario
        PersonaModel usuarioEncontrado = this.ExisteUsuario(usuario);
        
        // Si el usuario ya existe retornamos falso
        if (usuarioEncontrado == null) {
            return false;
        }
        
        // Contrasena mayor a 5 caracteres y que no sea vacia
        if (password.trim().length() <= 5) {
            return false;
        }
        
        // Ya no hay espacios disponibles
        if (Contador > Usuarios.length - 1) {
            return false;
        }
        
        // En la posicion actual ingresamos al usuario y como id le mandamos el actual mas uno porque empieza en 0
        Usuarios[Contador] = new PersonaModel(Contador+1, usuario, password, edad, carnet);
        Contador++;
        return true;
    }
    
    public boolean InicioSesion(String usuario, String password) {
        PersonaModel usuarioEncontrado = this.ExisteUsuario(usuario);
        
        // Si no lo encuentra retornamos falso
        if (usuarioEncontrado == null) {
            return false;
        }
        
        // Si lo encuentra comparamos si la contrasena es la misma
        return usuarioEncontrado.getPassword().equals(password);
    }
    
}
