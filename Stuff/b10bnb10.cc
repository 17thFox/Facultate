#include "iostream"
#include "math.h"
using namespace std;

/*
numar - 		numarul pe care vreau sa il convertesc
baza - 			baza in care vreu sa convertesc numarul "numar"
rezInvers - 	rezultatul invers in ordinea inversa
digits - 		digits imi arata numarul de cifre pe care trebuie sa il aiba rezultatul final, pentru ca sunt convertiri de exemplu 0011, care in rezInvers sunt memorate ca 11 si nu stiu cate zerouri are inainte, digits ma ajuta sa imi dau seama de cate ori sa inmultesc cu 10 dupa aceea
*/
int basechange (int numar, int baza, int rezInvers, int digits) {
	
	//cat timp nr e dif de 0
	if (numar != 0)  {
		
		//creez rezInvers prin inmultirea cu 10 a vechii valori-transmisa ca param- pe care o adun cu restul impartirii numarul la baza noua
		rezInvers = rezInvers * 10 + numar % baza;
		
		//"tai" ultima cifra a numarului
		numar = numar / baza;
		
		//cresc numarul de cifre
		digits++;
		
		//reapelez functia cu noile valori
		return basechange(numar, baza, rezInvers, digits);
	}
	
	//rezultatul final (in ordine corecta)
	int rezultat = 0;
	
	//cat timp rezInvers e dif de 0
	while (rezInvers != 0) {
		
		//iau ultima cifra si o adaug la valoare veche a rezultatului * 10
		rezultat = rezInvers % 10 + rezultat * 10;
		
		//"tai" ultima cifra
		rezInvers = rezInvers / 10;	
		
		//scad numarul de cifre ale lui rezInvers
		digits--;
	}
	
	
	//cat timp mai sunt cifre in rezInvers (adica zerouri)
	while (digits != 0) {
		
		//inmultesc rezultatul final cu 10
		rezultat = rezultat * 10;
		
		//scad numarul de cifre
		digits--;
	}
	
	//afisez rezultatul
	cout << "rezultat: " << rezultat;
	
	//ies din functie
	return 0;
}

/*
numar		numarul pe care vreau sa il convertesc in baza 10
baza		baza in care este numarul
exponent	evidenta exponentului, care urca de la 0 la lungimea numarului
rezultat	rezultatul conversiei
*/
int anyTo10 (int numar, int baza, int exponent, int rezultat) {
	
	//verific daca numarul este 0
	if (numar != 0) {
		
		//consruiesc rezultatul inmultind fiecare cifra cu baza^exponent la care adun rezultatul anterior
		rezultat = (numar % 10) * (pow(baza, exponent)) + rezultat;
		
		//"tai" ultima cifra din numar
		numar = numar / 10;
		
		//cresc valoarea exponentului
		exponent++;
		
		//reapelez functia cu noile valori
		return anyTo10(numar, baza, exponent, rezultat);
	}
	
	//daca numarul este nul, atunci afisez rezultatul
	cout << "rezultat: " << rezultat;
	
	//ies din functie
	return 0;
}

int main () {
	int numar, baza, optiune;
	
	//aleg modul de conversie
	cout << "Cum convertim?\n";
	cout << "1. baza10 -> orice baza\n2. orice baza -> baza10";
	cout << "\noptiune: ";
	cin >> optiune;
	
	if (optiune == 1) {
	
	//citesc numarul in baza 10
	cout << "numar: ";
	cin >> numar;
	
	//citesc baza in care vreu sa il convertesc
	cout << "baza: ";
	cin >> baza;
	
	//apelez functia cu numarul si baza, 0 ca rezInvers si 0 ca digits (nr de cifre din rezInvers)
	basechange(numar, baza, 0, 0);
	}
	
	else if (optiune == 2) {
		
		//citesc numarul in baza n
		cout << "numar: ";
		cin >> numar;
		
		//citesc baza numarului anterior
		cout << "baza: ";
		cin >> baza;
		
		//apelez functia cu numarul si baza, 0 ca exponent si 0 ca rezultat
		anyTo10(numar, baza, 0, 0);
	}
	
	//daca este selectata o alta optiune, iese din program
	else return 0;
	
	//ies din program
	return 0;
}