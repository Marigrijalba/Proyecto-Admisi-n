#ifndef ESTRUCTURAS_H
#define ESTRUCTURAS_H

struct Student {
    int serialNo;
    double greScore;
    double toeflScore;
    int universityRating;
    double sop;
    double lor;
    double cgpa;
    int research;
    double chanceOfAdmit;
};

struct Estadisticas {
    double sumatoria = 0;
    double sumatoriaCuadrados = 0;
    double min = 1000;
    double max = -1000;
};

#endif