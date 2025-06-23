import { Component } from '@angular/core';
import { HeroImageComponent } from '../hero-image/hero-image.component';

@Component({
  selector: 'app-hero-section',
  standalone: true,
  imports: [HeroImageComponent],
  templateUrl: './hero-section.component.html',
  styleUrls: ['./hero-section.component.css']
})
export class HeroSectionComponent {
}
