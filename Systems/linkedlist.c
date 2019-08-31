#include <stdio.h>
#include <stdlib.h>

struct node { int i; struct node *next }; 

struct node; 
void print_list(struct node *n) {

}

struct node * insert_front(struct node *n, int i) {

}

struct node * free_list(struct node *n) {

}

/* GDB GNU DeBugger 
    - to use gdb, you must compile using the -g flag with gcc (much larger file)
    $ gdb program 
    Starts a gdb shell from which you can run your program 
    Commands from in gdb shell:
        run: runs the program until it ends/crashes/gets a signal 
        list: show the lines of code run around a crash
        print var: print the value of var
        break line_number: creates a breakpoint at the given line
        continue: run the program until the next breakpoint
        next: run the next line of the program only(even if it's a function)
        step: run the next line of the program, if that is a functon call, run only the next line of that function 
        run: restarts the program
    gbd stopped at error and warns you but using continue goes on and crashes 

Valgrind: tool for debugging memory issues in C programs 
Must compile with -g in order to use 
Usage: $valgrind --leak-check=yes program
*/
