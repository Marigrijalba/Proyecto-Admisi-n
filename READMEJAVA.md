# Simulador de Admisión Universitaria (Java Swing)

Este proyecto es una aplicación en Java que permite predecir la probabilidad de ser admitido a una universidad con base en un modelo de regresión lineal entrenado previamente. Dicho modelo funciona con parámetros cargados desde un archivo JSON, y la interfaz se muestra con un diseño modern.


## Estructura del Proyecto
El repositorio contiene los siguientes archivos
```bash
JAVA/
├── Admisiones.java                  # Código fuente
├── modelo_parametros_grupoX.json    # Archivo JSON con intercepto y coeficientes
├── banner.jpg                       # Imagen decorativa para la interfaz
├── flatlaf.jar                      # Librería para estilo moderno FlatLaf
├── json.jar                         # Librería para leer JSON
└── Main.java
```                      

## Requisitos Previos

- Java JDK 8 o superior
- VS Code, o cualquier terminal con compilador Java

Verificar instalación con:
```bash
java -version
javac -version
```

## Compilación
Desde la terminal se compila el programa con el siguiente código en el bash.

Windows:
```bash
javac -cp ".;flatlaf.jar;json.jar" Admisiones.java Main.java
```
Linux o macOS:
```bash
javac -cp ".:flatlaf.jar:json.jar" Admisiones.java Main.java
```

## Ejecución
Una vez compilado, puedes ejecutar el programa con
```bash
java -cp ".;flatlaf.jar;json.jar" Main
```

## Funcionalidad

1. Carga el archivo modelo_parametros_grupoX.json:

   json
   {
     "intercept": -1.2594,
     "coefficients": {
       "GRE Score": 0.0023,
       "TOEFL Score": 0.0034,
       ...
     }
   }
   

2. Muestra una interfaz con los siguientes campos (delimitados por los rangos mencionados)

   - GRE Score: 260 – 340
   - TOEFL Score: 0 – 120
   - University Rating: 1 – 5
   - SOP: 1 – 5
   - LOR: 1 – 5
   - CGPA: 1 – 10
   - Research: solo 0 o 1

3. El usuario ingresa los valores numéricos válidos.

4. Al hacer clic en "Predicción", el programa:

   - Valida los valores y rangos
   - Aplica el modelo de regresión: prediccion = intercepto + a₁x₁ + a₂x₂ + ... + aₙxₙ


## Validaciones
- Solo se aceptan valores numéricos
- Cada campo tiene un rango permitido


## Estética
- Uso de librería externa FlatLaf para estilo visual moderno y claro
- Botón azul con texto blanco
- Imagen superior decorativa (banner.jpg)
- Fuentes limpias (Segoe UI)


## Autores y Créditos
Este simulador fue desarrollado por 
- Mariana Grijalba Mora
- Isabella Correa Jiménez
- Tomás Barbosa Rey
- Alexander Pedraza  Beltrán