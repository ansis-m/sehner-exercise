import {AfterViewInit, Component, ElementRef, Input, OnChanges, SimpleChanges, ViewChild} from '@angular/core';
import {MatTableDataSource, } from '@angular/material/table';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements AfterViewInit, OnChanges{
  displayedColumns: string[] = ['No', 'id', 'name', 'foundedOn', 'revenue', 'website', 'category'];

  @Input()
  dataSource: MatTableDataSource<Company> = new MatTableDataSource<Company>();

  @ViewChild('table', { static: false }) tableRef!: ElementRef;

  ngAfterViewInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes['dataSource']) {
      if (this.tableRef && this.tableRef.nativeElement) {
        this.tableRef.nativeElement.scrollTop = 0;
        this.tableRef.nativeElement.scrollLeft = 0;
      }
    }
  }
}

export interface Company {
  id: string;
  name: string;
  foundedOn: string;
  revenue: number;
  website: string;
  category: string;
}

