// registration.component.ts
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  name: string = '';
  email: string = '';
  password: string = '';
  city: string = '';
  phoneNumber: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    this.authService.register(this.name, this.email, this.password, this.city, this.phoneNumber).subscribe(
      response => {
        console.log('Registration successful', response);
        this.router.navigate(['/login']);  // Redirect to login page on successful registration
      },
      error => {
        console.error('Registration error', error);
      }
    );
  }
}
