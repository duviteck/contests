import math


def calc_ans(r, t):
    d = 4*r*r - 4*r + 1 + 8*t
    d_root = math.sqrt(4*r*r - 4*r + 1 + 8*t)
    n = int((d_root + 1 - 2*r) / 4)
    print d, d_root, d_root*d_root
    if int(d_root*d_root) < d:
        print "Here"
        n -= 1
    print n
    return n


def calc_ans_2(r, t):
    ans = 0
    remain = 2*r + 1
    while t >= remain:
        t -= remain
        remain += 4
        ans += 1
    print ans
    print remain, t
    return ans


def main():
    filename = 'input_a.in'
    filename_out = 'output_a.txt'
    result_lines = []
    with open(filename, 'r') as input_file:
        t = int(input_file.readline())
        for test_case in xrange(1, t + 1):
            [r, t] = [int(x) for x in input_file.readline().split()]
            ans = calc_ans(r, t)
            line = 'Case #' + str(test_case) + ': ' + str(ans) + '\n'
            result_lines += [line]
    with open(filename_out, 'w') as output_file:
        output_file.writelines(result_lines)


main()
