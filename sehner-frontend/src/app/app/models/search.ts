
export class Search{

  public searchBy: string;
  public searchValue: string;
  public sortBy: string;
  public ascending: boolean;
  public minRevenue: string;
  public maxRevenue: string;

  constructor(
    searchBy: string,
    searchValue: string,
    sortBy: string,
    ascending: boolean,
    minRevenue: string,
    maxRevenue: string) {
      this.searchBy = searchBy;
      this.searchValue = searchValue;
      this.sortBy = sortBy;
      this.ascending = ascending;
      this.minRevenue = minRevenue;
      this.maxRevenue = maxRevenue;
  }


}
