import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AlertDialogComponent } from '../dialog/alert-dialog/alert-dialog.component';


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
    MatDialogModule
  ],
})
export class ManageAccountComponent implements OnInit {
  token = '';
  name = '';
  email = '';
  password = '';
  message = '';

  constructor(private authService: AuthService, private dialog: MatDialog) {}

  openDialog(title: string, message: string): void {
    this.dialog.open(AlertDialogComponent, {
      width: '350px',
      data: { title, message },
    });
  }

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
        this.openDialog('Success', 'Fiók sikeresen frissítve!');
      },
      (error) => {
        const errorMessage = error.error?.message || 'Ismeretlen hiba történt.';
        this.openDialog('Error', `Hiba történt: ${errorMessage}`);
      }
    );
  }

  deleteAccount() {
    if (this.token) {
      this.authService.deleteAccount(this.token).subscribe(
        () => {
          this.openDialog('Success', 'Fiók sikeresen törölve!');
        },
        (error) => {
          const errorMessage = error.error?.message || 'Ismeretlen hiba történt.';
          this.openDialog('Error', `Hiba történt: ${errorMessage}`);
        }
      );
    } else {
        this.openDialog('Error', 'No token found. Please log in again.');
    }
  }
}
