import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { HomeRoutingModule,HomeRoutingComponents } from './home-routing.module';



@NgModule({
  declarations: [HomeRoutingComponents ],
  imports: [
    HomeRoutingModule,
    RouterModule,
    SharedModule,
    CommonModule
  ]
})
export class HomeModule { }
