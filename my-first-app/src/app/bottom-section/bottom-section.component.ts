import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bottom-section',
  templateUrl: './bottom-section.component.html',
  styleUrls: ['./bottom-section.component.css']
})
export class BottomSectionComponent implements OnInit {
  private images: string[] = [
    'assets/background-image.jpg', // Default image
    'assets/image1.jpg',
    'assets/image2.jpeg',
    'assets/image3.jpeg',
    'assets/image4.jpeg'
  ];
  private currentIndex = 0;

  ngOnInit(): void {
    this.startImageRotation();
  }

  startImageRotation(): void {
    setInterval(() => {
      this.currentIndex = (this.currentIndex + 1) % this.images.length;
      const bottomSection = document.querySelector('.bottom-section');
      if (bottomSection) {
        bottomSection.setAttribute(
          'style',
          `background-image: url('${this.images[this.currentIndex]}');`
        );
      }
    }, 4000); // Change image every 4 seconds
  }
}
