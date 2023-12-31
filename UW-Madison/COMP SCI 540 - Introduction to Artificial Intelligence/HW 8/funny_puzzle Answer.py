import heapq

def get_manhattan_distance(from_state, to_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    if not to_state:
        to_state = [1, 2, 3, 4, 5, 6, 7, 0, 0]

    distance = 0
    for i in range(1, 8):
        from_index = from_state.index(i)
        to_index = to_state.index(i)

        from_row, from_col = divmod(from_index, 3)
        to_row, to_col = divmod(to_index, 3)
        
        distance += (abs(from_row - to_row) + abs(from_col - to_col))     
    return distance

def print_succ(state):
    succ_states = get_succ(state)

    for succ_state in succ_states:
        print(succ_state, "h={}".format(get_manhattan_distance(succ_state)))


def get_succ(state):
    succ_states = []
    indices = [i for i, x in enumerate(state) if x == 0]

    for i in indices:
        manh_row, manh_col = divmod(i, 3)
        
        if (manh_row + 1 in range(3) and state[i + 3] != 0):
            temp_state = state.copy()
            temp_state[i] = state[i + 3]
            temp_state[i + 3] = 0
            succ_states.append(temp_state)

        if (manh_row - 1 in range(3) and state[i - 3] != 0): 
            temp_state = state.copy()
            temp_state[i] = state[i - 3]
            temp_state[i - 3] = 0
            succ_states.append(temp_state)
            
        if (manh_col + 1 in range(3) and state[i + 1] != 0):
            temp_state = state.copy()
            temp_state[i] = state[i + 1]
            temp_state[i + 1] = 0
            succ_states.append(temp_state) 

        if (manh_col - 1 in range(3) and state[i - 1] != 0):
            temp_state = state.copy()
            temp_state[i] = state[i - 1]
            temp_state[i - 1] = 0
            succ_states.append(temp_state)
    return sorted(succ_states)

def solve(state, goal_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    if not goal_state:
        goal_state = [1, 2, 3, 4, 5, 6, 7, 0, 0]
        
    Closed = []
    Open = []
    final_path = []  
    g = 0
    past_index = -1      
    curr_index = 0
    h = get_manhattan_distance(state)

    heapq.heappush(Open, (g + h, state, (g, h, curr_index, past_index)))
    max_queue_length = 1
    
    while (Open):
        max_queue_length = max(max_queue_length, len(Open))
        n = heapq.heappop(Open)
        Closed.append(n)

        if(n[2][1] == 0):
            break
        g = n[2][0] + 1
        past_index = n[2][2]
        succ_states = get_succ(n[1])

        for n_succ in succ_states:
            is_contain = False

            for Closed_elem in Closed:
                if(n_succ in Closed_elem):
                    is_contain = True
                    break

            if(is_contain == False):
                curr_index += 1
                h = get_manhattan_distance(n_succ)
                cost = g + h
                heapq.heappush(Open, (cost, n_succ, (g, h, curr_index, past_index)))
 
    last_index = len(Closed) - 1
    i = Closed[last_index][2][3]
    final_path.append(Closed[last_index])

    while(i > -1):
        for elem in Closed:
            if(elem[2][2] == i):   
                n = elem
                break
        final_path.insert(0, n)
        i = n[2][3]
        
    for succ_state_heap in final_path:
        print(succ_state_heap[1], "h={}".format(succ_state_heap[2][1]), "moves: {}".format(succ_state_heap[2][0]))
    print("Max queue length: {}".format(max_queue_length)) 

if __name__ == "__main__":
    """
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    """