import gym
import random
import numpy as np
import time
from collections import deque
import pickle

from collections import defaultdict

EPISODES =  20000
LEARNING_RATE = .1
DISCOUNT_FACTOR = .99
EPSILON = 1
EPSILON_DECAY = .999


def default_Q_value():
    return 0

if __name__ == "__main__":

    random.seed(1)
    np.random.seed(1)
    env = gym.envs.make("FrozenLake-v1")
    env.seed(1)
    env.action_space.np_random.seed(1)


    # You will need to update the Q_table in your iteration
    Q_table = defaultdict(default_Q_value) # starts with a pessimistic estimate of zero reward for each state.
    episode_reward_record = deque(maxlen=100)

    for i in range(EPISODES):
        episode_reward = 0
        done = False
        obs = env.reset()

        ##########################################################
        # YOU DO NOT NEED TO CHANGE ANYTHING ABOVE THIS LINE

        while (not done):
            if np.random.random() > EPSILON:
                action = np.argmax((Q_table[obs, 0], Q_table[obs, 1], Q_table[obs, 2], Q_table[obs, 3]))
            else:
                action = np.random.randint(env.action_space.n)
                
            new_obs, episode_reward, done, info = env.step(action)
            tempQ = Q_table[obs, action]
            
            if (done):
                doneQ = episode_reward - tempQ
                Q_table[obs, action] = tempQ + LEARNING_RATE * doneQ
            else:
                possible_act = np.max((Q_table[new_obs, 0], Q_table[new_obs, 1], Q_table[new_obs, 2], Q_table[new_obs, 3]))
                max_Q = episode_reward + DISCOUNT_FACTOR * possible_act - tempQ
                Q_table[obs, action] = tempQ + LEARNING_RATE * max_Q
                
            obs = new_obs
            
        EPSILON *= EPSILON_DECAY

        # YOU DO NOT NEED TO CHANGE ANYTHING BELOW THIS LINE
        ##########################################################

        # record the reward for this episode
        episode_reward_record.append(episode_reward) 

        
        if i%100 ==0 and i>0:
            print("LAST 100 EPISODE AVERAGE REWARD: " + str(sum(list(episode_reward_record))/100))
            print("EPSILON: " + str(EPSILON) )
    
    
    #### DO NOT MODIFY ######
    model_file = open('Q_TABLE.pkl' ,'wb')
    pickle.dump([Q_table,EPSILON],model_file)
    model_file.close()
    #########################