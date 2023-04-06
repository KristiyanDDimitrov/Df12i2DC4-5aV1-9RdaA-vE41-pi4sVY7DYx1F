<b>GETTING STARTED</b>

Hello and welcome to COVID19 Stats! The extremely simple proof-of-concept by Kristiyan Dimitrov for pro-ect.

What this app does in essence is extract data from a pre-defined COVID19 statistics API and visualises it in a
bare-bones HTML page. Data is saved in the database within the zipped file itself, but every time the app is started,
the data is cleared and re-filled, assuring fresh data each time.

In order to run it, simply:

1. Clone project if not yet done.
2. If you're reading this from the .zip file... unzip and open folder with IntelliJ.
3. Open "Database" on right-hand side, click on "+" and add new Data Source.
4. Choose MariaDB, set username and password to "root", click on "Apply".
5. Run the application from Covid19StatsApplication
6. In a browser, enter http://localhost:8080/country/{XX}, where {XX} is the two-letter representation of each country
(i.e. "BG" for Bulgaria, "DE" for Germany, etc.)


<b>POTENTIAL IMPROVEMENTS</b>

A few things could have been done better. One, for instance, is the lack of exception-handling. If you try and enter a
wrong country code you will be met by a whitelabel exception.

Another is making the page visually pleasing not just a proof-of-concept bare-bones HTML page like it's an enthusiast's
project from 1992.

There is also some casting that is... questionable at best, but hopefully it doesn't poke an eye out.

And finally, could have added some basic tests, but due to having to prepare for other interviews as well, time was scarce.