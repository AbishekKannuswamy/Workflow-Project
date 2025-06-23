import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {
  private images: string[] = [
    'assets/feature-image.jpg',
    'assets/feature-image-1.jpg',
    'assets/feature-image-2.jpg',
    'assets/feature-image-3.jpg',
    'assets/feature-image-4.jpg',
  ];
  private currentIndex = 0;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.startImageRotation();
    }
  }

  startImageRotation(): void {
    setInterval(() => {
      this.currentIndex = (this.currentIndex + 1) % this.images.length;
      const carousel = document.querySelector('.bottom-section');
      if (carousel) {
        carousel.setAttribute(
          'style',
          `background-image: url('${this.images[this.currentIndex]}');`
        );
      }
    }, 6000);
  }
}
