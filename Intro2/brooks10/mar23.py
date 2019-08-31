def evens (low,high):
    new = []
    for i in range (low,high+1):
        if i % 2 == 0:
            new.append(i)
    return new

print evens(5,12)


    
