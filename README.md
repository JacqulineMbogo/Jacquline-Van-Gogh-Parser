# Jacquline Scraper Solution

This project is a simple web scraper built using **Kotlin**, **Spring Boot**, and **Selenium** to fetch a list of results from a specified web page. The project exposes an API that returns a list of result details in JSON format.

I chose **Kotlin** with **Spring Boot** for this project because It is my language of choice, and I am most comfortable working with it.

I have used **Selenium** because it is a powerful tool for web scraping, especially when dealing with websites that use **JavaScript** to render content dynamically. Many websites today rely on JavaScript to load data, and traditional scraping methods (like `Jsoup`) won’t work in these cases. Selenium, being a browser automation tool, can interact with JavaScript content and allow us to extract data that would otherwise be hidden.

I used `findElementByCssSelector` in Selenium because **CSS selectors** provide a simple and flexible way to navigate and extract elements from a web page. They are easy to write and understand, and they can be very powerful when selecting elements that have specific classes, IDs, or attributes.

## Running the Project

You can run this project both locally (on your machine) and inside a **Docker container**. Below are the steps to set up the project in both scenarios.

### Prerequisites

Before running the project, ensure you have the following installed:

1. **Java 17** (or a compatible version)  
   Ensure you have **Java 17** or later installed on your machine.

2. **Gradle**  
   Gradle is required to build the project. You can download and install it from [here](https://gradle.org/install/).

3. **Docker** (if using Docker)  
   Install **Docker** from [Docker's website](https://www.docker.com/get-started) if you plan to run the project inside a Docker container.

4. **Google Chrome** and **ChromeDriver**  
   The project uses **Selenium** with **Chrome**. Make sure that **Google Chrome** and **ChromeDriver** are installed on your system or included in the Docker container for it to run headlessly.

---

### Running the Project Locally

1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd <project_directory>