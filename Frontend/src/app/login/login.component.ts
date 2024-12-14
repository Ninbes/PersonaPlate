import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {FormsModule} from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [FormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  jelszo = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.email, this.jelszo).subscribe(
      (response: any) => {
        localStorage.setItem('token', response);
        console.log('Sikeres bejelentkezés');
        this.router.navigate(['/ruhak']);
      },
      error => {
        console.error('Hiba történt a bejelentkezés során', error);
      }
    );
  }
}
