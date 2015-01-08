def calc_ans(a):
    a.sort()
    index = 0
    cnt = 0
    ans = 1
    for x in xrange(len(a)):
        while index < len(a) and a[index] <= x:
            cnt += 1
            index += 1
        ans *= cnt
        if ans >= 1000000007:
            ans %= 1000000007
        cnt -= 1
    return ans

t = int(raw_input())
for testCase in xrange(t):
    n = int(raw_input())
    nums = [int(x) for x in raw_input().split()][:n]
    print calc_ans(nums)