package ru.schipanovvv.accountmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.schipanovvv.accountmanager.workers.MainExecutor;
import ru.schipanovvv.accountmanager.workers.OperationsGenerator;

@SpringBootApplication
public class AccountmanagerApplication implements CommandLineRunner {
	int countOperationsGenerator = 3;

	@Autowired
	private ApplicationContext context;

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(3);
		pool.setMaxPoolSize(20);
		pool.setQueueCapacity(0);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

	public static void main(String[] args) {

		SpringApplication.run(AccountmanagerApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		for (int i = 0; i < countOperationsGenerator; i++) {
			OperationsGenerator operationsGenerator = (OperationsGenerator) context.getBean("operationsGenerator");
			taskExecutor.execute(operationsGenerator);
		}

		MainExecutor mainExecutor = (MainExecutor) context.getBean("mainExecutor");
		taskExecutor.execute(mainExecutor);
	}
}
