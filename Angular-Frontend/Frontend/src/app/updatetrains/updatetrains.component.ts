import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Train } from '../train';
import { TraindataService } from '../traindata.service';

@Component({
  selector: 'app-updatetrains',
  templateUrl: './updatetrains.component.html',
  styleUrls: ['./updatetrains.component.css']
})
export class UpdatetrainsComponent implements OnInit {

  trainNo!: String;
  train: Train = new Train();
  message:any;

  constructor(private service: TraindataService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.trainNo = this.route.snapshot.params['trainNo'];
    this.service.getTrainByno(this.trainNo).subscribe( data => {
      this.train = data;
      console.log(this.train);
      
    }, error => console.log(error));
  }

  
  async updateTrain() {
    await this.service.updateTrains(this.trainNo, this.train).subscribe( data => {
      
    })
    console.log("Welcome");
    
    alert("Train Updated Successfully")
    this.router.navigate(['/managetrains']);
    
  }

}
