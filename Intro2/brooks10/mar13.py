def factorial (n):
    if n == 1:
        return 1
    return n * factorial (n-1)

print factorial (5)
print factorial (4)
print factorial (3)
print factorial (123)

def fib (n):
    if n == 1 or n == 0:
        return 1
    return fib(n-1) + fib(n-2)

print fib (1)
print fib (3)
print fib (100)
print fib (34)
print factorial (34)
