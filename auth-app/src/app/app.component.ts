import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  showPage: string = 'login';

  userFeedbacks: { name: string, feedback: string }[] = [];

  constructor(private router: Router) {}

  ngOnInit() {
    if (typeof window !== 'undefined' && window.localStorage) {
      const stored = localStorage.getItem('userFeedbacks');
      if (stored) {
        this.userFeedbacks = JSON.parse(stored);
        console.log('Loaded feedbacks:', this.userFeedbacks);
      }
    }
  }

  navigateTo(page: 'login' | 'registration'): void {
    this.router.navigate([page]);
  }

  scrollToTop(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  onFeedbackSubmit(feedback: { name: string, feedback: string }) {
    this.userFeedbacks = [feedback, ...this.userFeedbacks].slice(0, 6); // Keep only latest 6
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('userFeedbacks', JSON.stringify(this.userFeedbacks));
    }
  }
}
