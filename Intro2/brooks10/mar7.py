def countdigits (n):
    numbers = '0123456789'
    count = 0
    for i in n:
        if i in numbers:
            count += 1
    return count

print countdigits ('12334')
print countdigits ('asdfasdfafsd')
print countdigits ('34247823987298357293847298347297527356289756298735678913698461278461897461278934618924681792346817924678912461789563784568934678214681792461782461875686482374618641892648179648')



                   
