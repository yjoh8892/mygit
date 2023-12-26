import sys
import math
from xml.sax.handler import feature_string_interning


def get_parameter_vectors():
    '''
    This function parses e.txt and s.txt to get the  26-dimensional multinomial
    parameter vector (characters probabilities of English and Spanish) as
    descibed in section 1.2 of the writeup

    Returns: tuple of vectors e and s
    '''
    #Implementing vectors e,s as lists (arrays) of length 26
    #with p[0] being the probability of 'A' and so on
    e = [0] * 26
    s = [0] * 26

    with open('e.txt', encoding = 'utf-8') as f:
        for line in f:
            #strip: removes the newline character
            #split: split the string on space character
            char, prob = line.strip().split(" ")
            #ord('E') gives the ASCII (integer) value of character 'E'
            #we then subtract it from 'A' to give array index
            #This way 'A' gets index 0 and 'Z' gets index 25.
            e[ord(char) - ord('A')] = float(prob)
    f.close()

    with open('s.txt', encoding = 'utf-8') as f:
        for line in f:
            char, prob = line.strip().split(" ")
            s[ord(char) - ord('A')] = float(prob)
    f.close()

    return (e,s)

def shred(filename):
    #Using a dictionary here. You may change this to any data structure of
    #your choice such as lists (X=[]) etc. for the assignment
    X = [0] * 26
    with open (filename, encoding = 'utf-8') as f:
        # TODO: add your code here
        Alpha = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
        for line in f:
            for char in line:
                #Check if it is alphabet or not
                if char.isalpha():
                    #Capitalize all alphabets
                    char = char.upper()
                    idx = Alpha.find(char)
                    X[idx] += 1

    return X


print("Q1")
x_idx = shred('letter.txt')
for i in range(0, 26):
    print(chr(i + 65), x_idx[i], end = "\n")

print("Q2")
gpv = get_parameter_vectors()
X1e1 = x_idx[0] * math.log(gpv[0][0])
X1s1 = x_idx[0] * math.log(gpv[1][0])

print(format(X1e1, '.4f'), format(X1s1, '.4f'), sep = "\n")

print("Q3")
f_eng = math.log(0.6)
f_sp = math.log(0.4)

for i in range(0,26):
    f_eng = f_eng + x_idx[i] * math.log(gpv[0][i])
    f_sp = f_sp + x_idx[i] * math.log(gpv[1][i])

print(format(f_eng, '.4f'), format(f_sp, '.4f'), sep = "\n")

print("Q4")
if((f_sp - f_eng) >= 100):
    print("0.0000", end = "\n")
elif((f_sp - f_eng) <= -100):
    print("1.0000", end = "\n")
else:
    k = math.log(0.4) - math.log(0.6)
    for i in range(0, 26):
        k = k + (x_idx[i]) * (math.log(gpv[1][i]) - math.log(gpv[0][i]))
    print(format(1 / (1 + math.e**k), '.4f'))

# TODO: add your code here for the assignment
# You are free to implement it as you wish!
# Happy Coding!