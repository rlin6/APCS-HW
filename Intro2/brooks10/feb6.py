def Fred ( zero,value ):
    if zero == 0:
        return ( 2 * value )
    else:
        return ( zero + value )
print Fred ( 0,12 )
print Fred ( 1,12 )
print Fred ( 0,1234 )
print Fred ( 123,123123)
