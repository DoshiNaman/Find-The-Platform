# Find-The-Platform
Find the Platform at which the given Train arrives

Given a 2D array arr[][3] consisting of information of N trains where arr[i][0] is the train number, arr[i][1] is the arrival time, and arr[i][2] is the duration of stoppage time. Given another integer F representing the train number, the task is to find the platform number on which the train with train number F arrives according to the following rules:

  * Platform numbering starts from 1 and there is an infinite number of platforms.
  * The platform which is freed earlier is allocated to the next train.
  * If two or more platforms are freed at the same time then the train arrives at the platform with the lowest platform number.
  * If two or more trains arriving at the same time, then the train with a smaller train number is allocated first.

Examples:

Input: arr[] = {{112567, 1, 2}, {112563, 3, 3}, {112569, 4, 7}, {112560, 9, 3}}, F = 112569
Output: 1
Explanation:
Below is the order of the arrival of trains:
Train          Platform   Leaving Time
112567        1               4
112563        2               7
112569       1              12
112560       2              13

Therefore, the train with train number 112569 arrives at platform number 1.

Input: arr[] = {{112567, 2, 1}, {112563, 5, 5}, {112569, 7, 3}, {112560, 3, 7}}, F = 112569
Output: 3

Approach: The given problem can be solved by using the priority queue. Follow the steps below to solve this problem:

  * Sort the given array arr[] of N trains according to the arrival time of the trains.
  * Initialize a priority queue, say PQ of pairs {PlatformNumber, time} that implements the min-heap according to the least departure time. Insert the {platform number, time}       i.e., {1, 0} in the priority queue.
  * Initialize a HashMap, say M that stores the platform number on which any train arrives.
  * Traverse the given array arr[] and perform the following steps:
    * Pop the top platform of the PQ and store them in free_platform[].
    * If the arrival time of the current train is at least the departure time of the popped platform, then update the departure time of the popped platform as the (sum of the         arrival and the stoppage time + 1) and insert the current status of the platform in PQ and the current platform number of the current train in the HashMap M.
    * Otherwise, add the new platform entry to the PQ and the current platform number of the current train in the HashMap M.
  * After completing the above steps, print the platform number associated with the train number F in the HashMap M.
