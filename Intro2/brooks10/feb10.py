def sumn (x):
    subt = 0
    ct = 1
    while ct <= x:
        subt = subt + ct
        ct = ct + 1
    return subt

print sumn (100)
print sumn (10)
print sumn (3)

def sumLH (Low,High):
    Total = 0 
    while Low <= High:
        Total = Total + Low
        Low = Low + 1
    return Total

print sumLH (10,12)
print sumLH (10,10)

def sumSQ (N):
    Total = 0
    Counter = 1 
    while Counter <= N:
        Total = Total + ( Counter ** 2 )
        Counter = Counter + 1
    return Total
print sumSQ (3)
print sumSQ (1)
print sumSQ (2) 
        
def sumInvSQ (N):
    Total = 0
    Counter = 1
    while Counter <= N:
        Total = Total + ( ( 1. / Counter ) ** 2 )
        Counter = Counter + 1
    return Total
print sumInvSQ (3)
print sumInvSQ (2)
print sumInvSQ (1)
print sumInvSQ (5)

