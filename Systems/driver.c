#include <stdio.h>
#include <stdlib.h>
#include "myStr.h"

int main()
{
    char *s = "whack";
    char *b = "hello";
    char *c = "cheeze";
    mystrcpy(b, s);
    printf("%s\n", s);
    printf("%s\n", b);
    return 0;
}

//n versions of str safer, never overflow 
//string literal = immutables, cant copy things into it 