[n, t] = [int(z) for z in raw_input().split()]
nums = [int(z) for z in raw_input().split()]

bestAns = 0
summary = 0
elemsCnt = 0
for curPos in xrange(len(nums)):
    summary += nums[curPos]
    elemsCnt += 1
    while summary > t and elemsCnt > 1:
        summary -= nums[curPos - elemsCnt + 1]
        elemsCnt -= 1
    if elemsCnt > bestAns and summary <= t:
        bestAns = elemsCnt

print bestAns