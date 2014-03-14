#include <stdio.h>
#include <stdlib.h>

/*
verificarea numarului se face de la sfarsit spre inceput
*/
long long fierastrau2(int n, int semn) {
	/*
	c1 este ultima cifra
	c2 este cifra zecilor
	*/
	int c1 = n % 10;
	int c2 = (n / 10) % 10;
	
	/*
	verific daca numarul e pozitiv sau nu 
	*/
	if (n < 0) {
		printf("Numarul NU are forma de fierastrau!");
		return 0;
	}
	
	/*
	daca numarul = 0, inseamna ca numarul introdus are forma de fierastrau
	*/
	if (n < 10) {
		printf("Numarul are forma de fierastrau!");
		return 0;
	}
		
	/*
	urmatorul if se foloseste o singura data pentru a stabili inceputul comparatiilor, "taie" ultima cifra si reapeleaza functia cu semnul respectiv
	
	2 pentru "<";
	1 pentru ">";
	*/
	if (semn == 0){
		
		if (c1 < c2)
			semn = 2;
		else 
			semn = 1;
		
		n = n / 10;
		return fierastrau2(n, semn);
	}
			
	/*
	urmatoarele 2 if-ul verifica daca urmatoarele numere sunt in ordinea corecta adica daca prin comparatia urmatoarelor 2 cifre, semnul dintre ele este opus fata de cel precedent, "taie ultima cifra si reapleaza fucntia cu semnul stabilit conform regulii de mai sus"
	*/
	if (c1 > c2 && semn == 2) {
		semn = 1;
		n = n / 10;
		return fierastrau2(n,semn);
		
	} 
	if (c1 < c2 && semn == 1) {
		semn = 2;
		n = n / 10;
		return fierastrau2(n,semn);
	}
	
	/*
	daca se ajunge aici inseamna ca numarul nu s-a terminat si nici cifrele nu sunt in ordinea corecta
	*/
	printf("Numarul NU are forma de fierastrau!");
	return 0;
}

int main() {
	long long numar;
	
	printf("numarul: ");
	scanf("%lld", &numar);
	
	fierastrau2(numar, 0);
}