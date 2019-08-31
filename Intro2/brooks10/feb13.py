def root2 () :
    Low = 1
    High = 2
    while High - Low > 0.00000000001:
        mid = 0.5 * (Low + High)
        if mid * mid > 2:
            High = mid
        else:
            Low = mid
    return Low  
    
print root2 ()

def root3 () :
    Low = 1
    High = 3
    while High - Low > 0.00000000001:
        mid = 0.5 * (Low + High)
        if mid * mid > 3:
            High = mid
        else:
            Low = mid
        return Low

print root3 ()

