def fibtime (n):
    if n == 38:
        return 20
    return fibtime(n-1) * ((1 + (5 ** 0.5)) / 2)

def fibtime2 (n):
    return 20 * (((1 + (5 ** 0.5)) / 2) ** (n-38))

print fibtime(50)
print fibtime2(50)
