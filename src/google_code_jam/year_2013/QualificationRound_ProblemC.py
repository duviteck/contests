def palindrome_gen():
    for i in [x for x in xrange(1, 10)]:
        yield i
    for i in [x+10*x for x in xrange(1, 10)]:
        yield i
    for i in [x+10*y+100*x for x in xrange(1, 10) for y in xrange(10)]:
        yield i
    for i in [x+10*y+100*y+1000*x for x in xrange(1, 10) for y in xrange(10)]:
        yield i
    for i in [x+10*y+100*z+1000*y+10000*x for x in xrange(1, 10) for y in xrange(10) for z in xrange(10)]:
        yield i
    for i in [x+10*y+100*z+1000*z+10000*y+100000*x for x in xrange(1, 10) for y in xrange(10) for z in xrange(10)]:
        yield i
    for i in [x+10*y+100*z+1000*w+10000*z+100000*y+1000000*x for x in xrange(1, 10) for y in xrange(10) for z in xrange(10) for w in xrange(10)]:
        yield i


def is_palindrome(x):
    n = x
    rev = 0
    while x > 0:
        dig = x % 10
        rev = rev * 10 + dig
        x /= 10
    return n == rev


def prepare_fair_square():
    fair_square = []
    for x in palindrome_gen():
        y = x * x
        if is_palindrome(y):
            fair_square += [y]
    return fair_square


def calc_ans(a, b, fair_square):
    return len(filter(lambda x: a <= x <= b, fair_square))


def main():
    fair_square = prepare_fair_square()
    filename = 'input_c.in'
    filename_out = 'output_c.txt'
    result_lines = []
    with open(filename, 'r') as input_file:
        t = int(input_file.readline())
        for test_case in xrange(1, t + 1):
            [a, b] = [int(x) for x in input_file.readline().split()]
            ans = calc_ans(a, b, fair_square)
            line = 'Case #' + str(test_case) + ': ' + str(ans) + '\n'
            result_lines += [line]
    with open(filename_out, 'w') as output_file:
        output_file.writelines(result_lines)


main()