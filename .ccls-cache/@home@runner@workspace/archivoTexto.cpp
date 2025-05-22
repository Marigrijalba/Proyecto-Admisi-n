#include "archivoTexto.h"

void printInfo(Student *estudiantes, int cantE, int cantC, string columnas[]) {
  ofstream salida("data/transformacion_resultado.txt", ios::app);
  if (!salida.is_open()) {
    cerr << "No se pudo crear el archivo de salida." << endl;
    return;
  }
  salida << "---INFO---" << endl;
  salida << "Número de filas (incluyendo header): " << cantE << endl;
  salida << "Número de columnas: " << cantC << endl;
  salida << "Nombres de columnas:" << endl;
  for (int i = 0; i < cantC; i++) {
    salida << " " << columnas[i] << endl;
  }
  int nulosSN = 0, nulosGRE = 0, nulosTOEFL = 0, nulosUR = 0, nulosSOP = 0,
      nulosLOR = 0, nulosCGPA = 0, nulosR = 0, nulosCA = 0;

  salida << "Resumen por columnas: " << endl;
  salida << "Columna          Nulls          Tipo" << endl;
  for (int j = 0; j < cantE; j++) {
    if (isnan(estudiantes[j].serialNo)) {
      nulosSN++;
    }
    if (isnan(estudiantes[j].greScore)) {
      nulosGRE++;
    }
    if (isnan(estudiantes[j].toeflScore)) {
      nulosTOEFL++;
    }
    if (isnan(estudiantes[j].universityRating)) {
      nulosUR++;
    }
    if (isnan(estudiantes[j].sop)) {
      nulosSOP++;
    }
    if (isnan(estudiantes[j].lor)) {
      nulosLOR++;
    }
    if (isnan(estudiantes[j].cgpa)) {
      nulosCGPA++;
    }
    if (isnan(estudiantes[j].research)) {
      nulosR++;
    }
    if (isnan(estudiantes[j].chanceOfAdmit)) {
      nulosCA++;
    }
  }
  salida << columnas[0] << "          " << nulosSN << "          Numérico"
         << endl;
  salida << columnas[1] << "           " << nulosGRE << "          Numérico"
         << endl;
  salida << columnas[2] << "         " << nulosTOEFL << "          Numérico"
         << endl;
  salida << columnas[3] << "   " << nulosUR << "          Numérico" << endl;
  salida << columnas[4] << "                 " << nulosSOP
         << "          Numérico" << endl;
  salida << columnas[5] << "                " << nulosLOR
         << "          Numérico" << endl;
  salida << columnas[6] << "                " << nulosCGPA
         << "          Numérico" << endl;
  salida << columnas[7] << "            " << nulosR << "          Numérico"
         << endl;
  salida << columnas[8] << "     " << nulosCA << "          Numérico" << endl;
  salida << endl;
  salida.close();
}

void printHead(Student *estudiantes, int cantE, string columnas[]) {
  ofstream salida("data/transformacion_resultado.txt", ios::app);
  if (!salida.is_open()) {
    cerr << "No se pudo crear el archivo de salida." << endl;
    return;
  }
  salida << "--- HEAD ---" << endl;
  for (int i = 0; i < 9; i++) {
    salida << "     " << columnas[i];
  }
  for (int j = 0; j < 5; j++) {
    salida << "              " << estudiantes[j].serialNo << "           "
           << estudiantes[j].greScore << "          "
           << estudiantes[j].toeflScore << "              "
           << estudiantes[j].universityRating << "         "
           << estudiantes[j].sop << "              " << estudiantes[j].lor
           << "              " << estudiantes[j].cgpa << "             "
           << estudiantes[j].research << "              "
           << estudiantes[j].chanceOfAdmit << endl;
  }
  salida << endl;
  salida.close();
}

void printDescribe(Student *estudiantes, int cantE, string columnas[]) {
  ofstream salida("data/transformacion_resultado.txt", ios::app);
  if (!salida.is_open()) {
    cerr << "No se pudo crear el archivo de salida." << endl;
    return;
  }
  salida << "--- DESCRIBE ---" << endl;
  salida << fixed << setprecision(4);

  Estadisticas estadisticas[9];

  for (int i = 0; i < cantE; i++) {
    double valores[9] = {static_cast<double>(estudiantes[i].serialNo),
                         estudiantes[i].greScore,
                         estudiantes[i].toeflScore,
                         static_cast<double>(estudiantes[i].universityRating),
                         estudiantes[i].sop,
                         estudiantes[i].lor,
                         estudiantes[i].cgpa,
                         static_cast<double>(estudiantes[i].research),
                         estudiantes[i].chanceOfAdmit};

    for (int j = 0; j < 9; j++) {
      estadisticas[j].sumatoria += valores[j];
      estadisticas[j].sumatoriaCuadrados += valores[j] * valores[j];
      if (valores[j] < estadisticas[j].min) {
        estadisticas[j].min = valores[j];
      }
      if (valores[j] > estadisticas[j].max) {
        estadisticas[j].max = valores[j];
      }
    }
  }

  for (int k = 0; k < 9; k++) {
    double mean = estadisticas[k].sumatoria / cantE;
    double std = sqrt(estadisticas[k].sumatoriaCuadrados / cantE - mean * mean);
    salida << columnas[k] << ": "
           << "Mean = " << mean << ", Std = " << std
           << ", Min = " << estadisticas[k].min
           << ", Max = " << estadisticas[k].max << endl;
  }
  salida << endl;
  salida.close();
}

void printCorrelation(Student *estudiantes, int cantE, string columnas[]) {
  ofstream salida("data/transformacion_resultado.txt", ios::app);
  if (!salida.is_open()) {
    cerr << "No se pudo crear el archivo de salida." << endl;
    return;
  }

  salida << "--- CORRELATION MATRIX ---" << endl;

  const int n = 8; // número de variables (sin contar chanceOfAdmit)
  double datos[cantE][n];

  // Llenamos la matriz de datos
  for (int i = 0; i < cantE; i++) {
    datos[i][0] = estudiantes[i].serialNo;
    datos[i][1] = estudiantes[i].greScore;
    datos[i][2] = estudiantes[i].toeflScore;
    datos[i][3] = estudiantes[i].universityRating;
    datos[i][4] = estudiantes[i].sop;
    datos[i][5] = estudiantes[i].lor;
    datos[i][6] = estudiantes[i].cgpa;
    datos[i][7] = estudiantes[i].research;
  }

  // Calcular medias
  double medias[n] = {0};
  for (int j = 0; j < n; j++) {
    for (int i = 0; i < cantE; i++) {
      medias[j] += datos[i][j];
    }
    medias[j] /= cantE;
  }

  // Calcular desviaciones estándar
  double desvios[n] = {0};
  for (int j = 0; j < n; j++) {
    for (int i = 0; i < cantE; i++) {
      desvios[j] += pow(datos[i][j] - medias[j], 2);
    }
    desvios[j] = sqrt(desvios[j] / cantE);
  }

  // Calcular la matriz de correlación
  salida << fixed << setprecision(4);
  salida << setw(20) << " ";
  for (int j = 0; j < n; j++) {
    salida << setw(15) << columnas[j];
  }
  salida << endl;

  for (int i = 0; i < n; i++) {
    salida << setw(20) << columnas[i];
    for (int j = 0; j < n; j++) {
      double suma = 0;
      for (int k = 0; k < cantE; k++) {
        suma += (datos[k][i] - medias[i]) * (datos[k][j] - medias[j]);
      }
      double correlacion = suma / (cantE * desvios[i] * desvios[j]);
      salida << setw(15) << correlacion;
    }
    salida << endl;
  }

  salida.close();
}