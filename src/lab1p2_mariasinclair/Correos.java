
package lab1p2_mariasinclair;

import java.util.Date;


public class Correos {
    
   private String ncompleto;
   private String nacimiento; 
   private String correo;
   private String contraseña;

    public Correos(String ncompleto, String nacimiento, String correo, String contraseña) {
        this.ncompleto = ncompleto;
        this.nacimiento = nacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNcompleto() {
        return ncompleto;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public String toString() {
        return "Nombre Completo: =" + ncompleto + "\nFecha de Nacimiento=" + nacimiento + "\n Corrreo Electronico:" + correo + "\nContraseña:" + contraseña + '}';
    }
  
    
    
      }

