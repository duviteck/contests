def calc_ans(n, k, a, b, c, r):
    n -= k
    ls = [a]
    for i in xrange(1, k):
        ls += [(b * ls[i - 1] + c) % r]
    print ls
    ls.sort()
    print ls

    last = -1
    cnt = 0
    while cnt < k:
        #print n
        dif = max(0, ls[cnt] - last - 1)
        if dif >= n:
            return last + n
        else:
            n -= dif
        last = ls[cnt]
        cnt += 1

    return ls[k - 1] + n


def main():
    fin_name = "find_the_min.txt"
    fout_name = "out.txt"

    fin = open(fin_name, 'r')
    with open(fout_name, 'w') as fout:
        numOfTests = int(fin.readline())
        for i in xrange(1, numOfTests + 1):
            n, k = [int(x) for x in fin.readline().split()]
            a, b, c, r = [int(x) for x in fin.readline().split()]
            ans = calc_ans(n, k, a, b, c, r)
            print >>fout, "Case #" + str(i) + ": " + str(ans)
    fin.close()


if __name__ == "__main__":
    main()