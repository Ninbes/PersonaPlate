import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  standalone: true,
  styleUrl: './register.component.css',
  imports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    FormsModule,
  ],
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  message = '';

  constructor(private authService: AuthService, private router: Router) {}

  registrationSuccess = false;

register() {
  this.authService.register(this.name, this.email, this.password).subscribe(
    () => {
      this.message = 'Sikeres regisztráció!';
      this.registrationSuccess = true; // Mark success
    },
    (error) => {
      this.message = 'Hiba történt a regisztráció során: ' + error.error.message;
      this.registrationSuccess = false;
    }
  );
}

goToLogin() {
  this.router.navigate(['/']);
}
}
