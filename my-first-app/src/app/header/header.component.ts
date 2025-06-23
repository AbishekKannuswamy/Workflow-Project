import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    if (sidebar) {
      sidebar.classList.toggle('open'); // Toggle the 'open' class
    }
  }

  buttonClicked(buttonName: string) {
    console.log(`${buttonName} button clicked`);
  }
}
