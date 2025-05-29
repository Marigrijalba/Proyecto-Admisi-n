# Proyecto de Análisis de Datos para Admisión Universitaria (C++)

Este proyecto en C++ permite la utilización de un conjunto datos de admisión universitaria en un archivo binario. El sistema lee un archivo CSV, transforma los datos en estructuras, genera un archivo binario, calcula estadísticas descriptivas, produce una matriz de correlación de Pearson y presenta un menú interactivo para el usuario.

## Estructura del Proyecto
El repositorio contiene los siguientes archivos y carpetas:

```bash
Proyecto/
├── data/ #donde se almacenan los archivos generados
│ ├── Admission_Predict_new.bin
│ └── transformacion_resultado.txt
├── estructuras.h
├── archivoBin.h
├── archivoBin.cpp
├── archivoTexto.h
├── archivoTexto.cpp
├── menuInteractivo.h
├── menuInteractivo.cpp
├── main.cpp
└── Admission_Predict.csv # Archivo original de datos (entrada)
```

> El archivo `Admission_Predict.csv` debe estar en el **directorio raíz** del proyecto.

### Compilación
Para compilar el proyecto es necesario tener instalado un compilador de C++ como `g++` o utilizar una versión online como Replit. A continuación, se abre una terminal en el directorio raíz y se ejecuta el siguiente código en el bash. Esto generará un archivo ejecutable llamado proyecto1.
```bash
g++ main.cpp archivoBin.cpp archivoTexto.cpp menuInteractivo.cpp -o proyecto1
```

### Ejecución
Una vez compilado, puedes ejecutar el programa con:
```bash
./proyecto1
```

### Durante la ejecución:
- El programa lee el archivo Admission_Predict.csv desde el directorio raíz.
- Genera el archivo binario Admission_Predict_new.bin dentro de data/.
- Muestra las primeras 5 entradas transformadas para validación de que se creó el arreglo de estructuras de manera exitosa.
- Presenta un menú interactivo con las siguientes opciones:

Menú del Programa
1. Mostrar Info: Muestra la información general del dataset.
2. Mostrar Head: Muestra las primeras 5 filas del dataset.
3. Mostrar Describe: Calcula estadísticas descriptivas (media, desviación estándar, mínimo y máximo).
4. Mostrar Matriz de Correlación: Calcula la matriz de correlación de Pearson entre las variables numéricas.
0. Salir: Cierra el programa.

### Resultados
Los resultados de las opciones del menú (1, 2, 3 y 4) se almacenan en el archivo de texto:
`data/transformacion_resultado.txt`

### Limpieza
Para eliminar el ejecutable generado:
```bash
rm proyecto1
```

### Requisitos
- g++ (o cualquier compilador compatible con C++)
- Archivo de entrada: `Admission_Predict.csv` ubicado en el directorio raíz del proyecto

----
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

### Integrantes
- Mariana Grijalba Mora
- Tomas Barbosa Rey
- Isabella Correa Jiménez
- Alexander Pedraza Beltrán