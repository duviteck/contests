def calc_ans(n, k, a):
    #print n, k, a
    for x in xrange(n):
        if a[x] >= 1000000007:
            a[x] -= 1000000007
    a.sort()

    #print a
    ans = 0
    upperMult = k
    lowerMult = 1
    mult = 1
    for x in xrange(k - 1, n):
        add = (a[x] * mult) % 1000000007
        #print add
        mult = (mult * upperMult / lowerMult)   # can we divide here?
        upperMult += 1
        lowerMult += 1
        ans += add
    return ans % 1000000007


def main():
    fin_name = "card_game.txt"
    fout_name = "out.txt"

    fin = open(fin_name, 'r')
    with open(fout_name, 'w') as fout:
        numOfTests = int(fin.readline())
        for i in xrange(1, numOfTests + 1):
            print "Test " + str(i)
            [n, k] = [int(x) for x in fin.readline().split()]
            a = map(int, fin.readline().split())
            ans = calc_ans(n, k, a)
            print >>fout, "Case #" + str(i) + ": " + str(ans)
    fin.close()


if __name__ == "__main__":
    main()