import { Component } from '@angular/core';
import { DashboardComponent } from './dashboard/dashboard'; // ✅ IMPORT THIS

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DashboardComponent], // ✅ ADD HERE
  templateUrl: './app.html',
})
export class App {}