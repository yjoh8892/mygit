import csv
import math
import numpy as np
import matplotlib.pyplot as plt
from scipy.cluster.hierarchy import dendrogram, linkage

# Section 0.1: load_data(filepath)
def load_data(filepath):
    with open(filepath, encoding = "utf8") as csvfile:
        quality = csv.DictReader(csvfile)
        list = []
        keys = ['HP', 'Attack', 'Defense', 'Sp. Atk', 'Sp. Def', 'Speed']

        for line in quality:
            a_dict = dict(line)
            a_dict = {k: v for k, v in a_dict.items() if k in keys}
            list.append(a_dict)
    return list

# Section 0.2: calc_features(row)
def calc_features(row):
    output = np.array(np.zeros(6), dtype = np.int64)
    output[0] = int(row['Attack'])
    output[1] = int(row['Sp. Atk'])
    output[2] = int(row['Speed'])
    output[3] = int(row['Defense'])
    output[4] = int(row['Sp. Def'])
    output[5] = int(row['HP'])
    return output

# Section 0.3: hac(features)
def hac(features):
    class Cluster:
        id = -1
        points = set([])
        
        def __init__(self, id, points) -> None:
            self.id = id
            self.points = points
            pass
    
    NumPokemon = len(features)
    ClustersList = set([Cluster(i,set([i])) for i in range(len(features))])
    DistanceList = [[-1 for i in range(NumPokemon)] for j in range(NumPokemon)]

    Z = np.empty(shape = [NumPokemon - 1, 4])
    i = 0

    for p1 in range(NumPokemon):
        for p2 in range(NumPokemon):
            if p1 == p2: continue
            DistanceList[p1][p2] = np.linalg.norm(features[p2] - features[p1])

    for x in range(NumPokemon - 1):
        minDistance = [math.inf, 0, 0]
        OldClusters = set([])
    
        for a in ClustersList:
            for b in ClustersList:
                OldClusters.add(a.id)
                if b.id in OldClusters: continue
                NowDistance = []
    
                for point in a.points:
                    for pointComp in b.points:
                        NowDistance.append(DistanceList[point][pointComp])
            
                dataVal = [max(NowDistance), a.id, b.id]
                if dataVal[0] < minDistance[0]:
                    minDistance = dataVal
                elif dataVal[0] == minDistance[0]:
                    if dataVal[1] == minDistance[1]:
                        minDistance = dataVal if dataVal[2] < minDistance[2] else minDistance
                    else:
                        minDistance = dataVal if dataVal[1] < minDistance[1] else minDistance

        cluster1 = cluster2 = None
        for cluster in ClustersList:
            if minDistance[1] == cluster.id:
                cluster1 = cluster
            if minDistance[2] == cluster.id:
                cluster2 = cluster
        
        newPoint = cluster1.points.union(cluster2.points)
        newCluster = Cluster(NumPokemon + x, newPoint)

        LIndex = cluster1.id if cluster1.id > cluster2.id else cluster2.id
        SIndex = cluster1.id if cluster1.id < cluster2.id else cluster2.id

        Z[i] = [SIndex, LIndex, minDistance[0], len(newCluster.points)]

        ClustersList.remove(cluster1)
        ClustersList.remove(cluster2)
        ClustersList.add(newCluster)

        i += 1
    return np.array(Z)

# Section 0.4: imshow_hac(Z)
def imshow_hac(Z):
    dn = dendrogram(Z)
    plt.show()