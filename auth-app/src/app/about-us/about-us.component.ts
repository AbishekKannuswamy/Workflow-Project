import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './about-us.component.html'
})
export class AboutUsComponent {
  creators = [
    {
      name: 'Alice Smith',
      role: 'Frontend Developer',
      photo: 'assets/creators/211421205007_Abishek Kannuswamy G R_Information Technology.jpg',
      socials: {
        github: 'https://github.com/alicesmith',
        linkedin: 'https://linkedin.com/in/alicesmith',
        twitter: 'https://twitter.com/alicesmith'
      }
    },
    {
      name: 'Bob Johnson',
      role: 'Backend Developer',
      photo: 'assets/creators/bob.jpg',
      socials: {
        github: 'https://github.com/bobjohnson',
        linkedin: 'https://linkedin.com/in/bobjohnson',
        twitter: 'https://twitter.com/bobjohnson'
      }
    },
    {
      name: 'Carol Lee',
      role: 'UI/UX Designer',
      photo: 'assets/creators/carol.jpg',
      socials: {
        github: 'https://github.com/carollee',
        linkedin: 'https://linkedin.com/in/carollee',
        twitter: 'https://twitter.com/carollee'
      }
    },
    {
      name: 'David Kim',
      role: 'DevOps Engineer',
      photo: 'assets/creators/david.jpg',
      socials: {
        github: 'https://github.com/davidkim',
        linkedin: 'https://linkedin.com/in/davidkim',
        twitter: 'https://twitter.com/davidkim'
      }
    },
    {
      name: 'Eva Brown',
      role: 'Project Manager',
      photo: 'assets/creators/eva.jpg',
      socials: {
        github: 'https://github.com/evabrown',
        linkedin: 'https://linkedin.com/in/evabrown',
        twitter: 'https://twitter.com/evabrown'
      }
    }
  ];
}
