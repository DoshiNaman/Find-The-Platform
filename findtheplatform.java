// Java program for the above approach

import java.util.*;

// Stores the information for each
// train as Objects
class Train {
	// Stores the train number
	String train_num;

	// Stores the arrival time
	int arrival_time;

	// Stores the stoppage time
	int stoppage_time;

	// Constructor
	Train(String train_num,
		int arrival_time,
		int stoppage_time)
	{
		this.train_num = train_num;
		this.arrival_time = arrival_time;
		this.stoppage_time = stoppage_time;
	}
}

class GFG {
	// Function to find the platform
	// on which train F arrives
	static int findPlatformOf(
		ArrayList<Train> trains, int n,
		String F)
	{
		// Sort the array arr[] according
		// to the arrival time
		Collections.sort(
			trains,
			(a, b) -> a.arrival_time - b.arrival_time);

		// Stores the platforms that
		// is in currently in use
		PriorityQueue<int[]> pq
			= new PriorityQueue<>(
				(a, b)
					-> a[1] == b[1] ? a[0] - b[0]
									: a[1] - b[1]);

		// Insert the platform number 1
		// with departure time as 0
		pq.add(new int[] { 1, 0 });

		// Store the platform number
		// on which train arrived
		HashMap<String, Integer> schedule
			= new HashMap<>();

		// Traverse the given array
		for (Train t : trains) {

			// Pop the top platform of
			// the priority queue
			int[] free_platform = pq.poll();

			// If arrival time of the train
			// >= freeing time of the platform
			if (t.arrival_time >= free_platform[1]) {
				// Update the train status
				free_platform[1]
					= t.arrival_time + t.stoppage_time + 1;

				// Add the current platform
				// to the pq
				pq.add(free_platform);

				// Add the platform
				// number to the HashMap
				schedule.put(t.train_num,
							free_platform[0]);
			}

			// Otherwise, add a new platform
			// for the current train
			else {

				// Update the priority queue
				pq.add(free_platform);

				// Get the platform number
				int platform_num = pq.size() + 1;

				// Add the platform to
				// the priority queue
				pq.add(new int[] {
					platform_num,
					t.arrival_time
						+ t.stoppage_time + 1 });

				// Add the platform
				// number to the HashMap
				schedule.put(t.train_num,
							platform_num);
			}
		}

		// Return the platform on
		// which F train arrived
		return schedule.get(F);
	}

	// Driver Code
	public static void main(String[] args)
	{
		ArrayList<Train> trains
			= new ArrayList<>();

		trains.add(new Train(

			"112567", 2, 1));
		trains.add(new Train(
			"112563", 5, 5));
		trains.add(new Train(
			"112569", 7, 3));
		trains.add(new Train(
			"112560", 3, 7));
		String F = "112569";

		System.out.println(
			findPlatformOf(
				trains, trains.size(), F));
	}
  //code made by 'naman_d0shi'
}

