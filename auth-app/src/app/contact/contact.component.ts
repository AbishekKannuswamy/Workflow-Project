import { Component, AfterViewInit } from '@angular/core';

declare var emailjs: any;

@Component({
  selector: 'app-contact',
  standalone: true,
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements AfterViewInit {
  ngAfterViewInit() {
    // Load EmailJS if not already loaded
    if (!(window as any).emailjs) {
      const script = document.createElement('script');
      script.src = 'https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js';
      script.onload = () => this.initEmailJS();
      document.body.appendChild(script);
    } else {
      this.initEmailJS();
    }
  }

  initEmailJS() {
    emailjs.init("XM7-a9legTV07uRj6");
    const form = document.getElementById('contactForm');
    if (form) {
      form.addEventListener('submit', (event: Event) => {
        event.preventDefault();
        const name = (document.getElementById('name') as HTMLInputElement).value;
        const query = (document.getElementById('query') as HTMLTextAreaElement).value;
        const email = (document.getElementById('email') as HTMLInputElement).value;

        emailjs.send("service_47c5zlo", "template_nb9atoj", {
          name: name,
          query: query,
          email: email
        }).then(function(response: any) {
          console.log("Email sent successfully!", response.status, response.text);
          alert("Email sent successfully!");
        }, function(error: any) {
          console.error("Failed to send email. Please try again later.", error);
          alert("Failed to send email. Please try again later.");
        });
      });
    }
  }
}
