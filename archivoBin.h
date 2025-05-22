#ifndef ARCHIVOBINARIO_H
#define ARCHIVOBINARIO_H

#include <iostream> //Para imprimir en consola
#include <fstream> //Para trabajar con archivos
#include <sstream> //para convertir una línea en un stream que se pueda dividir
#include <string>  //Para usar la clase std::string
#include "estructuras.h"
using namespace std;

void writeStudentsBinary(const string& nombreArchivo, Student* estudiantes, size_t cantE);
void readStudentsBinary(const string& nombreArchivo);

#endif