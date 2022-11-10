from math import sqrt


import math

# Solving x from: ax^2 + bx + c = 0
def toisYhtKaava(a, b, c):
    D = b**2 - (4 * a * c)
    if D < 0:
        print(D)
        return -1;
    print("D: " + str(D))
    x = (-b + (math.sqrt(D))) / (2 * a)
    return x

print(toisYhtKaava(1, 60, -900))
input()
