## KOMPRESOR I DEKOMPRESOR ZA POMOCĄ ALGORYTMU KODOWANIA HUFFMANA Z KOLEJKĄ PRIORYTETOWĄ NA KOPCU

### URUCHOMIENIE PROGRAMU
Program przyjmuje dwa argumenty wywołania:  
-pierwszy to ścieżka do pliku, który chcemy skompresować lub zdekompresować,  
-drugi to ścieżka do katalogu, w którym chcemy zapisać plik wyjściowy.

### DZIAŁANIE PROGRAMU
Program przyjmuje pierwszy plik i sprawdza jego ostatnie rozszerzenie. Jeżeli będzie to .huf (moje rozszerzenie dla plików skompresowanych),  
to wykona dekompresję tego pliku i zapisze wynik w katalogu z drugiego argumentu wywołania jako plik .txt.  
Każde inne rozszerzenie zostanie skompresowane do pliku .huf do katalogu z drugiego argumentu wywołania.

### UWAGI
Program nie skompresuje plików poniżej 3 bajtów.