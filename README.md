# Cron-Expression-Parser

A cron parser is a tool that helps interpret and understand cron expressions, which are used in Unix-like operating systems for scheduling tasks (jobs) to run automatically at specific times.

### Cron Expression
A cron expression is a string of characters that represents a schedule. It consists of 5 time fields plus a command:

A simple command-line tool for parsing and explaining cron expressions. Built with Spring Boot.

```bash
┌─────────────── minute (0 - 59)
│ ┌───────────── hour (0 - 23)
│ │ ┌───────────── day of month (1 - 31)
│ │ │ ┌───────────── month (1 - 12)
│ │ │ │ ┌───────────── day of week (1 - 7 or MON-SUN)
│ │ │ │ │
* * * * * command
```
### Example Cron Expressions:

1. 0 2 * * * backup.sh

- Runs backup.sh at 2:00 AM every day


2. */15 * * * * check-status.sh

- Runs check-status.sh every 15 minutes


3. 0 9-17 * * MON-FRI alert.sh

- Runs alert.sh every hour from 9 AM to 5 PM, Monday through Friday
---

## Quick Start

Make sure you have Java 17 and Maven installed, then:

```bash
# Clone it
git clone git@github.com:yourusername/cron-parser.git

# Build it
cd cron-parser
mvn clean install

# Run it
mvn spring-boot:run
```

## How to Use
- Just type in a cron expression when prompted in the below format. The tool will break it down and explain each part.
- Sample Input : 
  > */15 0 1,15 * 1-5 /usr/bin/find

- Sample Output : 
  ```bash
  minute        0 15 30 45
  hour          0
  day of month  1 15
  month         1 2 3 4 5 6 7 8 9 10 11 12
  day of week   1 2 3 4 5
  command       /usr/bin/find


## Project Structure

```bash
    src/main/java/com/example/CronPaserCLI/
    ├── parsers/         # Different expression parsers
    ├── model/           # Data models
    ├── service/         # Business logic
    └── Application.java # Entry point
```
