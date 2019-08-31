def calc():
    straw=open('grades.txt','rU')
    text=straw.read()
    text.split('\n')
    sum1 = []
    for i in text:
        sum1 += [float(i)]
    return sum(sum1) / len(sum1)

print calc()

