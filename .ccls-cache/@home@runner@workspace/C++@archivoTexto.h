#ifndef ARCHIVOTEXTO_H
#define ARCHIVOTEXTO_H

#include <iostream> //Para imprimir en consola
#include <string> ////Para usar la clase std::string
#include <fstream> //Para trabajar con archivos
#include <iomanip> //Para manipuladores de flujo en el formato de entreda y salida de datos
#include <cmath>   //Para funciones matemáticas
#include "estructuras.h"
using namespace std;

void printInfo(Student *estudiantes, int cantE, int cantC, string columnas[]);
void printHead(Student *estudiantes, int cantE, string columnas[]);
void printDescribe(Student *estudiantes, int cantE, string columnas[]);
void printCorrelation(Student *estudiantes, int cantE, string columnas[]);

#endif