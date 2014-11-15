def process_line(line):
    letters = {}
    # calc occurrences of each letter
    for symbol in line:
        if ord('a') <= ord(symbol) <= ord('z'):
            if symbol not in letters:
                letters[symbol] = 1
            else:
                letters[symbol] += 1
    # sort occurrences
    ls = letters.values()
    ls.sort(reverse = True)
    # calc ans
    ans = 0
    max = 26
    for i in ls:
        ans += i * max
        max -= 1
    return ans


def main():

    fin_name = "beautiful_strings.txt"
    fout_name = "out.txt"

    fin = open(fin_name, 'r')
    with open(fout_name, 'w') as fout:
        numOfTests = int(fin.readline())
        for i in xrange(1, numOfTests + 1):
            line = ''.join(fin.readline().split())
            line = line.lower()
            ans = process_line(line)
            print >>fout, "Case #" + str(i) + ": " + str(ans)
    fin.close()


if __name__ == "__main__":
    main()