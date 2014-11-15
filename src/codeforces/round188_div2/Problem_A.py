n, k = [int(x) for x in raw_input().split()]

even = n / 2
odd = n - even
if k <= odd:
    print k * 2 - 1
else:
    k -= odd
    print k * 2
