import {Component, EventEmitter, Output} from '@angular/core';
import {MatSelectChange} from "@angular/material/select";
import {Search} from "../../app/models/search";
import {ThemePalette} from "@angular/material/core";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent {


  color: ThemePalette = "warn";
  sortBy: string = "";
  ascending: boolean = true;
  searchBy: string = "";
  searchValue: string = "";
  minRevenue: string = "";
  maxRevenue: string = "";

  @Output() searchFields: EventEmitter<Search> = new EventEmitter<Search>();

  search() {
    let search = {
      searchBy: this.searchBy,
      searchValue: this.searchValue,
      sortBy: this.sortBy,
      ascending: this.ascending,
      minRevenue: this.minRevenue,
      maxRevenue: this.maxRevenue
    };
    console.log(search);
    this.searchFields.emit(search);
  }
}
