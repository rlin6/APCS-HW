def tower (n):
    if n == 1:
        return 1
    return 2 * tower(n-1) + 1

print tower(1)
print tower(2)
print tower(3)
print tower(4)
print tower(64)
