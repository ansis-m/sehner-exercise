import {ChangeDetectorRef, Component} from '@angular/core';
import {Search} from "./app/models/search";
import {Company} from "./components/table/table.component";
import {MatTableDataSource} from "@angular/material/table";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private http: HttpClient,
              private cdr: ChangeDetectorRef){
    this.handleCustomEvent(new Search("", "", "", true, "", ""));
  }

  handleCustomEvent(search: Search) {
    console.log("form parent: " + JSON.stringify(search));

    const url = 'http://localhost:8089/companies/search';

    this.http.post<{content: Company[]}>(url, search).subscribe(result => {
      console.log(result);
      this.source = new MatTableDataSource<Company>(result.content);
      this.cdr.detectChanges();
    });
  }

  source = new MatTableDataSource<Company>();
}
