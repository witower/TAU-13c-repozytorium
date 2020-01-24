#!/usr/bin/env python3
import sys
import re
def check_figure(tab):
    if len(tab) < 4 or len(tab) > 5:
        return 'nie rozpoznano'
    if len(tab) == 4:
        try:
            a = float(tab[1])
            b = float(tab[2])
            c = float(tab[3])
        except ValueError:
            return 'nie rozpoznano'
        if a <= 0.0 or b <= 0.0 or c <= 0.0:
            return 'nie rozpoznano'
        if a + b <= c or a + c <= b or b + c <= a:
            return 'nie rozpoznano'
        if a == b == c:
            return 'trojkat rownoboczny'
        if a == b or c == b or a == c:
            return 'trojkat rownoramienny'
        return 'trojkat roznoboczny'
    if len(tab) == 5:
        try:
            a = float(tab[1])
            b = float(tab[2])
            c = float(tab[3])
            d = float(tab[4])
        except ValueError:
            return 'nie rozpoznano'
        if a <= 0.0 or b <= 0.0 or c <= 0.0 or d <= 0.0:
            return 'nie rozpoznano'
        if a + b + d <= c or a + c + d <= b or b + c + d <= a or a + b + c <= d:
            return 'nie rozpoznano'
        if a == b == c == d:
            return 'kwadrat'
        if (a == b and c == d) or (c == b and a == d) or (a == c and b == d):
            return 'prostokat'
        return 'czworobok'
    return('to koniec jest')
print(check_figure(sys.argv))
