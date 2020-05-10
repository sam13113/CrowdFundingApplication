import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal/ngx-bootstrap-modal';
@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {

  redirectUrl = '';
  @ViewChild('lgModal') lgModal: ModalDirective;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  showModal() {
    this.redirectUrl = '';
    this.lgModal.show();
  }

  hideModal() {

    this.lgModal.hide();
    console.log('redirectUrl', this.redirectUrl);
    if (this.redirectUrl !== '') {
      this.router.navigate([this.redirectUrl]);
    }

  }
}
