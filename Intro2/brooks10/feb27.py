def IsPrimeWhile (n):
    counter = 0
    i = 1
    while n / i >= i and counter <= 1:
        if n % i == 0:
            counter += 1
        i += 1
    if counter <= 1:
        print True
    else:
        print False

def IsPrimeFor (n):
    factors = 0
    for i in range (1,n+1):
        if (n % i) == 0:
            factors += 1
    if factors == 2:
        print True
    else:
        print False

IsPrimeWhile (23)
IsPrimeFor (23)
IsPrimeWhile (50)
IsPrimeFor (50) 
IsPrimeWhile (2)
IsPrimeFor (2)
IsPrimeWhile (100)
IsPrimeFor (100)

def Saver ():
    counter = 

def IsPrime (n):
    counter = 1
    Factor = 0
    Stop = 0
    if Stop == 0:
        if n % counter == 0:
            Factor += 1
        counter += 1
        IsPrime (n)

def IsPrime2 (n):
    if n == x :
        return True
    else:
        
