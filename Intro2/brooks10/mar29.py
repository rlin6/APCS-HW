def fib(n):
    new = []
    for i in range(n):
        new.append(fib(n-1) + fib(n-2))
    return new

print fib(3)
