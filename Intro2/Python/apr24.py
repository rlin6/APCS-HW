def smol(filename):
    straw=open(filename,'rU')
    s=straw.read()
    Lnums=s.split('\n')
    Lnums.remove('')
    small=s[0]
    for i in Lnums:
        if float(i) < float(small):
            small=float(i)
    return small

print smol('grades.txt')
