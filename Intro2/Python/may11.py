def mostfreq():
    straw=open('GettysburgAddress.txt','ru').read()
    s=straw.lower()
    s=s.replace('.','').replace(',','').replace('--','').split()
    D={}
    for words in s:
        if words in D:
            D[words]= D[words]+1
        else:
            D[words]=1
    V = []
    for stuff in D:
        V.append([D[stuff],stuff])
    w = sorted(V)
    return w[-5::]

print mostfreq()

    
