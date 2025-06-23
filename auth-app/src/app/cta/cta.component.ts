import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-cta',
  templateUrl: './cta.component.html',
  styleUrls: ['./cta.component.css']
})
export class CtaComponent {
  @Output() signUp = new EventEmitter<void>();

  onSignUp() {
    this.signUp.emit();
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
