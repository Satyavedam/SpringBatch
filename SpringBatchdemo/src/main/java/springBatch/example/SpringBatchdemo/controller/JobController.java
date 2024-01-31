package springBatch.example.SpringBatchdemo.controller;

public class JobController {
	

	@RestController
	@RequestMapping("/jobs")
	public class JobController {

		@Autowired
		private JobLauncher jobLauncher;

		@Autowired
		private Job job;

		@PostMapping("importManager")
		public void importCsvToDBJob() {
			JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
					.toJobParameters();
			try {
				jobLauncher.run(job, jobParameters);
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();

			}
		}
	}


}
