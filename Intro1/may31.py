def fam(L):
    lastname = ''
    D = {}
    for family in L:
        lastname = family[-1]
        D[lastname] = []
        for name in family[0:-1]:
            D[lastname].append(name)
    return D

w = fam([['fasd','fasdf','asdfa','asdffsd']])
print w['asdffsd']
