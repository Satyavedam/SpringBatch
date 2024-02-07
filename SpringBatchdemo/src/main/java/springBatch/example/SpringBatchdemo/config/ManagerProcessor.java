package springBatch.example.SpringBatchdemo.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import springBatch.example.SpringBatchdemo.entity.Manager;

@Component
	public class ManagerProcessor implements ItemProcessor<Manager, Manager> {

		@Override
		public Manager process(Manager manager) throws Exception {
			return manager;
		}

	}


