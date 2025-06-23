import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Import CommonModule for *ngIf

@Component({
  selector: 'app-feedback-popup',
  templateUrl: './feedback-popup.component.html',
  styleUrls: ['./feedback-popup.component.css'],
  standalone: true, // Mark as standalone
  imports: [FormsModule, CommonModule], // Include CommonModule for *ngIf
})
export class FeedbackPopupComponent {
  showPopup = false;
  feedbackName = '';
  feedbackText = '';

  submitFeedback() {
    console.log('Feedback Submitted:', {
      name: this.feedbackName,
      feedback: this.feedbackText,
    });
    alert('Thank you for your feedback!');
    this.showPopup = false;
    this.feedbackName = '';
    this.feedbackText = '';
  }
}
