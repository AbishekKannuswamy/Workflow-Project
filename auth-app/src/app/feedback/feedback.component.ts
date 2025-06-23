import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-feedback',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {
  showPopup = false;
  feedbackText = '';
  userName = '';

  @Output() feedbackSubmit = new EventEmitter<{ name: string, feedback: string }>();

  openPopup() {
    this.showPopup = true;
  }

  closePopup() {
    this.showPopup = false;
    this.feedbackText = '';
    this.userName = '';
  }

  sendFeedback() {
    if (this.feedbackText.trim()) {
      this.feedbackSubmit.emit({
        name: this.userName || 'Anonymous',
        feedback: this.feedbackText
      });
      this.closePopup();
    }
  }
}
