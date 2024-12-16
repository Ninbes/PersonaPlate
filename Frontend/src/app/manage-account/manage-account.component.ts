import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-manage-account',
  templateUrl: './manage-account.component.html',
  standalone: true,
  styleUrl: './manage-account.component.css',
  imports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    FormsModule,
  ],
})
export class ManageAccountComponent implements OnInit {
  token = '';
  name = '';
  email = '';
  password = '';
  message = '';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.token = token;
    } else {
      console.error('No token found!');
    }
  }

  updateAccount() {
    const updates = { name: this.name, email: this.email, password: this.password };
    this.authService.updateAccount(this.token, updates).subscribe(
      () => {
        this.message = 'Fiók sikeresen frissítve!';
      },
      (error) => {
        this.message = 'Hiba történt: ' + error.error.message;
      }
    );
  }

  deleteAccount() {
    if (this.token) {
      this.authService.deleteAccount(this.token).subscribe(
        () => {
          this.message = 'Fiók sikeresen törölve!';
        },
        (error) => {
          this.message = 'Hiba történt: ' + error.error.message;
        }
      );
    } else {
      this.message = 'No token found. Please log in again.';
    }
  }
}
