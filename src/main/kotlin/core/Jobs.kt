package core

interface Job {
  fun execute()
  fun complete() {
    Jobs.completeJob(this)
  }
}

object Jobs {
  private val activeJobs: MutableList<Job> = mutableListOf()
  private val completedJobs: MutableList<Job> = mutableListOf()

  fun getJobCount(): Int {
    return activeJobs.size
  }

  fun addJob(job: Job) {
    activeJobs.add(job)
  }

  fun completeJob(job: Job) {
    completedJobs.add(job)
  }

  fun executeJobs() {
    activeJobs.removeAll(completedJobs)
    completedJobs.clear()
    activeJobs.forEach { it.execute() }
  }
}