import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { AppConstants } from 'src/app/app.constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  formSubmit = false;
  constructor(
    private fb: FormBuilder) {

      this.loginForm = this.initLoginForm();
    }

  ngOnInit(): void {
  }
  onClickLogin() {
    this.formSubmit = true;
    if (this.loginForm.valid) {
      const data = this.loginForm.value;
    }
  }

  initLoginForm() {
    return this.fb.group({
      'email': ['', this.validateEMAIL],
      'password': ['', Validators.compose([Validators.required, Validators.minLength(8)])]
    });
  }

  onCloseModal() {
    // this.authService.modalShow$.next(false);
  }
  validateEMAIL(c: FormControl) {
    const EMAIL_REGEXP = AppConstants.EMAIL_REGEX;
    return EMAIL_REGEXP.test(c.value) ? null : { validateEMAIL: true };
  }


}
