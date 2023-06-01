# Data Processing and Backend Development Exercise
**Duration:** Approximately 4 hours, but don't hesitate to submit incomplete work if you reach this time limit.  
**Preferred Language:** Python (Not mandatory)  
**Tests:** Optional but appreciated

## Task Overview
Your objective in this exercise is to process company data and construct a read-only API for data querying. 
Prioritize creating code that is simple, clear, and conducive to debugging, testing, and extension. 
Comments addressing your assumptions and potential enhancements to your code are encouraged.
Partial implementation of the idea is acceptable, provided that it is articulated clearly.

### Step 1: Dataset Enrichment
- The `companies.csv` dataset encompasses a list of companies.
- The `websites.csv` dataset contains classified websites originating from another data source.
- Your task is to augment the company dataset with a new `website_category` field by correlating both datasets using the `website` and `domain` fields.
- You may process this in-memory and save the results in a separate file, e.g., `companies-with-categories.csv`.

### Step 2: Populate Database with Enriched Dataset
- Import the enriched company dataset into PostgreSQL, SQLite, or any other relational database.
- Select suitable column types to facilitate easier data querying.
- A single table (e.g., `companies`) will suffice.

### Step 3: API Endpoint Development
- Use the enriched company data retrieved from the database.
- Design an API endpoint that supports searching for companies, with filtering options for revenue and category, and sorting by any field.
- You may use Flask, FastAPI, or any other comparable framework.
