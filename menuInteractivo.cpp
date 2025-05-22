#include "menuInteractivo.h"
#include "archivoTexto.h"

void menuInteractivo(Student* estudiantes, int cantE, string columnas[]){
  int opcion;
  do{
    cout<<"---MENÚ DE ANÁLISIS---"<<endl;
    cout << "1. Mostrar Info"<<endl;
    cout << "2. Mostrar Head"<<endl;
    cout << "3. Mostrar Describe"<<endl;
    cout << "4. Mostrar Correlation"<<endl;
    cout << "0. Salir"<<endl;
    cout << "Seleccione una opción: ";
    cin >> opcion;

    switch (opcion){
    case 1: 
      printInfo(estudiantes, cantE + 1, 9, columnas);
      cout<<"Información guardada en el archivo de texto."<<endl;
      cout<<endl;
      break;

    case 2:
      printHead(estudiantes, cantE, columnas);
      cout<<"Primeras 5 lineas guardadas en el archivo de texto."<<endl;
      cout<<endl;
      break;

    case 3:
      printDescribe(estudiantes, cantE, columnas);
      cout<<"Estadísticas descriptivas guardadas en el archivo de texto"<<endl;
      cout<<endl;
      break;

    case 4:
      printCorrelation(estudiantes, cantE, columnas);
      cout<<"Matriz de correlación guardada en el archivo de texto"<<endl;
      cout<<endl;
      break;

    case 0:
      cout<<"Saliendo del menú."<<endl;
      break;

    default:
      cout<<"Opción inválida. Intente nuevamente."<<endl;
    }
  }while(opcion != 0);
}