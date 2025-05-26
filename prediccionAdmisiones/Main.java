public class Main{
  public static void main(String[] args){
    SwingUtilities.invokeLater(Admisiones::new); //ejecuta la aplicación de forma segura en el hilo de eventos de Swing y crea una nueva instancia de Admisiones
  }
}