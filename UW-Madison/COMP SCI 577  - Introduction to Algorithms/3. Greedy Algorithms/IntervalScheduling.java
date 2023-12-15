// Yoo Jin OH
// SEP 29 2022

import java.util.*;

class Job {
  private final int start;
  private final int finish;

  public Job(int start, int finish) {
    this.start = start;
    this.finish = finish;
  }

  public int getStart() {
    return start;
  }

  public int getFinish() {
    return finish;
  }
}

public class IntervalScheduling {

  private static final Scanner scanner = new Scanner(System.in);

  private static void swap(Job[] jobs, int i, int j) {
    Job temp = jobs[i];
    jobs[i] = jobs[j];
    jobs[j] = temp;
  }

  private static int partition(Job[] jons, int low, int high) {
    Job pivot = jons[high];
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
      //Sort jobs by finish time
      if (jons[j].getFinish() < pivot.getFinish()) {
        i++;
        swap(jons, i, j);
      }
    }
    swap(jons, i + 1, high);
    return (i + 1);
  }

  // works in O(nlog(n))
  private static void quickSort(Job[] jobs, int low, int high) {
    if (low < high) {
      int index = partition(jobs, low, high);

      quickSort(jobs, low, index - 1);
      quickSort(jobs, index + 1, high);
    }
  }

  public static void printMaxJobs(Job[] jobs) {

    if (jobs.length == 0) {
      System.out.println(0);
      return;
    }

    quickSort(jobs, 0, jobs.length - 1);

    Job prevJob = jobs[0];
    int scheduledJobs = 1;

    for (int i = 1; i < jobs.length; i++) {
      if (jobs[i].getStart() >= prevJob.getFinish()) {
        scheduledJobs++;
        prevJob = jobs[i];
      }
    }
    System.out.println(scheduledJobs);
  }


  public static void main(String[] args) {
    int num = scanner.nextInt();

    for (int i = 0; i < num; i++) {
      int numJobs = scanner.nextInt();
      Job[] jobs = new Job[numJobs];
      for (int j = 0; j < numJobs; j++) {
        jobs[j] = new Job(scanner.nextInt(), scanner.nextInt());
      }
      printMaxJobs(jobs);
    }
  }
}
