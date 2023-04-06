<b>GETTING STARTED</b>

Hello and welcome to COVID19 Stats! The extremely simple proof-of-concept by Kristiyan Dimitrov for pro-ect.

What this app does in essence is extract data from a pre-defined COVID19 statistics API and visualises it in a
bare-bones HTML page.

In order to run it, simply:

1. If you're reading this from the .zip file... unzip and open folder with IntelliJ.
2. Since IntelliJ is smart and saves settings for databases as well, simply run the app.
3. In a browser, enter http://localhost:8080/country/{XX}, where {XX} is the two-letter representation of each country
(i.e. "BG" for Bulgaria, "DE" for Germany, etc.)

<b>TROUBLESHOOTING</b>

If database for some reason is not saved properly, open "Database" on right-hand side, click on "+" and add new
Data Source. Choose MariaDB, set username and password to "root". Then open a Query Console and paste the script.sql
file in the resources folder.

<b>POTENTIAL IMPROVEMENTS</b>

A few things could have been done better. One, for instance, is the lack of exception-handling. If you try and enter a
wrong country code you will be met by a whitelabel exception.

Another is making the page visually pleasing not just a proof-of-concept bare-bones HTML page like it's an enthusiast's
project from 1992.

There is also some casting that is... questionable at best, but hopefully it doesn't poke an eye out.