# Proyecto de Análisis de Datos para Admisión Universitaria

Este proyecto en C++ permite la utilización de un conjunto datos de admisión universitaria en un archivo binario. El sistema lee un archivo CSV, transforma los datos en estructuras, genera un archivo binario, calcula estadísticas descriptivas, produce una matriz de correlación de Pearson y presenta un menú interactivo para el usuario.

## Estructura del Proyecto
El repositorio contiene los siguientes archivos y carpetas:

proyecto/
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

> 📌 El archivo `Admission_Predict.csv` debe estar en el **directorio raíz** del proyecto.

### Compilación
Para compilar el proyecto es necesario tener instalado un compilador de C++ como `g++` o utilizar una versión online como Replit. A continuación, se abre una terminal en el directorio raíz y se ejecuta el siguiente sódigo en el bash. Esto generará un archivo ejecutable llamado proyecto1.
- g++ main.cpp archivoBin.cpp archivoTexto.cpp menuInteractivo.cpp -o proyecto1

### Ejecución
Una vez compilado, puedes ejecutar el programa con:
./proyecto1

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
data/transformacion_resultado.txt

### Limpieza
Para eliminar el ejecutable generado:
rm proyecto1

### Requisitos
g++ (o cualquier compilador compatible con C++)
Archivo de entrada: Admission_Predict.csv ubicado en el directorio raíz del proyecto

### Integrantes
- Mariana Grijalba Mora
- Tomas Barbosa Rey
- Isabella Correa Jiménez
- Alexander Pedraza Beltrán