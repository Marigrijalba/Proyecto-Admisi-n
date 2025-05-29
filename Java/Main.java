
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf; 


public class Main{
  public static void main(String[] args){
    try{
      UIManager.setLookAndFeel(new FlatLightLaf()); //Activar diseño moderno con FlatLaf
    } catch (Exception e){
      System.err.println("Error al aplicar FlatLaf");
    }
    SwingUtilities.invokeLater(Admisiones::new); //ejecuta la app en el hilo de interfaz
  }
}
