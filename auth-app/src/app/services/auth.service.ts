import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

// --- Interfaces for Backend Communication (MATCH YOUR NEW FORM FIELDS) ---

// Registration Request Body - MODIFIED
interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  // firstName?: string; // Removed as per request
  // lastName?: string;  // Removed as per request
  // mobileNumber?: string; // Removed as per request
  //confirmPassword: string; // Still sending this for backend validation if needed
  role: string[]; // e.g., "Manager", "Team Lead", "SDET Engineer"
  otp?: string; // Optional, as it's conditional
}

// Registration Success Response (unchanged)
interface RegisterResponse {
  message: string;
  userId: string;
}

// Login Request Body (unchanged)
interface LoginRequest {
  username: string;
  password: string;
}

// Login Success Response (unchanged)
interface LoginResponse {
  message: string;
  token: string;
}

// Generic Backend Error Response (unchanged)
interface BackendErrorResponse {
  error: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private backendBaseUrl = 'http://localhost:8081/api/auth/public'; // Ensure this matches your backend URL

  constructor(private http: HttpClient) { }

  register(userData: RegisterRequest): Observable<RegisterResponse> {
    const registerUrl = `${this.backendBaseUrl}/signup`;
    console.log('Sending registration request to backend:', userData);

    return this.http.post<RegisterResponse>(registerUrl, userData)
      .pipe(
        tap(response => console.log('Registration successful on frontend:', response)),
        catchError(this.handleError)
      );
  }

  login(credentials: LoginRequest): Observable<LoginResponse> {
    const loginUrl = `${this.backendBaseUrl}/signin`;
    console.log('Sending login request to backend:', credentials);

    return this.http.post<LoginResponse>(loginUrl, credentials)
      .pipe(
        tap(response => {
          console.log('Login successful on frontend:', response);
          // localStorage.setItem('authToken', response.token); // Store token in a real app
        }),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Network/Client Error: ${error.error.message}`;
    } else if (error.error && typeof error.error === 'object' && 'error' in error.error) {
      const backendError = error.error as BackendErrorResponse;
      errorMessage = `Backend Error (${error.status}): ${backendError.error}`;
    } else {
      errorMessage = `Server Error (${error.status}): ${error.message || 'No specific error message.'}`;
      if (error.status === 0) {
        errorMessage = 'Connection refused. Is the backend server running and accessible at ' + this.backendBaseUrl + '?';
      }
    }
    console.error('Auth API Call Failed:', error);
    return throwError(() => new Error(errorMessage));
  }
}
