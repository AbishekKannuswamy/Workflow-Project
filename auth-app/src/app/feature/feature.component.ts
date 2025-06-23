import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Required for *ngIf

@Component({
  selector: 'app-feature',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.css'],
  standalone: true, // Mark as standalone
  imports: [CommonModule], // Include CommonModule for *ngIf
})
export class FeatureComponent {
  showPopup: string | null = null; // Allow string or null values

  togglePopup(feature: string | null): void {
    this.showPopup = feature; // Set the popup to the feature or null
  }
}
