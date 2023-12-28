import sys
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

if __name__=="__main__":
    # Question 2: Visualize Data
    readCSV = pd.read_csv(sys.argv[1])
    readCSV.set_index('year').plot()

    plt.xlabel('Year') 
    plt.ylabel('Number of frozen days')
    plt.savefig("plot.jpg")

    # Question 3: Linear Regression
    tempArray = np.array(readCSV)
    X = []
    Y = []

    for i in tempArray:
        tempMatrix = [1, i[0]]
        tempMatrix = np.array(tempMatrix)
        tempMatrix = tempMatrix.T
        X.append(tempMatrix)
        Y.append(i[1])

    X = np.array(X)
    Y = np.array(Y)
    
    # Question 3.1: Q3a
    print("Q3a:")
    print(X)

    # Question 3.2: Q3b
    print("Q3b:")
    print(Y)

    # Question 3.3: Q3c
    Z = np.matmul(X.T, X)
    print("Q3c:")
    print(Z)

    # Question 3.4: Q3d
    I = np.linalg.inv(Z)
    print("Q3d:")
    print(I)

    # Question 3.5: Q3e
    PI = np.matmul(I, X.T)
    print("Q3e:")
    print(PI)

    # Question 3.6: Q3f
    hat_beta = np.matmul(PI, Y)
    print("Q3f:")
    print(hat_beta)

    # Question 4: Prediction
    y_test = hat_beta[0] + (hat_beta[1] * 2021)
    print("Q4: " + str(y_test))

    # Question 5: Model Interpretation
    symbol = ""

    if hat_beta[1] > 0:
        symbol = ">"
    elif hat_beta[1] < 0:
        symbol = "<"
    else:
        symbol = "="

    print("Q5a: " + symbol)
    print("Q5b: The >, <, or = symbol indicates whether the number of days in the Mendota Ice Age has increased, decreased or remained unchanged over the years.")
    
    # Question 6: Model Limitation
    xMulti = (-hat_beta[0]) / hat_beta[1]
    print("Q6a: " + str(xMulti))
    print("Q6b: I think that x* is a compelling prediction because it is based on the data, showing decreasing trend of Mendota Ice days from 1855 to 2021")