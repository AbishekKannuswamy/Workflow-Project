import { Component, Output, EventEmitter } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  @Output() login = new EventEmitter<void>();
  @Output() register = new EventEmitter<void>();
  @Output() contact = new EventEmitter<void>();
  @Output() about = new EventEmitter<void>();

  scrollToSection(section: string) {
    const el = document.getElementById(section);
    if (el) el.scrollIntoView({ behavior: 'smooth' });
  }
}
