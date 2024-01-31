package springBatch.example.SpringBatchdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springBatch.example.SpringBatchdemo.entity.Manager;

public class ManagerRepo extends JpaRepository <Manager,Integer> {

}
