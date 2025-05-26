
import org.json.JSONObject;
import org.json.JSONTokener;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Admisiones extends JFrame{
  
  private final Map<String, JTextField> campos = new LinkedHashMap<String, JTextField>();
  private double intercepto;
  private final Map<String, Double> coeficientes = new LinkedHashMap<String, Double>();

  public Admisiones(){
    setTitle("Predicción de Admisión Universitaria");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    
    //Imagen decorativa
    ImageIcon imagen = redimensionarImagen("banner.png", 400, 100);
    JLabel imagenLabel = new JLabel(imagen);
    add(imagenLabel, BorderLayout.NORTH);
  
    //Panel de entrada de datos
    JPanel panelEntrada = new JPanel(new GridLayout(0, 2));
    String[] caracteristicas = {"GRE Score", "TOEFL Score", "University Rating", "SOP", "LOR", "CGPA", "Research"};
    for(String caracteristica : caracteristicas){
      panelEntrada.add(new JLabel(caracteristica));
      JTextField campo = new JTextField();
      campos.put(caracteristica, campo);
      panelEntrada.add(campo);
    }
    add(panelEntrada, BorderLayout.CENTER);

    //Botón y etiqueta de resultado
    JButton botonPrediccion = new JButton("Predicción");
    JLabel etiquetaResultado = new JLabel(" ", SwingConstants.CENTER);
    etiquetaResultado.setFont(new Font("Times New Roman", Font.BOLD, 16));

    botonPrediccion.addActionListener(e -> {
      double prediccion = intercepto;
      for(Map.Entry<String, JTextField> entrada : campos.entrySet()){
        try{
          double valor = Double.parseDouble(entrada.getValue().getText());
          prediccion += valor * coeficientes.getOrDefault(entrada.getKey(), 0.0);
        } catch (NumberFormatException excepcion){
          JOptionPane.showMessageDialog(this, "Ingresa valores numéricos válidos para todos los campos.");
          return;
        }
      }
      String resultado = prediccion >= 0.80 ? "Admitido" : "No Admitido";
      etiquetaResultado.setText(String.format("Probabilidad: %.4f → %s", prediccion, resultado));
    });

    //Panel inferior con botón y resultado
    JPanel panelInferior = new JPanel(new BorderLayout());
    panelInferior.add(botonPrediccion, BorderLayout.NORTH);
    panelInferior.add(etiquetaResultado, BorderLayout.CENTER);
    add(panelInferior, BorderLayout.SOUTH);

    //Cargar coeficientes del modelo
    cargarModeloRegresion("modelo_parametros_grupoX.json");

    //Configuración final
    setSize(420, 400);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void cargarModeloRegresion(String ruta){
    try(InputStream is = new FileInputStream(ruta)){
      JSONObject json = new JSONObject(new JSONTokener(is));
      intercepto = json.getDouble("intercept");
      JSONObject cfs = json.getJSONObject("coefficients");
      for(String key : cfs.keySet()){
        coeficientes.put(key, cfs.getDouble(key));
      }
    } catch (Exception e){
      JOptionPane.showMessageDialog(this, "Error al cargar el archivo JSON: " + e.getMessage());
    }
  }

  public ImageIcon redimensionarImagen(String ruta, int ancho, int alto){
    ImageIcon icono = new ImageIcon(ruta);
    Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(img);
  }
}
