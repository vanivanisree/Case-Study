package com.casestudy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.BookingModel;
import com.casestudy.model.TrainModel;
import com.casestudy.repository.TrainRepository;

@Transactional
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/train")
public class TrainController {
	
	@Autowired
	TrainRepository trainrepo;
	
	//Adding Logger 
	Logger logger = LoggerFactory.getLogger(TrainController.class);
	
	
	
	//Rest API to add Train details
	@PostMapping("/addtrain")
	public void addtrain(@RequestBody TrainModel trainmodel) {
		trainrepo.save(trainmodel);
		
		//logger implementation
        logger.info("[addtrain] info message added");
        logger.debug("[addtrain] debug message added");
        
        return;
	
	}
	
	
	//Rest API to get all Train details
	@GetMapping("/viewalltrains")
	public List<TrainModel> getAllTrains(){
		
		//logger implementation
        logger.info("[viewalltrains] info message added");
        logger.debug("[viewalltrains] debug message added");
        
		return trainrepo.findAll();
	}
	

	//Rest API to get Train details by Id
	@GetMapping("/viewtrainbyno/{trainNo}")
	public TrainModel getTrains(@PathVariable("trainNo") String trainNo){
		
		//logger implementation
        logger.info("[viewtrainbyno/trainId] info message added");
        logger.debug("[viewtrainbyno/trainId] debug message added");
		
		return trainrepo.findByTrainNo(trainNo);		
	}
	
	//Rest API to get Train details by Name
	@GetMapping("/viewtrainbyname/{trainName}")
	public List<TrainModel> getTrainsbyname(@PathVariable("trainName") String trainName){
		
		//logger implementation
        logger.info("[viewtrainbyname/trainName] info message added");
        logger.debug("[viewtrainbyname/trainName] debug message added");
		
		return trainrepo.findByTrainName(trainName);		
	}
	
	
	//Rest API to update Train details by Id
	@RequestMapping(value="/updatetrain/{trainNo}", method=RequestMethod.PUT)
	public String update(@PathVariable("trainNo") String trainNo, @RequestBody TrainModel trainmodel) {
		trainrepo.save(trainmodel);
		
		//logger implementation
        logger.info("[updatetrain/trainId] info message added");
        logger.debug("[updatetrain/trainId] debug message added");
        
		return "Train with no. "+trainNo+" havebeen updated successfully";
	}
	
	
	//Rest API to delete Train details by Id
	@DeleteMapping("/deletetrain/{trainNo}")
	public void delete(@PathVariable String trainNo){
		TrainModel obj = new TrainModel();
		obj=trainrepo.findByTrainNo(trainNo);
		trainrepo.delete(obj);
		
		//logger implementation
        logger.info("[deletetrain/trainId] info message added");
        logger.debug("[deletetrain/trainId] debug message added");
	}
	
	
	//Rest API to get Train details from a particular source to destination
	@GetMapping("/findbw/{trainFrom}/{trainTo}")
	public List<TrainModel> findByloc(@PathVariable("trainFrom") String trainFrom, @PathVariable("trainTo") String trainTo){
		
		//logger implementation
        logger.info("[findto/source/destination] info message added");
        logger.debug("[findto/source/destination] debug message added");
        
		return trainrepo.findByTrainFromAndTrainTo(trainFrom,trainTo);
	}
	
	

	
	
	//Rset API to get Train fare by Train Id 
	@GetMapping("/findfarebyno/{trainNo}")
	public int findfare(@PathVariable("trainNo") String trainNo){
		
		//logger implementation
        logger.info("[findfarebyno/trainId] info message added");
        logger.debug("[findfarebyno/trainId] debug message added");
        
		return trainrepo.findByTrainNo(trainNo).getFare();
	}
	
	
	//Rest API to decrease Train Seats by Train Id 
	@PostMapping("/decreaseseat/{trainNo}/{seats}")
	public void decreaseseats(@PathVariable("trainNo") String trainNo, @PathVariable("seats") int seats, @RequestBody BookingModel bookmodel){
		TrainModel obj = new TrainModel();
		obj=trainrepo.findByTrainNo(trainNo);
		int temp = obj.getSeats();
		obj.setSeats(temp-seats);
		trainrepo.save(obj);
		
		//logger implementation
        logger.info("[decreaseseat/trainId/seats] info message added");
        logger.debug("[decreaseseat/trainId/seats] debug message added");
	}
	
	
	//Rest API to increase Train Seats by Train Id 
	@PostMapping("/increaseseat/{trainNo}/{seats}")
	public void increaseseats(@PathVariable("trainNo") String trainNo, @PathVariable("seats") int seats){
		TrainModel obj = new TrainModel();
		obj=trainrepo.findByTrainNo(trainNo);
		int temp = obj.getSeats();
		obj.setSeats(temp+seats);
		trainrepo.save(obj);
		
		//logger implementation
        logger.info("[increaseseat/trainId/seats] info message added");
        logger.debug("[increaseseat/trainId/seats] debug message added");	
	}
	
	
}