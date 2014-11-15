import re

line = raw_input().strip()
heavy = [(m.start(), 'h') for m in re.finditer('heavy', line)]
metal = [(m.start(), 'm') for m in re.finditer('metal', line)]

ls = heavy + metal
ls.sort(key=lambda x: x[0])

metal_count = len(metal)
cur_metal_count = metal_count
ans = 0
for x in ls:
    if x[1] == 'h':
        ans += cur_metal_count
    else:
        cur_metal_count -= 1

print ans


