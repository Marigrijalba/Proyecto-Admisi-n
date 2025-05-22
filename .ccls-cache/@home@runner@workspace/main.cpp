#include "archivoBin.h"
#include "archivoTexto.h"
#include "menuInteractivo.h"

int main() {
  ifstream archivo("Admission_Predict.csv");
  if (!archivo.is_open()) {
    cerr << "No se pudo abrir el archivo CSV." << endl;
    return 1;
  }

  string encabezado;
  getline(archivo, encabezado); // leer encabezado
  stringstream ssEncabezado(encabezado);
  string columnas[9];
  for (int i = 0; i < 9; i++) {
    getline(ssEncabezado, columnas[i], ',');
  }

  size_t capacidad = 100; // capacidad inicial
  size_t cantE = 0;
  Student *estudiantes = new Student[capacidad]; // memoria dinámica
  string linea;
  while (getline(archivo, linea)) {
    if (cantE == capacidad) { // redimensionar si es necesario
      capacidad *= 2;
      Student *temp = new Student[capacidad];
      for (size_t i = 0; i < cantE; i++) {
        temp[i] = estudiantes[i];
      }
      delete[] estudiantes;
      estudiantes = temp;
    }

    stringstream ss(linea);
    string campo;

    getline(ss, campo, ',');
    estudiantes[cantE].serialNo = stoi(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].greScore = stod(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].toeflScore = stod(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].universityRating = stoi(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].sop = stod(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].lor = stod(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].cgpa = stod(campo);
    getline(ss, campo, ',');
    estudiantes[cantE].research = stoi(campo);
    getline(ss, campo);
    estudiantes[cantE].chanceOfAdmit = stod(campo);
    cantE++;
  }
  archivo.close();

  writeStudentsBinary("data/Admission_Predict_new.bin", estudiantes, cantE);
  readStudentsBinary("data/Admission_Predict_new.bin");
  menuInteractivo(estudiantes, cantE, columnas);

  delete[] estudiantes;
  return 0;
}