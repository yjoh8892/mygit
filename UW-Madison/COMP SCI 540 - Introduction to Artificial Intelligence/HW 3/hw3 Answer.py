from scipy.linalg import eigh
import numpy as np
import matplotlib.pyplot as plt
from re import I, X

def load_and_center_dataset(filename):
    # Your implementation goes here!
    X = np.load(filename)
    X = X - np.mean(X, axis = 0)
    return X

def get_covariance(dataset):
    # Your implementation goes here!
    X = dataset
    Y = np.dot(np.transpose(X), X)
    Z = Y / (len(X) - 1)
    return Z

def get_eig(S, m):
    # Your implementation goes here!
    EValues, EVectors = eigh(S, subset_by_index = [len(S) - m, len(S) - 1])
    i = EValues.argsort()[::-1]
    EValues = EValues[i]
    return np.diagflat(EValues), EVectors[:, i]

def get_eig_prop(S, prop):
    # Your implementation goes here!
    EValues, EVectors = eigh(S)
    index = EValues.argsort()[::-1]
    EValues = np.sort(EValues)[::-1]
    EValues = EValues[index]
    EValues = np.diagflat(EValues)

    for i in range(EValues.size):
        if (EValues[i] / np.sum(EValues) <= prop):
            break

    EValues = EValues[0: i]
    EValues = np.diagflat(EValues)
    EVectors = EVectors[:, i]
    
    return EValues, EVectors

def project_image(image, U):
    # Your implementation goes here!
    alpha = np.dot(U, np.transpose(U))
    image = np.dot(image, alpha)
    return image

def display_image(orig, proj):
    # Your implementation goes here!
    orig = np.transpose(np.reshape(orig, (32,32)))
    proj = np.transpose(np.reshape(proj, (32,32)))

    fig, (ax1, ax2) = plt.subplots(1, 2)
    ax1.set_title('Original')
    ax2.set_title('Projection')

    Im1 = ax1.imshow(orig, aspect = 'equal')
    fig.colorbar(Im1, ax = ax1)
    Im2 = ax2.imshow(proj, aspect = 'equal')
    fig.colorbar(Im2, ax = ax2)
    plt.show()