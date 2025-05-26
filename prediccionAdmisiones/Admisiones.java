import org.json.JSONObject; //reprenta un objeto JSON y permite acceder a los valores de coeficientes e intercepto
import org.json.JSONTockener; //conversión a datos legibles
import javax.swing.*; //proporciona componentes gráficos (GUI)
import java.awt.*; //manejo del diseño visual y organización de los componentes en pantalla
import java.util.*; //manejo de flujo de datos para lectura del archivo (FileInputStream e InputStream)
import java.io.*; //asociación de cada campo con su valor ingresado y su coeficiente correspondiente

public class Admisiones extends JFrame{
  
  private final Map<String, JTextFiel> campos = new LinkedHashMap<String, JTextFiel>(); //guarda los campos de texto donde el usuario va a ingresas los datos (por nombre de característica)
  private double intercepto;
  private final Map<String, Double> coeficientes = new LinkedHasMap<String, Double>(); //guarda los coeficientes de cada variable

  public Admisiones(){
    setTitle("Predicción de Admisión Universitaria");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new Borderlayout()); //organiza los componentes en 5 zonas(norte, sur, este, oeste y centro)
    
    //Imagen decorativa
    ImageIcon imagen = redimensionarImagen("banner.png", 400, 100);
    JLabel imagenLabel = new JLabel(imagen); //etiqueta
    add(imagenLabel, BorderLayout.NORTH); //parte superior
  
    //Panel de entrada de datos
    JPanel panelEntrada = new JPanel(new GrindLayout(0, 2)); //crea un pánel de 2 columnas (etiqueta y campo)
    String[] caracteristicas = {"GRE Score", "TOEFL Score", "University Rating", "SOP", "LOR", "CGPA", "Research"};
    for(String caracteristica : caracteristicas){
      inputPanel.add(new JLabel(caracteristica)); //muestra el nombre de la característica
      JTextField campo = new JTextField(); //campo de escritura
      campos.put(caracteristica, campo); //guarda el campo en el mapa
      inputPanel.add(campo); //agrega el campo al panel
    }
    add(inputPanel, BorderLayout.CENTER); //centro de la ventana

    //Botón y etiqueta de resultado
    JButton botonPrediccion = new JButton("Predicción"); //botón
    JLabel etiquetaResultado = new JLabel(" ", SwingConstants.CENTER); //etiqueta para mostrar el resultado centrado
    etiquetaResultado.setFont(new Font("Times New Roman", Font.BOLD, 16)); //fuente

    botonPrediccion.addActionListener(e -> { //acción del botón
      double prediccion = intercepto;
      for(Map.Entry<String, JTextField> entrada : campos.entrySet()){
        try{
          double valor = Double.parseDouble(entrada.getValue().getText()); //lee el valor ingresado
          prediccion += valor *coeficientes.getDefault(entrada.getKey(). 0.0); //aplica la fórmula
        } catch (NumberFormatException excepcion){
          JOptionPane.showMessageDialog(this, "Ingresa valores numéricos válidos para todos los campos.");
          return; //detiene si existe un error en la entrada
        }
      }
      String resultado = prediccion >= 0.80 ? "Admitido" : "No Admitido";
      etiquetaResultado.setText(String.format("Probabilidad: %.4f → %s", prediccion, resultado));
    });

    //Panel inferior con botón y resultado
    JPanel panelInferior = new JPanel(new BorderLayout());
    panelInferior.add(botonPrediccion, BorderLayout.NORTH);
    panelInferior.add(etiquetaResultado, Borderlayout.CENTER);
    add(panelInferior, BorderLayout.SOUTH); //panel inferior con botón y resultado

    //Cargar coeficientes del modelo
    cargarModeloRegresion("modelo_parametros_grupoX.json");

    //Configuración final
    setSize(420, 400); //tamaño de la ventana
    setLocationRelativeto(null); //centrar la ventana en la pantalla
    setVisible(true); //visibilidad
  }

  public void cargarModeloRegresion(String ruta){
    try(InputStream is = new InputStream(ruta)){
      JSONObject json = new JSONObject(new JSONTockener(is)); //abre el archivo JSON
      intercepto = json.getDouble("intercept"); //lee y guarda intercepto
      JSONObject cfs = json.getJSONObject("coefficients");
      for(String key = cfs.keySet()){
        coeficientes.put(key, cfs.getDouble(key)); //lee y guarda coeficientes
      }
    } catch (Exception e){
      JOptionPane.showMessageDialog(this, "Error al cargar el archivo JSON: " + e.getMessage());
    }
  }

  public ImageIcon redimensionarImagen(String ruta, int ancho, int alto){
    ImageIcon icono = new ImageIcon(ruta); //carga la imagen
    Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH); //redimensiona tamaño fijo
    return new ImageIcon(img);
  }
}

