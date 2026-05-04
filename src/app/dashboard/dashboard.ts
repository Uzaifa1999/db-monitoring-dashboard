import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
})
export class DashboardComponent implements OnInit {

  transactions: any[] = [];
  private apiUrl = 'http://localhost:8080/api/transactions';

  chart: any;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
  this.loadData();

  setInterval(() => {
    this.loadData();
  }, 5000); // every 5 sec
}

  loadData() {
    this.http.get<any[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.transactions = data;

        this.createChart(); // 🔥 create chart
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  createChart() {
    if (this.chart) {
      this.chart.destroy(); // avoid duplicate charts
    }

    const labels = this.transactions.map(t => t.id);
    const cpuData = this.transactions.map(t => t.amount);

    this.chart = new Chart('cpuChart', {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: 'CPU Usage (%)',
          data: cpuData,
          fill: false,
          tension: 0.3
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: true
          }
        }
      }
    });
  }
}