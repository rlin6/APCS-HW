#include <stdio.h> 
#include <stdlib.h> 
#include <string.h>

struct Pokemon 
{
    char name[10]; 
    int hp; 
};



int main()
{
    struct Pokemon mypokemon;
    return 0;
}

/*  struct foo a, b;   regular variables, not pointers 
    a.id = 56;
    a.vals[0] = 12; 
    a.vals[1] = 13;
    b = a;             direct copy 

    dont use char *s, it points to another piece of mem, using it in struct gives it memory just for pointer with no actual memory storage, can't interact with it
    using char s[] with no size will make it into another pointer 
    use strcpy to set string array value
    Strings are immutable in Java -> can use = as you can creating new strings and swapping mem address, strings are mutable in C 
    char values have number vals 

    Stack memory vs heap memory 
    Each program can have its own stack and heap 
    Stack memory:
        stores all normally declared variables (including pointers and structs), arrays, and function calls 
        functions are pushed onto stack in order they are called, and popped off when completed 
        when a function is popped off the stack, the stack memory associated with it is released 

    Heap memory:
        stores dynamically stored memory 
        memory lives until manually removed or program ends   

        malloc() / free()
        calloc() / realloc() 

    malloc(size_t x)
        allocates x bytes of memory (from the heap)
        returns the address at the beginning of the allocation
        returns a void pointer

        int *p; 
        p = malloc( 5 * sizeof(int) );

    free(void * p)
        releases dynamically allocated memory 
        has one pointer, a pointer to the beginning of a dynamically allocated block of memory 
        every call to malloc/calloc should have a corresponding call to free() 

    calloc(size_t n, size_t x)
        allocates n * x bytes of memory, setting every bit to 0 
        works like malloc in all other ways 

    realloc(void *p, size_t x)
        changes the amount of memory allocated for a block to x bytes
        p must point to beginning of a block 
        returns a pointer to the beginning of the block (this is not always the same as p)
        if x is smaller than original size, the extra bytes will be released
        if x is larger than orginal size then either:
        1. theres enough space at the of the orginal allocation the orginal allocation will be updated 
        if not enougb space a new alloc containing orgianl values will be freed 
        */