import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from './modal/modal.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { ImageUploadComponent } from './image-upload/image-upload.component';
import { CurrencyConverterPipe } from '../pipes/currency';


@NgModule({
  declarations: [CurrencyConverterPipe, ModalComponent, LoginComponent, SignupComponent, ImageUploadComponent],
  imports: [
    CommonModule,
    TranslateModule,
    FormsModule,
    ReactiveFormsModule,
    ModalModule.forRoot()
  ],
  providers:[TranslateService],
  exports: [CurrencyConverterPipe, TranslateModule, FormsModule,
    ReactiveFormsModule, ModalComponent, LoginComponent, SignupComponent]
})
export class SharedModule { }
