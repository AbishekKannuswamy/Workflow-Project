import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Required for *ngIf
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true, // Mark as standalone
  imports: [CommonModule, FormsModule], // Include CommonModule and FormsModule
})
export class LoginComponent {
  loginIdentifier: string = '';
  loginIdentifierError: string | null = null;
  password: string = '';
  passwordError: string | null = null;
  passwordFieldType: string = 'password';
  loginMessage: string | null = null;
  isLoggingIn: boolean = false;

  constructor(private router: Router) {}

  validateLoginIdentifier() {
    if (!this.loginIdentifier) {
      this.loginIdentifierError = 'Email or username is required.';
    } else {
      this.loginIdentifierError = null;
    }
  }

  validatePassword() {
    if (!this.password) {
      this.passwordError = 'Password is required.';
    } else {
      this.passwordError = null;
    }
  }

  togglePasswordVisibility(show: boolean) {
    this.passwordFieldType = show ? 'text' : 'password';
  }

  onForgotPassword() {
    // Implement forgot password logic here
    this.loginMessage = 'Forgot password clicked!';
  }

  onLogin() {
    this.validateLoginIdentifier();
    this.validatePassword();
    if (this.loginIdentifierError || this.passwordError) {
      return;
    }
    this.isLoggingIn = true;
    // Simulate login
    setTimeout(() => {
      this.isLoggingIn = false;
      if (this.loginIdentifier === 'user' && this.password === 'pass') {
        this.loginMessage = 'Login successful!';
      } else {
        this.loginMessage = 'Invalid credentials.';
      }
    }, 1000);
  }

  onRegisterLinkClick() {
    this.router.navigate(['registration']);
  }
}
