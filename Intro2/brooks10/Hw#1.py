def my_abs (draco):
    if draco < 0:
        return -1 * draco
    else:
        return draco

print my_abs (123)
print my_abs (0)
print my_abs (-123)

def almost_equal (fred,george):
    if my_abs ( (fred - george) ) <= 0.1:
        return True
    else:
        return False

print almost_equal (0.1,0)
print almost_equal (1,2)
print almost_equal (0,0)
print almost_equal (0,0.05)

def distanceFormula ( x1,y1,x2,y2 ):
    return ( ( ( x1 - x2 ) ** 2 ) + ( ( y1 - y2 ) ** 2 ) ) ** 0.5

print distanceFormula ( 3,0,4,0 )
print distanceFormula ( 0,0,3,4 )

def CloseEnough ( x1,y1,x2,y2,x3,y3,x4,y4 ):
    if almost_equal ( ( distanceFormula ( x1,y1,x2,y2 ) )


