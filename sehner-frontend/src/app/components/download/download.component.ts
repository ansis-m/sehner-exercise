import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-download',
  templateUrl: './download.component.html',
  styleUrls: ['./download.component.css']
})
export class DownloadComponent {

  constructor(private http: HttpClient){}

  download(): void {
    const fileUrl = 'http://localhost:8089/download';
    const headers = new HttpHeaders().set('Accept', 'application/octet-stream');

    this.http.get(fileUrl, { headers, responseType: 'blob' })
      .subscribe((response: Blob) => {
        const blob = new Blob([response], { type: 'application/octet-stream' });
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'companies-with-categories.csv';
        link.click();
      });
  }
}
