#include<iostream>
#include <stdlib.h>
using namespace std;
int a[100][100], e[100], s[100], n;
void sortare()
{
	unsigned aux;
	for (int i = 1; i < n; i++)
	for (int j = i + 1; j <= n; j++)
	{
		if (s[i] < s[j])
		{
			aux = s[i];
			s[i] = s[j];
			s[j] = aux;
			aux = e[i];
			e[i] = e[j];
			e[j] = aux;
		}
	}
}
int main()
{
	unsigned suma = 0;
	cout << "Introduceti lungimea secventei: ";
	cin >> n;

	cout << "Introduceti secventa gradelor in ordine descrescatoare: ";
	for( int  i = 1; i <= n; i++)
	{
		cin >> s[i];
		suma = suma + s[i];
		e[i] = i;
	}

	if ((suma % 2 == 1) || (s[1] > n - 1))
	{
		cout << "Graful nu se poate construi!";
		return 0;
	}

	for (int i = 1; i <= n; i++)
	for (int j = 1; j <= n; j++)
		a[i][j] = 0;

	while (s[1] != 0)
	{
		for (int i = 2; i <= s[1]+1; i++)
		{
			s[i] = s[i] - 1;
			if (s[i] < 0) break;
			a[e[1]][e[i]] = 1;
			a[e[i]][e[1]] = 1;
			
		}
		s[1] = 0;
		sortare();
	}

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
			cout << a[i][j] << " ";
			cout << "\n";
	}
	system ("pause");
	return 0;
}
