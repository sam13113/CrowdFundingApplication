import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Project } from '../../core/models/project';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy  {

  projectsSub$: Subscription;
  projects: Project[];
  message = '';

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  daysToGo(end_date) {
    // return this.dateService.daysBetweenDates(new Date(), end_date);
  }

  ngOnDestroy() {
    // this.projectsSub$.unsubscribe();
  }

}
