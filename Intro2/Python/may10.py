def vari():
    s=open('dictall.txt','ru')
    w=s.read()
    x=w.split('\n')
    sumi = 0
    for i in len(x):
        if x[i] == (x[i-1] + 's'):
	    sumi += 1
    return sumi

print vari()
