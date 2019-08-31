def fred(q):
    return q * q - 3

print fred(3)
print fred(0.4)
print fred(-1)
print fred(10)

def IsPrime (n):
    factors = 0
    if n < 10000000:
      for i in range (1,n):
        if (n % i) == 0:
          factors += 1
    else:
      for i in range (1,10000000):
        if (n % i) == 0:
          factors += 1
    if factors <= 2:
        return True
    else:
        return False

print IsPrime (1231231223123134234523452345234512432423469087)


