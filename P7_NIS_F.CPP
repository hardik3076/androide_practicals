#include <stdio.h>
#include <conio.h>
#include <string.h>

#define MAX 100

// Function for encryption
void encryptRailFence(char text[], int key) {
     int row = 0, down = 0;
    int len = strlen(text);
    char rail[10][MAX]; // 10 max rails for simplicity
    int i, j;

    // Initialize rail matrix with '\n'
    for (i = 0; i < key; i++)
	for (j = 0; j < len; j++)
	    rail[i][j] = '\n';



    // Fill the matrix in zig-zag
    for (i = 0; i < len; i++) {
        rail[row][i] = text[i];

        if (row == 0)
            down = 1;
        else if (row == key - 1)
            down = 0;

        if (down)
            row++;
        else
            row--;
    }

    // Read row-wise to get ciphertext
    printf("\nEncrypted Text: ");
    for (i = 0; i < key; i++)
        for (j = 0; j < len; j++)
            if (rail[i][j] != '\n')
                printf("%c", rail[i][j]);
    printf("\n");
}

// Function for decryption
void decryptRailFence(char cipher[], int key) {
    int len = strlen(cipher);
    char rail[10][MAX];
    int row = 0, down = 0;

     int index = 0;
     int i, j;

    // Initialize rail matrix with '\n'
    for (i = 0; i < key; i++)
	for (j = 0; j < len; j++)
	    rail[i][j] = '\n';



    // Mark positions with '*'
    for (i = 0; i < len; i++) {
	rail[row][i] = '*';

	if (row == 0)
	    down = 1;
	else if (row == key - 1)
	    down = 0;

	if (down)
	    row++;
	else
	    row--;
    }

    // Fill cipher text in marked positions

    for (i = 0; i < key; i++)
	for (j = 0; j < len; j++)
	    if (rail[i][j] == '*' && index < len)
		rail[i][j] = cipher[index++];

    // Read in zig-zag manner
    row = 0;
    down = 0;
    printf("\nDecrypted Text: ");
    for (i = 0; i < len; i++) {
	printf("%c", rail[row][i]);

	if (row == 0)
	    down = 1;
	else if (row == key - 1)
	    down = 0;

	if (down)
	    row++;
	else
	    row--;
    }
    printf("\n");
}

int main() {
    char text[MAX];
    int key, option;

    clrscr();
    do {

	printf("=== Rail Fence Cipher ===\n");
	printf("1. Encrypt\n");
	printf("2. Decrypt\n");
	printf("3. Exit\n");
	printf("Choose an option: ");
	scanf("%d", &option);
	fflush(stdin); // clear input buffer for Turbo C

	switch(option) {
            case 1:
                printf("Enter plaintext (spaces allowed): ");
                scanf(" %[^\n]s", text); // reads a line with spaces
                printf("Enter key (number of rails): ");
                scanf("%d", &key);
                encryptRailFence(text, key);
                getch();
                break;

            case 2:
                printf("Enter ciphertext: ");
                scanf(" %[^\n]s", text); // reads a line with spaces
                printf("Enter key (number of rails): ");
                scanf("%d", &key);
                decryptRailFence(text, key);
                getch();
                break;

            case 3:
                printf("Exiting program...\n");
                getch();
                break;

            default:
                printf("Invalid option! Try again.\n");
                getch();
        }
    } while(option != 3);

    return 0;
}
