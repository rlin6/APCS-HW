def toupper (s):
    up = 'QWERTYUIOPASDFGHJKLZXCVBNM'
    lo = 'qwertyuiopasdfghjklzxcvbnm'
    if s in lo:
        return up[lo.index(s)]
    return s

print toupper ('a')
print toupper ('A')
print toupper ('sfafdasfa.,,.')

def shift (s):
    a = ''
    for i in s:
        if i == 'z':
            c = 'a'
        elif i == 'Z':
            c = 'A'
        else:
            b = ord(i)
            c = chr(b+1)
        a += c
    return a 

print shift ('HiThere')
print shift ('EndMyMissouri')
print shift ("zzzzz")

def decode (s):
    a = ''
    for i in s:
        if i == 'a':
            c = 'z'
        elif i == 'A':
            c = 'Z'
        else:
            b = ord(i)
            c = chr(b-1)
        a += c
    return a

print decode ('FoeNzNjttpvsj')
print decode ('aaaaaa')

def isCap (s):
    for i in s:
        if i in 'QWERTYUIOPASDFGHJKLZXCVBNM':
            return True
        else:
            return False

def isLower (s):
    for i in s:
        if i in 'qwertyuiopasdfghjklzxcvbnm':
            return True
        else:
            return False

def lowermove (s,n):
    a = ''
    if isLower(s):
        lower = 'abcdefghijklmnopqrstuvwxyz'
        b = lower[s.find(i) + n % 26 ]
        a += b
    return a

def capmove (s,n):
    a = ''
    if isCap(s):
        cap = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
        b = cap[s.find(i) + n % 26 ]
        a += b
    return a

def shift (s,n):
    for i in s:
        lowermove(s,n)
        capmove(s,n)

print shift ('ABCDEFG', 1)

            
            
        
