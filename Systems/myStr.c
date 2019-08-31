#include <stdio.h>
#include <stdlib.h>

int mystrlen(char *s) {
    int ans = 0;
    while (*s++) {  //increment through index until terminating zero is reached
        ans++;
    }
    return ans;
}

char * mystrcpy(char *dest, char *source) {
    while (*source++) {
        dest[*source] = source[*source] ;  
    }
    return dest;
}

char * mystrncpy(char *dest, char *source, int n);
char * mystrcat(char *dest, char *source);
char * mystrncat(char *dest, char *source, int n);
int mystrcmp(char *s1, char *s2);
char * mystrchr(char *s, char c); 
char * mystrstr(char *s1, char *s2);


