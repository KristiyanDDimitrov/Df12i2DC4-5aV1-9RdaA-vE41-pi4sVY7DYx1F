create table covid_stats.countries
(
    id              VARCHAR(255)  NOT NULL,
    country_name    VARCHAR(255)  NOT NULL,
    country_code    varchar(255)  NOT NULL,
    slug            varchar(255)  NOT NULL,
    new_confirmed   int      NOT NULL,
    total_confirmed int      NOT NULL,
    new_deaths      int      NOT NULL,
    total_deaths    int      NOT NULL,
    new_recovered   int      NOT NULL,
    total_recovered int      NOT NULL,
    date            datetime NOT NULL
);