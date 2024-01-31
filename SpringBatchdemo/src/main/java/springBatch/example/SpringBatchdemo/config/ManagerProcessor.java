package springBatch.example.SpringBatchdemo.config;

import javax.batch.api.chunk.ItemProcessor;

import org.springframework.stereotype.Component;

public class ManagerProcessor {
	
	@Component
	public class ManagerProcessor implements ItemProcessor<Manager, Manager> {

	    @Override
	    public Manager process(Manager manager) throws Exception {
	        return manager;
	    }

	}


}
