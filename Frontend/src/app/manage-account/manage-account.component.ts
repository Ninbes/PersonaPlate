import { Component } from '@angular/core';
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
export class ManageAccountComponent {
  token = '';
  name = '';
  email = '';
  password = '';
  userId = '';
  message = '';

  constructor(private authService: AuthService) {}

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
    this.authService.deleteAccount(this.token, this.userId).subscribe(
      () => {
        this.message = 'Fiók sikeresen törölve!';
      },
      (error) => {
        this.message = 'Hiba történt: ' + error.error.message;
      }
    );
  }
}
