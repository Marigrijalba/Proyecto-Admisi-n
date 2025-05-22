#include "archivoBin.h"

void writeStudentsBinary(const string& nombreArchivo, Student* estudiantes, size_t cantE){
  ofstream salidaBinario("data/Admission_Predict_new.bin", ios::binary);
  if(!salidaBinario){
    cerr<<"No se pudo crear el archivo binario para escritura."<<endl;
    return;
  }

  salidaBinario.write(reinterpret_cast<char*>(&cantE), sizeof(size_t));
  salidaBinario.write(reinterpret_cast<char*>(estudiantes), sizeof(Student)*cantE);
  salidaBinario.close();
}

void readStudentsBinary(const string& nombreArchivo){
  ifstream lecturaBinario("data/Admission_Predict_new.bin", ios::binary);
  if(!lecturaBinario){
    cerr<<"No se pudo abrir el archivo binario para lectura."<<endl;
    return;
  }

  size_t cantE;
  lecturaBinario.read(reinterpret_cast<char*>(&cantE), sizeof(size_t));
  Student* estudiantes = new Student[cantE];
  lecturaBinario.read(reinterpret_cast<char*>(estudiantes), sizeof(Student)*cantE);
  lecturaBinario.close();

  cout<<"---VALIDACIÓN ARCHIVO BINARIO---"<<endl;
  for(int i = 0; i < cantE; i++){
    cout << estudiantes[i].serialNo << "     "
         << estudiantes[i].greScore << "     "
         << estudiantes[i].toeflScore << "     "
         << estudiantes[i].universityRating << "     "
         << estudiantes[i].sop << "     "
         << estudiantes[i].lor << "     "
         << estudiantes[i].cgpa << "     "
         << estudiantes[i].research << "     "
         << estudiantes[i].chanceOfAdmit << endl;
  }
  cout<<endl;
  delete[] estudiantes;
}
