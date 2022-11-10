def fib1(n):
    print ("Fibonacci sequence\n------------------")
    a, b = 0, 1
    while a < n:
        print(a)
        a, b = b, b + a
    print("----\nDone")


    # O(n^2)
def fib2(n):
    if (n < 0):
        print("Error: n < 0")
        return -1
    elif (n == 1 or n == 2):
        return 1
    elif (n == 0):
        return 0
    else:
        return fib2(n-1) + fib2(n-2)

    # O(n)
def fib3(n):
    if (n < 0):
        print("Error: n < 0")
        return -1
    if (n == 0):
        return 0
    i = 2
    curr = 1
    prev = 1
    while (i < n):
        prev, curr = curr, curr + prev
        i = i + 1
    return curr

fib1(1000)
print(fib2(int(input("Which n:th fibonacci number do you want? (1st and 2nd equal 1): "))))
print("Done\n----")
print(fib3(int(input("Which n:th fibonacci number do you want? (1st and 2nd equal 1): "))))
print("Done\n----")
input()
