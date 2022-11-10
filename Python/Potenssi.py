import sys

def main():
    x = input("Anna kantaluku: ")
    y = input("Anna potenssiluku: ")
    try:
        x = float(x)
        y = int(y)
    except ValueError:
        print("Virhe: Antamasi syöte ei kelpaa")
        sys.exit(0)
    #sqArea(x)
    val = pow(x, y)
    print("%.4f potenssiin %i on: " % (x, y) + str(val) + "\nPyöristettynä: " + str(round(val, 3)))
    input()

def sqArea(num):
    print(num**2)

def pow(num, exp):
    if exp < 0:
        return 1/pow(num, exp * -1)
    if exp == 0:
        return 1
    if exp == 1:
        return num
    if exp % 2 == 0:
        return pow(num, exp/2) * pow(num, exp/2)
    else:
        exp = exp - 1
        return pow(num, exp/2) * pow(num, exp/2) * num


main()
