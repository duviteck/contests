n, k, m = [int(x) for x in raw_input().split()]

if n >= m or k >= m:
    print 0
    exit()

if (n < 0 and k < 0) or (n < 0 and k == 0) or (n == 0 and k < 0) or (n == 0 and k == 0):
    print -1
    exit()

ans = 0
if n < 0 or k < 0:
    if k < 0:
        n, k = k, n     # n < 0, k > 0
    ans += (k - 1 - n) / k
    n += k * ans

if n >= m or k >= m:
    print ans
    exit()

if k < n:
    n, k = k, n     # k > n

while k < m:
    n, k = k, n + k
    ans += 1

print ans