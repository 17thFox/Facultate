#include <stdio.h>
#include <stdlib.h>


int fierastrau(int n, int semn, int ok) {
	int k = 0, ok;
	int c1 = n % 10;
	int c2 = (n / 10) % 10;
	printf("da 3\n");
	
	if (c1 > c2 && semn == 2) {
		semn = 2;
		n = n / 10;
		ok = 1;
		printf("da 1\n");
		fierastrau(n, semn);
		
	} else  {
		if (c1 < c2 && semn == 1) {
			semn = 1;
			n = n / 10;
			ok = 1;
		printf("da 2\n");
			fierastrau(n, semn);
		}
		else 
			if (n != 0)
			ok = 0;
	}
	if (ok == 1)
		return 1;
	return 0;
}
	


int main () {
	int numar, semn;
	
	printf("numarul: ");
	scanf("%d", &numar);
	
	printf ("%d %d",numar % 10, (numar / 10) % 10  );
	if (numar % 10 < (numar / 10) % 10)
		semn = 2;
	else 
		semn = 1;
	
	if (fierastrau(numar / 10, semn) == 1) 
		printf("Numarul e fierastrau!");
	else 
		printf("Numarul nu e fierastrau!");
}