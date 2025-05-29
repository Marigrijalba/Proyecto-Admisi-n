import org.json.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Admisiones extends JFrame{
  
  private final Map<String, JTextField> campos = new LinkedHashMap<String, JTextField>(); //guarda campos de entrada
  private double intercepto;
  private final Map<String, Double> coeficientes = new LinkedHashMap<String, Double>(); //coeficientes del modelo de regresión para cada variable
  private final JLabel etiquetaResultado = new JLabel(" ", SwingConstants.CENTER); //etiqueta para mostrar el resultado


  public Admisiones(){
    setTitle("Predicción de Admisión Universitaria");
    setDefaultCloseOperation(EXIT_ON_CLOSE); // cierra la app al cerrar la ventana
    setLayout(new BorderLayout()); //establece el diseño principal de la ventana por zonas
    getContentPane().setBackground(Color.decode("#f9f9f9")); //fondo claro
    
    //Imagen decorativa
    ImageIcon imagen = redimensionarImagen("banner.jpg", 400, 100);
    JLabel etiquetaImagen = new JLabel(imagen);
    add(etiquetaImagen, BorderLayout.NORTH);
  
    //Panel central con campos de entrada
    JPanel panelEntrada = new JPanel(new GridBagLayout()); //mejor alineación y espaciado
    panelEntrada.setOpaque(false);
    GridBagConstraints gbc = new GridBagConstraints(); //configura como se posicionan los elementos
    gbc.insets = new Insets(8, 10, 8, 10) ; //espaciado interno entre los componentes
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0; //ajuste proporcional
    
    String[] caracteristicas = {"GRE Score", "TOEFL Score", "University Rating", "SOP", "LOR", "CGPA", "Research"};
    Font fuente = new Font("Segoe UI", Font.PLAIN, 14);

    int fila = 0;
    for(String caracteristica : caracteristicas){
      gbc.gridx = 0;
      gbc.gridy = fila;
      JLabel etiqueta = new JLabel(caracteristica + ":"); //etiqueta descriptiva
      etiqueta.setFont(fuente);
      panelEntrada.add(etiqueta, gbc);

      gbc.gridx = 1; //campo de entrada en columna 1
      JTextField campo = new JTextField(10); //campo de entrada
      campo.setFont(fuente);
      campos.put(caracteristica, campo); //guarda el campo en el mapa usando el nombre como clave
      panelEntrada.add(campo, gbc);
      fila++;
    }
    add(panelEntrada, BorderLayout.CENTER);

    //Botón
    JButton botonPrediccion = new JButton("Predicción");
    botonPrediccion.setBackground(new Color(30, 144, 225)); //fondo azul brillante
    botonPrediccion.setForeground(Color.WHITE); //letras blancas
    botonPrediccion.setFocusPainted(false);
    botonPrediccion.setFont(new Font("Segoe UI", Font.BOLD, 14));

    botonPrediccion.addActionListener(e -> {

      Map<String, double[]> rangos = Map.of( //definición de los rangos permitidos para cada campo
        "GRE Score", new double[]{260, 340},
        "TOEFL Score", new double[]{0, 120},
        "University Rating", new double[]{1,5},
        "SOP", new double[]{1, 5},
        "LOR", new double[]{1, 5},
        "CGPA", new double[]{1, 10},
        "Research", new double[]{0, 1}
      );

      double prediccion = intercepto;

      for(Map.Entry<String, JTextField> entrada : campos.entrySet()){
        String clave = entrada.getKey();
        String texto = entrada.getValue().getText();

        try{
          double valor = Double.parseDouble(texto);
          double min = rangos.get(clave)[0];
          double max = rangos.get(clave)[1];

          if(clave.equals("Research")){
            if(valor != 0 && valor != 1){
              JOptionPane.showMessageDialog(this, "El valor de \"Research\" debe ser 0 (no tiene experiencia) o 1 (sí tiene).", "Valor inválido", JOptionPane.WARNING_MESSAGE);
              return;
            }
          } else if (valor < min || valor > max){
            JOptionPane.showMessageDialog(this, "El valor de \"" + clave + "\" debe estar entre " + min + " y " + max + ".", "Valor fuera de rango", JOptionPane.WARNING_MESSAGE);
            return;
          }

          prediccion += valor * coeficientes.getOrDefault(clave, 0.0); //suma el valor del intercepto y multiplica cada entrada por su coeficiente
        } catch (NumberFormatException excepcion){
          JOptionPane.showMessageDialog(this, "Por favor ingresa un valor válido en \"" + clave + "\".", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
          return;
        }
      }
      String resultado = prediccion >= 0.80 ? "Admitido" : "No Admitido";
      etiquetaResultado.setText(String.format("Probabilidad: %.2f → %s", prediccion, resultado));
    });

    //Panel inferior con boton y resultado
    JPanel panelInferior = new JPanel(new BorderLayout());
    panelInferior.setOpaque(false);
    panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    etiquetaResultado.setFont(new Font("Segoe UI", Font.BOLD, 16));
    etiquetaResultado.setOpaque(false);
    panelInferior.add(botonPrediccion, BorderLayout.NORTH);
    panelInferior.add(etiquetaResultado, BorderLayout.CENTER);
    add(panelInferior, BorderLayout.SOUTH);

    //Cargar coeficientes del modelo
    cargarModeloRegresion("modelo_parametros_grupoX.json");

    pack(); //ajusta automáticamente el contenido
    setResizable(false); //no permite redimensionar
    setLocationRelativeTo(null); //centra la ventana en pantalla
    setVisible(true); //visibilidad
  }

  public void cargarModeloRegresion(String ruta){
    try(InputStream is = new FileInputStream(ruta)){
      JSONObject json = new JSONObject(new JSONTokener(is)); //lectura del archivo json
      intercepto = json.getDouble("intercept");
      JSONObject cfs = json.getJSONObject("coefficients");
      for(String key : cfs.keySet()){
        coeficientes.put(key, cfs.getDouble(key)); //llena el mapa de coeficientes
      }
    } catch (Exception e){
      JOptionPane.showMessageDialog(this, "Error al cargar el archivo JSON: " + e.getMessage());
    }
  }

  public ImageIcon redimensionarImagen(String ruta, int ancho, int alto){
    File archivo = new File(ruta);
    if(!archivo.exists()){
      System.out.println("Imagen no encontrada: " + ruta);
    }
    ImageIcon icono = new ImageIcon(ruta);
    Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(img); //imagen redimensionada
  }
}
