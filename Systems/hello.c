#include <stdio.h>
#include <stdlib.h>

int length(char *s) {
    int ans = 0;
    while (*s++) {  //boolean nature of numbers
        ans++;
    }
    return ans;
}

int main() {
    char *s = "hello";  //immutable string, dont do
    char s1[] = "hello"; //[] vs [256]: [] allocates exactly the amount for string, however cannot concatenate- stuck with size; [256] creates immutable string and copies it(including terminating zeroes)

    printf("s points to: %p\n", s);
    printf("s1 points to: %p\n", s1);
    printf("address of \"hello\": %p\n", &"hello");

    printf("s1: %s\n", s1);

    s1[0] = 'y';
    s1[1] = 'o';
    s1[2] = '!';
    s1[3] = 0;
    //s1[3] = '1'; strings end with terminating 0, weird without 

    printf("length of %s: %d\n", s1, length(s1));
    return 0;
}


/* Array size != length of string
C functions are pass-by-value 
You can modify argument values if you pass a pointer (object references in Java)
Actual argument is not passed into function, a copy of the value is made and passed into function, the copy is altered 
Stack - FILO First In Last Out 
Stack based memory: associated function calls and variables popped out when done
String literal associated with memory address, created copy of address into function 
* = pointer, int x = 5; void foo(int *i) can modify x 
int x = 5; void foo(int i) can't modify x */ 

/*Function Declaration:
1. Declare and define all functions at the top of your code, making sure the order is accurate
2. Declare function headers at the top of your code, and provide the definitions later on
    - order of declarations and definitions does not matter 
    ex. int length(char *s);
    - will compile, but get error message 
3. Put all function declarations/header in a separate (.h) file, and include that at the top of your code, still have to give definitions
    - use "" instead of <> */

/* Compiling & Linking 

Compiling
    gcc performs multiple tasks to create an executable file, among them, we have:
    Compiling- turning c source files into binary files, no executable 
    gcc -c will compile only, resulting in a non-executable .o file     
Linking
    combining binary code from multiple files into a single executable 
    ld - link command

Can combine multiple c file into a c program by including them all in one gcc command: gcc test.c string.c foo.c 

cant have multiple mains; cannot have duplicate function or global variable names across these files 
must have main to create an executable program (dont have to for compiling) 

standard for multiple-file c program: preferred structure is to provide separate .c/.h files for each part except for the file containing the main 

each .c including main driver should be compiled to a .o file 

The .o files should then be linked together via gcc 

Make
    create compiling instructions and setup dependencies
    standard name for the file is makefile 
    syntax:
        target: dependencies 
        <TAB>rules
(example)
all: strfun.o mystr.o 
    gcc strfun.o mystr.o 

strfun.o: strfun.c mystr.h 
    gcc -c strfun.c 

mystr.o: mystr.c mystr.h
    gcc -c mystr.c 

run in terminal as make 

make recursively goes through files
will only recompile things that needs to be recompiled - looking at timestamps 
$ make target -> specific target
you can provide "phony" targets that are not part of the normal dependencies chain - for things you want to automate but not run everytime 