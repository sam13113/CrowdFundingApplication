import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthUser } from 'src/app/core/models/auth-user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  status$: Subscription;
  user$: Subscription;
  authStatus: boolean;
  authUser: AuthUser;

  @Output() modal: EventEmitter<string> = new EventEmitter<string>();

  constructor() {this.authStatus=false; }

  ngOnInit(): void {
  }
 showModal(type) {
     this.modal.emit(type);
  }

  onLogout() {
    // this.authService.logOutUser();
  }
}
